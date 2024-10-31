package com.farm.collector.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "season")
@Data
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonId;
    private String seasonName;
    @OneToOne(mappedBy = "season", cascade = CascadeType.ALL)
    private Plant plant;
}
