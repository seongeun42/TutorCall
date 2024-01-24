package com.potato.TutorCall.notice.controller;

import com.potato.TutorCall.notice.domain.Faq;
import com.potato.TutorCall.notice.domain.Notice;
import com.potato.TutorCall.notice.dto.*;
import com.potato.TutorCall.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
@Tag(name="Notice API", description = "notice, faq CRUD를 위한 API")
public class NoticeController {
    private final NoticeService noticeService;

    @Operation(summary="Notice 게시글 선택 조회", description = "Notice 게시글 선택 조회")
    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeResponse> findNotice(@PathVariable("noticeId") long noticeId){
        Notice notice = noticeService.findById(noticeId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new NoticeResponse(notice));
    }

    @Operation(summary="Notice 게시글 전체 조회", description = "Notice 게시글 전체 조회")
    @GetMapping
    public ResponseEntity<List<NoticeResponse>> noticeAll() {
        List<NoticeResponse> notices = noticeService.findAll()
                .stream()
                .map(NoticeResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(notices);
    }
    
    @Operation(summary="Notice 게시글 작성", description = "관리자 Notice 게시글 작성")
    @PostMapping
    public ResponseEntity<Notice> createNotice(@RequestBody NoticeDto noticeDto){
        Notice savedNotice = noticeService.save(noticeDto);

        //생성된 공지사항 ID
        long noticeId = savedNotice.getId();

        // 응답 메시지 생성
//        Notice response = new Notice(noticeId, "공지사항이 생성되었습니다.");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedNotice);
    }

    @Operation(summary="Notice 게시글 수정", description = "관리자 Notice 게시글 수정")
    @PatchMapping("/{noticeId}")
    public ResponseEntity<Notice> updateNotice(@PathVariable("noticeId") long noticeId, @RequestBody UpdateNotice updateNotice){
        Notice updatedNotice = noticeService.update(noticeId, updateNotice);

        return ResponseEntity.ok()
                .body(updatedNotice);
    }

    @Operation(summary="Notice 게시글 삭제", description = "관리자 Notice 게시글 삭제")
    @DeleteMapping("/{noticeId}")
    public ResponseEntity<Void> deleteNotice(@PathVariable("noticeId") long noticeId){
        noticeService.delete(noticeId);

        return ResponseEntity.ok()
                .build();
    }

    @Operation(summary="Faq 조회", description = "Faq 게시글 조회")
    @GetMapping("/faq")
    public ResponseEntity<?> faqAll() {
        List<FaqResponse> faqs = noticeService.faqFind()
                .stream()
                .map(FaqResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(faqs);
    }

    @Operation(summary="Faq 등록", description = "관리자 Faq 게시글 작성")
    @PostMapping("/faq")
    public ResponseEntity<Faq> createFaq(@RequestBody FaqDto faqDto){
        Faq savedFaq = noticeService.saveFaq(faqDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedFaq);
    }

    @Operation(summary="Faq 게시글 수정", description = "관리자 Faq 게시글 수정")
    @PatchMapping("/faq/{faqId}")
    public ResponseEntity<Faq> updateFaq(@PathVariable("faqId") long faqId, @RequestBody UpdateFaq updateFaq){
        Faq updatedFaq = noticeService.updateFaq(faqId, updateFaq);
        return ResponseEntity.ok()
                .body(updatedFaq);
    }

    @Operation(summary="Faq 게시글 삭제", description = "관리자 Faq 게시글 삭제")
    @DeleteMapping("/faq/{faqId}")
    public ResponseEntity<Void> deleteFaq(@PathVariable("faqId") long faqId){
        noticeService.deleteFaq(faqId);

        return ResponseEntity.ok()
                .build();
    }

}
