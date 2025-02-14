package com.farm.collector.entities;



import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "crop")
@Data
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cropId;
    private String cropName;
    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL)
    private List<Plant> plants;
}
