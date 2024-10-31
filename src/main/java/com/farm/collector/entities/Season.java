package com.farm.collector.entities;

import lombok.Data;
import javax.persistence.*;
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
