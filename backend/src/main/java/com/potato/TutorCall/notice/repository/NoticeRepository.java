package com.potato.TutorCall.notice.repository;

import com.potato.TutorCall.notice.domain.Notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
//    /** 삭제된 게시글 제외하고 조회 */
//    @Query(value = "Select a From Notice a Where a.state = :state Order By a.id Desc")
//    Page<Notice> findAll(Pageable pageable, @Param("state") DeleteState state);
//
//    /** 삭제된 게시글 제외하고 조회 + 키워드 */
//    @Query(value = "Select a From Notice a Where a.state = :state And a.content like concat('%', :keyword, '%') Order By a.id Desc")
//    Page<Notice> findAll(Pageable pageable, @Param("state") DeleteState state, @Param("keyword") String keyword);
//
//    /** 한 유저의 삭제된 게시글 제외하고 조회 */
//    @Query(value = "Select a From Notice a Where a.state = :state And a.writer = :user Order By a.id Desc")
//    Page<Notice> findAllByUser(Pageable pageable, @Param("state") DeleteState state, @Param("user") User user);
//
//    /** 한 유저의 삭제된 게시글 제외하고 조회 + 키워드 */
//    @Query(value = "Select a From Notice a Where a.state = :state And a.writer = :user And a.title like %:keyword% Order By a.id Desc")
//    Page<Notice> findAllByUser(Pageable pageable, @Param("state") DeleteState state, @Param("user") User user, @Param("keyword") String keyword);
}