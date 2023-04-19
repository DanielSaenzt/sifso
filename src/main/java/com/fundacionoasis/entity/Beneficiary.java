package com.fundacionoasis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private Integer age;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "id"})
    @JoinColumn(name = "roup_benficiary_id", referencedColumnName = "id")
    private GroupBeneficiary groupBeneficiary;

    @Getter
    @Setter
    private Boolean status;
}
