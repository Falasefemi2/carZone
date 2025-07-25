package com.femi.carzone.service;

import com.femi.carzone.GlobalExceptionHandler;
import com.femi.carzone.dto.CarMapper;
import com.femi.carzone.dto.CarRequest;
import com.femi.carzone.dto.CarResponse;
import com.femi.carzone.dto.EngineRequest;
import com.femi.carzone.model.Car;
import com.femi.carzone.model.Engine;
import com.femi.carzone.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public CarResponse createCar(CarRequest carRequest) {
        Car car = CarMapper.toEntity(carRequest);
        car = carRepository.save(car);
        return CarMapper.toResponse(car);
    }

    public CarResponse updateCar(Long id, CarRequest carRequest) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));

        existingCar.setName(carRequest.name());
        existingCar.setYear(carRequest.year());
        existingCar.setBrand(carRequest.brand());
        existingCar.setFuelType(carRequest.fuelType());
        existingCar.setPrice(carRequest.price());

        EngineRequest engineDto = carRequest.engine();
        Engine engine = Engine.builder()
                .displacement(engineDto.displacement())
                .noOfCyclinders(engineDto.noOfCyclinders())
                .carRange(engineDto.carRange())
                .build();

        existingCar.setEngine(engine);

        Car updated = carRepository.save(existingCar);
        return CarMapper.toResponse(updated);
    }


    public List<CarResponse> getCarsByBrand(String brand) {
        return carRepository.findByBrand(brand).stream()
                .map(CarMapper::toResponse)
                .toList();
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public List<CarResponse> getAllCars() {
        return carRepository.findAll().stream()
                .map(CarMapper::toResponse)
                .toList();
    }

    public CarResponse getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));

        return CarMapper.toResponse(car);
    }
}
