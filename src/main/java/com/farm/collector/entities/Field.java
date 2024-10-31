package com.farm.collector.entities;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "field")
@Data
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fieldId;
    @OneToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;
    private String fieldName;
    @OneToOne(mappedBy = "field", cascade = CascadeType.ALL)
    private Plant plant;
}
