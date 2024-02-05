package com.potato.TutorCall.report.repository;

import com.potato.TutorCall.report.domain.Report;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReportRepositoryImpl implements ReportRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public Long registReport(Report report) {
        entityManager.persist(report);
        entityManager.flush();
        entityManager.clear();

        return report.getId();
    }
}
