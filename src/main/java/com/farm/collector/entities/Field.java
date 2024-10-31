package com.farm.collector.entities;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Plant> plants;
}
