package com.farm.collector.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "farm")
@Data
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long farmId;
    private String farmName;
    @OneToOne(mappedBy = "farm", cascade = CascadeType.ALL)
    private Field field;
}
