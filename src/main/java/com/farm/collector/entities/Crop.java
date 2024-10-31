package com.farm.collector.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "crop")
@Data
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cropId;
    private String cropName;
    @OneToOne(mappedBy = "crop", cascade = CascadeType.ALL)
    private Plant plant;
}
