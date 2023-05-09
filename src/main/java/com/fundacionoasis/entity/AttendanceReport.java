package com.fundacionoasis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "attendance_report")
@AllArgsConstructor
@NoArgsConstructor

public class AttendanceReport {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "workshop_id", referencedColumnName = "id")
    private Workshop workshop;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "beneficiary_id", referencedColumnName = "id")
    private Beneficiary beneficiary;
    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date created_at;
    @Getter
    @Setter
    private String status;

    @PrePersist
    private void OnCreate(){created_at = new Date();}

}
