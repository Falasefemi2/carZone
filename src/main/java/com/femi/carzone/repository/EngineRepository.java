package com.femi.carzone.repository;

import com.femi.carzone.model.Engine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EngineRepository extends JpaRepository<Engine, Long> {

    Optional<Engine> findByDisplacement(int displacement);

    List<Engine> findByNoOfCyclindersGreaterThan(int count);

    List<Engine> findByDisplacementAndNoOfCyclinders(int displacement, int noOfCyclinders);
}
