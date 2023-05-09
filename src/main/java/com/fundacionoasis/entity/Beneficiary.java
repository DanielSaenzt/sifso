package com.fundacionoasis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "beneficiary")
@AllArgsConstructor
@NoArgsConstructor
public class Beneficiary {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String dni;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String diseases;

    @Getter
    @Setter
    private String eps;

    @Getter
    @Setter
    private String sisben_score;

    @Getter
    @Setter
    private String cell_phone_number;

    @Getter
    @Setter
    private String relationship_pre_registering;

    @Getter
    @Setter
    private Integer disability;

    @Getter
    @Setter
    private Integer age;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "Id"})
    @JoinColumn(name = "group_benficiary_id", referencedColumnName = "Id")
    private GroupBeneficiary groupBeneficiary;

    @Getter
    @Setter
    private String status;
    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date created_at;
    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "Id"})
    @JoinColumn(name = "document_type_id", referencedColumnName = "Id")
    private DocumentType documentType;
    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "Id"})
    @JoinColumn(name = "blood_type_id", referencedColumnName = "Id")
    private BloodType bloodType;
    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "Id"})
    @JoinColumn(name = "sex_type_id", referencedColumnName = "Id")
    private SexType sexType;


    @PrePersist
    private void OnCreate(){created_at = new Date();}
}
