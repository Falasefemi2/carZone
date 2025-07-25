package com.femi.carzone.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record CarResponse(
        Long id,
        String name,
        String brand,
        String year,
        String fuelType,
        BigDecimal price,
        EngineResponse engine,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

