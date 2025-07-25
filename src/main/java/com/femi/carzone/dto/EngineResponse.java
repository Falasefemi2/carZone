package com.femi.carzone.dto;

import lombok.Builder;

@Builder
public record EngineResponse(
        Long id,
        int displacement,
        int noOfCyclinders,
        int carRange
) {}
