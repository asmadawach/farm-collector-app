package com.farm.collector.migration;

import com.farm.collector.entities.Crop;
import com.farm.collector.entities.Farm;
import com.farm.collector.entities.Field;
import com.farm.collector.entities.Season;
import com.farm.collector.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class DataMigration implements CommandLineRunner {

    private final FarmRepository farmRepository;
    private final CropRepository cropRepository;
    private final FieldRepository fieldRepository;
    private final SeasonRepository seasonRepository;
    private final PlantRepository plantRepository;
    private final HarvestRepository harvestRepository;

    @Override
    public void run(String... args) {
        loadSampleData();
    }

    private void loadSampleData() {
        // Create and save Farm objects
        Farm farm1 = new Farm();
        farm1.setFarmName("Sunny Farm");
        farmRepository.save(farm1);

        Farm farm2 = new Farm();
        farm2.setFarmName("Green Valley Farm");
        farmRepository.save(farm2);

        // Create and save Crop objects
        Crop crop1 = new Crop();
        crop1.setCropName("Wheat");
        cropRepository.save(crop1);

        Crop crop2 = new Crop();
        crop2.setCropName("Corn");
        cropRepository.save(crop2);

        // Create and save Field objects
        Field field1 = new Field();
        field1.setFarm(farm1);
        field1.setFieldName("North Field");
        fieldRepository.save(field1);

        Field field2 = new Field();
        field2.setFarm(farm2);
        field2.setFieldName("South Field");
        fieldRepository.save(field2);

        // Create and save Season objects
        Season season1 = new Season();
        season1.setSeasonName("Spring");
        seasonRepository.save(season1);

        Season season2 = new Season();
        season2.setSeasonName("Summer");
        seasonRepository.save(season2);
    }
}

