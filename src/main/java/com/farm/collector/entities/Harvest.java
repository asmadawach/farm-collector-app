package com.farm.collector.entities;

import com.farm.collector.dto.request.HarvestDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "harvest")
@Data
public class Harvest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long harvestId;
    @OneToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;
    private double actualHarvestedAmount;

    public static Harvest toEntity(HarvestDto harvestDto) {
        if (harvestDto == null) {
            return null;
        }
        Harvest harvest = new Harvest();
        harvest.setActualHarvestedAmount(harvestDto.getActualHarvestedAmount());
        return harvest;
    }
}
