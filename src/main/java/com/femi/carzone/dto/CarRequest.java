package com.femi.carzone.dto;

import com.femi.carzone.model.Engine;
import jakarta.validation.Valid;
import lombok.Builder;

import java.math.BigDecimal;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Builder
public record CarRequest(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Year is required")
        String year,

        @NotBlank(message = "Brand is required")
        String brand,

        @NotBlank(message = "Fuel type is required")
        String fuelType,

        @NotNull(message = "Engine is required")
        @Valid
        EngineRequest engine,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        BigDecimal price
) {}
