package com.potato.TutorCall.notice.controller;

import com.potato.TutorCall.notice.domain.Notice;
import com.potato.TutorCall.notice.dto.FaqDto;
import com.potato.TutorCall.notice.dto.NoticeDto;
import com.potato.TutorCall.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
@Tag(name="Notice API", description = "notice, faq CRUD를 위한 API")
public class NoticeController {
    private final NoticeService noticeService;

    @Operation(summary="Q&A 게시글 선택 조회", description = "Q&A 게시글 선택 조회")
    @GetMapping("/{noticeId}")
    public ResponseEntity<?> notice(@PathVariable("noticeId") int noticeId){
        return null;
    }

    @Operation(summary="Q&A 게시글 전체 조회", description = "Q&A 게시글 전체 조회")
    @GetMapping("/")
    public ResponseEntity<?> noticeAll(@RequestBody NoticeDto noticeDto){
        return null;
    }

    @Operation(summary="Q&A 게시글 작성", description = "관리자 Q&A 게시글 작성")
    @PostMapping("/")
    public ResponseEntity<Notice> createNotice(@RequestBody NoticeDto noticeDto){
        Notice savedNotice = noticeService.save(noticeDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedNotice);
    }

    @Operation(summary="Q&A 게시글 수정", description = "관리자 Q&A 게시글 수정")
    @PatchMapping("/{noticeId}")
    public ResponseEntity<?> updateNotice(@PathVariable("noticeId") int noticeId, @RequestBody NoticeDto noticeDto){
        return null;
    }

    @Operation(summary="Q&A 게시글 삭제", description = "관리자 Q&A 게시글 삭제")
    @DeleteMapping("/{noticeId}")
    public ResponseEntity<?> deleteNotice(@PathVariable("noticeId") int noticeId){
        return null;
    }

    @Operation(summary="Faq 조회", description = "Faq 게시글 조회")
    @GetMapping("/faq")
    public ResponseEntity<?> faq(FaqDto faqDto){
        return null;
    }

    @Operation(summary="Faq 등록", description = "관리자 Faq 게시글 작성")
    @PostMapping("/faq")
    public ResponseEntity<?> createFaq(@RequestBody FaqDto fqaDto){
        return null;
    }

    @Operation(summary="Faq 게시글 작성", description = "관리자 Faq 게시글 작성")
    @PatchMapping("/faq/{faqId}")
    public ResponseEntity<?> updateFaq(@PathVariable("faqId") int faqId, @RequestBody FaqDto fqaDto){
        return null;
    }

    @Operation(summary="Faq 게시글 삭제", description = "관리자 Faq 게시글 삭제")
    @DeleteMapping("/faq/{faqId}")
    public ResponseEntity<?> deleteFaq(@PathVariable("faqId") int faqId){
        return null;
    }
}
