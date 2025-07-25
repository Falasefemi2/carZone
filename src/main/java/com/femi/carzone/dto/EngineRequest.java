package com.femi.carzone.dto;

import lombok.Builder;

import jakarta.validation.constraints.*;

@Builder
public record EngineRequest(
        @Min(value = 1, message = "Displacement must be greater than 0")
        int displacement,

        @Min(value = 1, message = "Number of cylinders must be greater than 0")
        int noOfCyclinders,

        @Min(value = 1, message = "Car range must be greater than 0")
        int carRange
) {}
