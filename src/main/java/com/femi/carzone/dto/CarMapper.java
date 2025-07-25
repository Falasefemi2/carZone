package com.femi.carzone.dto;

import com.femi.carzone.model.Car;
import com.femi.carzone.model.Engine;

public class CarMapper {

    public static Car toEntity(CarRequest dto) {
        if (dto == null) return null;

        Engine engine = Engine.builder()
                .displacement(dto.engine().displacement())
                .noOfCyclinders(dto.engine().noOfCyclinders())
                .carRange(dto.engine().carRange())
                .build();

        return Car.builder()
                .name(dto.name())
                .year(dto.year())
                .brand(dto.brand())
                .fuelType(dto.fuelType())
                .price(dto.price())
                .engine(engine)
                .build();
    }

    public static CarResponse toResponse(Car car) {
        if (car == null) return null;

        Engine engine = car.getEngine();

        EngineResponse engineResponse = EngineResponse.builder()
                .id(engine.getId())
                .displacement(engine.getDisplacement())
                .noOfCyclinders(engine.getNoOfCyclinders())
                .carRange(engine.getCarRange())
                .build();

        return CarResponse.builder()
                .id(car.getId())
                .name(car.getName())
                .year(car.getYear())
                .brand(car.getBrand())
                .fuelType(car.getFuelType())
                .price(car.getPrice())
                .engine(engineResponse)
                .createdAt(car.getCreatedAt())
                .updatedAt(car.getUpdatedAt())
                .build();
    }
}
