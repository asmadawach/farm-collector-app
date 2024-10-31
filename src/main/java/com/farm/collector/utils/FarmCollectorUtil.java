package com.farm.collector.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class FarmCollectorUtil {
    public  <T> T getEntityOrThrow(JpaRepository<T, Long> repository, Long id, String errorMessage) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(errorMessage + id));
    }
}
