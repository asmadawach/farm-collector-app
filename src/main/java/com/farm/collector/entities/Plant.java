package com.farm.collector.entities;

import com.farm.collector.dto.request.PlantDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "plant")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plantId;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;

    @ManyToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;

    private double plantingArea;

    private double expectedProductAmount;

    @OneToOne(mappedBy = "plant", cascade = CascadeType.ALL)
    private Harvest harvest;

    public static Plant toEntity(PlantDto plantDto) {
        if (plantDto == null) {
            return null;
        }

        Plant plant = new Plant();
        plant.setPlantingArea(plantDto.getPlantingArea());
        plant.setExpectedProductAmount(plantDto.getExpectedProductAmount());

        return plant;
    }
}
