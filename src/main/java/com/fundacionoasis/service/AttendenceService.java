package com.fundacionoasis.service;

import com.fundacionoasis.entity.AttendanceReport;
import com.fundacionoasis.entity.Beneficiary;
import com.fundacionoasis.entity.DocumentType;
import com.fundacionoasis.repository.IAttendaceReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendenceService {
    @Autowired
    private IAttendaceReportRepository iAttendaceReportRepository;
    public List<AttendanceReport> findAll(){
        return iAttendaceReportRepository.findAll();
    }

    public AttendanceReport save(AttendanceReport attendanceReport){
        return iAttendaceReportRepository.save(attendanceReport);
    }

    public void deleteById(Long id){
        iAttendaceReportRepository.deleteById(id);
    }


    public Optional<AttendanceReport> findById(Long id){
        return iAttendaceReportRepository.findById(id);
    }

}
