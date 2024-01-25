package com.potato.TutorCall.report.repository;

import com.potato.TutorCall.report.domain.Report;
import com.potato.TutorCall.report.domain.enums.ReportType;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>, ReportRepositoryCustom {

    Page<Report> findAllByTypeAndProceedState(Pageable pageable, ReportType reportType, boolean proceedState);

    @Modifying
    @Query(value="UPDATE Report r set r.proceedState = true where r.id = :reportId")
    int updateReportById(@Param("reportId") long reportId);

}
