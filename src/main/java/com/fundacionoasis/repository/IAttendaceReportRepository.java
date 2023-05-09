package com.fundacionoasis.repository;

import com.fundacionoasis.entity.AttendanceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttendaceReportRepository extends JpaRepository<AttendanceReport, Long> {
}
