package com.farm.collector.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "season")
@Data
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonId;
    private String seasonName;
    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private List<Plant> plants;
}
