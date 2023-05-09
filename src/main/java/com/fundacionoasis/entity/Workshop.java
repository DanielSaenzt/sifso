package com.fundacionoasis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "workshop")
@AllArgsConstructor
@NoArgsConstructor
public class Workshop {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "title")
    private String title;
    @Getter
    @Setter
    @Column(name = "date")
    private Timestamp start;

    @Getter
    @Setter
    @Column(name = "enddate")
    private Timestamp end;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

    @Getter
    @Setter
    @Column(name = "color")
    private String color;

    @Getter
    @Setter
    @Column(name = "type_beneficiary")
    private String typeBeneficiary;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "type_workshop_id", referencedColumnName = "Id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TypeWorkshop typeWorkshop;



    @Getter
    @Setter
    private String status;
}
