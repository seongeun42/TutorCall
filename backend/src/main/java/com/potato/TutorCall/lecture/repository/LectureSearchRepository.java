package com.potato.TutorCall.lecture.repository;

import com.potato.TutorCall.lecture.dto.LectureSearchCondition;
import com.potato.TutorCall.lecture.dto.LectureListResponseDto;
import com.potato.TutorCall.lecture.dto.QLectureListResponseDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.potato.TutorCall.lecture.domain.QLecture.lecture;

@Repository
@RequiredArgsConstructor
public class LectureSearchRepository {

    private final JPAQueryFactory queryFactory;

    public Page<LectureListResponseDto> search(LectureSearchCondition condition, Pageable pageable) {
        List<LectureListResponseDto> result = queryFactory
                .select(new QLectureListResponseDto(
                        lecture.id,
                        lecture.promotionTitle,
                        lecture.promotionContent,
                        lecture.tag,
                        lecture.tutor.user,
                        lecture.promotionState,
                        lecture.promotionCreatedAt))
                .from(lecture)
                .where(
                        keywordContains(condition.getKeyword()),
                        promotionStateEq(condition.getState()),
                        tagEq(condition.getTag()),
                        isDelete()
                )
                .orderBy(lecture.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(Wildcard.count)
                .from(lecture)
                .where(
                        keywordContains(condition.getKeyword()),
                        promotionStateEq(condition.getState()),
                        tagEq(condition.getTag()),
                        isDelete()
                ).fetch().get(0);

        System.out.println("개수 : " + result.size() + ", " + total);

        return new PageImpl<>(result, pageable, total);
    }

    private BooleanExpression keywordContains(String keyword) {
        return StringUtils.hasText(keyword) ? lecture.promotionTitle.contains(keyword) : null;
    }

    private BooleanExpression tagEq(Long tag) {
        return tag != null ? lecture.tag.id.eq(tag) : null;
    }

    private BooleanExpression promotionStateEq(Boolean state) {
        return state != null ? lecture.promotionState.eq(state) : null;
    }

    private BooleanExpression isDelete() {
        return lecture.isDelete.eq(false);
    }

}
