package com.fundacionoasis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
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
    private String description;

    @Getter
    @Setter
    @Column(name = "type_beneficiary")
    private String typeBeneficiary;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "type_workshop_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TypeWorkshop typeWorkshop;

    @Getter
    @Setter
    @OneToMany(mappedBy = "workshop",cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "workshop"})
    private List<Planning> plannings;

    @Getter
    @Setter
    private Boolean status;
}
