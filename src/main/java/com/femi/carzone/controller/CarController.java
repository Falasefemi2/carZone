package com.femi.carzone.controller;

import com.femi.carzone.dto.ApiResponse;
import com.femi.carzone.dto.CarRequest;
import com.femi.carzone.dto.CarResponse;
import com.femi.carzone.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<ApiResponse<CarResponse>> createCar(@Valid @RequestBody CarRequest carRequest) {
        CarResponse car = carService.createCar(carRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Car created successfully", car));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CarResponse>> updateCar(
            @PathVariable("id") Long id,
            @Valid @RequestBody CarRequest carRequest) {

        CarResponse car = carService.updateCar(id, carRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Car updated successfully", car));
    }


    @GetMapping("/by-brand")
    public ResponseEntity<ApiResponse<List<CarResponse>>> getCarsByBrand(@RequestParam("brand") String brand) {
        List<CarResponse> cars = carService.getCarsByBrand(brand);
        return ResponseEntity.ok(ApiResponse.success("Cars retrieved for brand: " + brand, cars));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok(ApiResponse.success("Car deleted successfully", null));
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<CarResponse>>> getAllCars() {
        List<CarResponse> cars = carService.getAllCars();
        return ResponseEntity.ok(ApiResponse.success("All cars retrieved successfully", cars));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CarResponse>> getCarById(@PathVariable("id") Long id) {
        CarResponse car = carService.getCarById(id);
        return ResponseEntity.ok(ApiResponse.success("Car retrieved successfully", car));
    }
}

