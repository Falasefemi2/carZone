package com.femi.carzone.dto;


import com.femi.carzone.model.Engine;

public class EngineMapper {

    public static Engine toEntity(EngineRequest request) {
        if (request == null) return null;

        return Engine.builder()
                .displacement(request.displacement())
                .noOfCyclinders(request.noOfCyclinders())
                .carRange(request.carRange())
                .build();
    }

    public static EngineResponse toResponse(Engine engine) {
        if (engine == null) return null;

        return EngineResponse.builder()
                .id(engine.getId())
                .displacement(engine.getDisplacement())
                .noOfCyclinders(engine.getNoOfCyclinders())
                .carRange(engine.getCarRange())
                .build();
    }
}
