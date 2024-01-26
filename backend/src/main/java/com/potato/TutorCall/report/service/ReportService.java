package com.potato.TutorCall.report.service;

import com.potato.TutorCall.exception.customException.DuplicatedException;
import com.potato.TutorCall.exception.customException.InvalidException;
import com.potato.TutorCall.exception.customException.NotFoundException;
import com.potato.TutorCall.lecture.domain.Lecture;
import com.potato.TutorCall.lecture.repository.LectureRepository;
import com.potato.TutorCall.qna.domain.Answer;
import com.potato.TutorCall.qna.domain.Question;
import com.potato.TutorCall.qna.repository.AnswerRepository;
import com.potato.TutorCall.qna.repository.QuestionRepository;
import com.potato.TutorCall.report.domain.Report;
import com.potato.TutorCall.report.domain.enums.ReportType;
import com.potato.TutorCall.report.dto.CommonResponseDto;
import com.potato.TutorCall.report.dto.ReportForm;
import com.potato.TutorCall.report.dto.ReportListDto;
import com.potato.TutorCall.report.repository.ReportRepository;
import com.potato.TutorCall.user.domain.User;
import com.potato.TutorCall.user.domain.enums.RoleType;
import com.potato.TutorCall.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final LectureRepository lectureRepository;
    private final AnswerRepository answerRepository;

    public ReportService(ReportRepository reportRepository, UserRepository userRepository, QuestionRepository questionRepository, LectureRepository lectureRepository, AnswerRepository answerRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.lectureRepository = lectureRepository;
        this.answerRepository = answerRepository;
    }

    @Transactional
    public ResponseEntity<?> returnResponseEntity(long reportedId, User reporter,
                                                  ReportForm reportForm, ReportType reportType){
        Report report = Report.builder()
                .reported(reportedId)
                .reporter(reporter)
                .content(reportForm.getTitle())
                .result(reportForm.getContent())
                .type(reportType)
                .build();

        Long reportId = reportRepository.registReport(report);

        if(reportId == null)
            throw new NotFoundException("신고 접수 실패");

        CommonResponseDto commonResponseDto =
                CommonResponseDto.builder()
                        .message("신고 접수 성공")
                        .reportId(reportId)
                        .build();

        System.out.println(commonResponseDto.toString());

        return ResponseEntity.ok(commonResponseDto);
    }

    public boolean isDuplicatedReport(User reporter, long reportedId, ReportType reportType){

        Report report = reportRepository.findReportByReportedAndReporterAndType(reportedId, reporter,
                reportType);

        return report != null;
    }

    @Transactional
    public ResponseEntity<?> reportUser(long reporterId, long reportedId, ReportForm reportForm){

        User reporter =
                userRepository.findById(reporterId)
                        .orElseThrow(()-> new NotFoundException("잘못된 유저 접근"));
        User reported =
                userRepository.findById(reportedId)
                        .orElseThrow(()-> new NotFoundException("잘못된 유저 신고"));

        if(isDuplicatedReport(reporter, reportedId, ReportType.USER))
            throw new DuplicatedException("중복 신고는 불가능합니다!");

        return returnResponseEntity(reportedId, reporter, reportForm, ReportType.USER);

    }

    @Transactional
    public ResponseEntity<?> reportQuestion(long reporterId, long questionId, ReportForm reportForm){
        User reporter =
                userRepository.findById(reporterId)
                        .orElseThrow(()-> new NotFoundException("잘못된 유저 접근"));
        Question reported =
                questionRepository.findById(questionId)
                        .orElseThrow(()-> new NotFoundException("존재하지 않는 대상 신고"));

        if(isDuplicatedReport(reporter, questionId, ReportType.QUESTION))
            throw new DuplicatedException("중복 신고는 불가능합니다!");

        return returnResponseEntity(questionId, reporter, reportForm, ReportType.QUESTION);
    }

    @Transactional
    public ResponseEntity<?> reportPromotion(long reporterId, long lectureId, ReportForm reportForm){
        User reporter =
                userRepository.findById(reporterId)
                        .orElseThrow(()-> new NotFoundException("잘못된 유저 접근"));
        Lecture reported =
                lectureRepository.findById(lectureId)
                        .orElseThrow(()-> new NotFoundException("존재하지 않는 홍보물 신고"));

        if(isDuplicatedReport(reporter, lectureId, ReportType.PROMOTION))
            throw new DuplicatedException("중복 신고는 불가능합니다!");

        return returnResponseEntity(lectureId, reporter, reportForm, ReportType.PROMOTION);
    }

    @Transactional
    public ResponseEntity<?> reportAnswer(long reporterId, long answerId, ReportForm reportForm){
        User reporter =
                userRepository.findById(reporterId)
                        .orElseThrow(()-> new NotFoundException("잘못된 유저 접근"));
        Answer reported =
                answerRepository.findById(answerId)
                        .orElseThrow(()-> new NotFoundException("존재하지 않는 대상 신고"));

        if(isDuplicatedReport(reporter, answerId, ReportType.ANSWER))
            throw new DuplicatedException("중복 신고는 불가능합니다!");

        return returnResponseEntity(answerId, reporter, reportForm, ReportType.ANSWER);
    }

    public ResponseEntity<?> findAllReport(long userId, Pageable pageable, ReportListDto reportListDto){

        User user =
                userRepository.findById(userId)
                        .orElseThrow(()-> new NotFoundException("잘못된 유저 접근"));

        if(!user.getRole().equals(RoleType.ADMIN))
            throw new InvalidException("권한이 존재하지 않습니다");

        Page<Report> list = null;
        list = reportRepository.findAllByTypeAndProceedState(pageable, reportListDto.getType(),
                reportListDto.isState());

        CommonResponseDto commonResponseDto = CommonResponseDto.builder()
                .reports(list).build();

        return ResponseEntity.ok(commonResponseDto);
    }

    @Transactional
    public ResponseEntity<?> acceptReport(long userId, long reportId){

        User user =
                userRepository.findById(userId)
                        .orElseThrow(()-> new NotFoundException("잘못된 유저 접근"));

        if(!user.getRole().equals(RoleType.ADMIN))
            throw new InvalidException("권한이 존재하지 않습니다");

        Report report = reportRepository.findById(reportId)
                .orElseThrow(()->new NotFoundException("존재하지 않는 report"));

        if(report.isProceedState())
            throw new DuplicatedException("이미 처리된 신고물입니다.");

        int count = 0;
        switch(report.getType()){
            case USER -> {
                count = userRepository.updateUserByIdAndblock(report.getReported(), LocalDateTime.now().plusDays(14));
                break;
            }
            case ANSWER -> {
                count = answerRepository.updateAnswerByIdAndIsDelete(report.getReported(), true);
                break;
            }
            case QUESTION -> {
                count = questionRepository.updateQuestionByIdAndIsDelete(report.getReported(), true);
                break;
            }
            case PROMOTION -> {
                count = lectureRepository.updateLecturerByIdAndisDelete(report.getReported(), true);
                break;
            }
        }

        if (count == 0) throw new NotFoundException("잘못된 신고 대상");

        count = reportRepository.updateReportById(reportId);
        if (count == 0) throw new NotFoundException("신고 처리 실패");

        CommonResponseDto commonResponseDto = CommonResponseDto.builder()
                .reportId(reportId)
                .message("신고가 처리되었습니다").build();

        return ResponseEntity.ok(commonResponseDto);
    }

}

