package com.fundacionoasis.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "planning")
@AllArgsConstructor
@NoArgsConstructor
public class Planning {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String comment;

    @Getter
    @Setter
    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date;

    @Getter
    @Setter
    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "plannings"})
    @JoinColumn(name = "workshop_id", referencedColumnName = "id")
    private Workshop workshop;
}
