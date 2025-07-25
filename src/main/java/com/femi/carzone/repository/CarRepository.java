package com.femi.carzone.repository;

import com.femi.carzone.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByBrand(String brand);

    List<Car> findByBrandAndEngine_Displacement(String brand, int displacement);
}
