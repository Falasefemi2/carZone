package com.femi.carzone.service;

import com.femi.carzone.dto.CarRequest;
import com.femi.carzone.dto.EngineMapper;
import com.femi.carzone.dto.EngineRequest;
import com.femi.carzone.dto.EngineResponse;
import com.femi.carzone.model.Engine;
import com.femi.carzone.repository.EngineRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EngineService {

    private final EngineRepository engineRepository;

    public EngineResponse createEngine(EngineRequest engineRequest) {
        Engine engine = EngineMapper.toEntity(engineRequest);
        engine =  engineRepository.save(engine);
        return EngineMapper.toResponse(engine);
    }

    public EngineResponse updateEngine(Long id, EngineRequest engineRequest) {
        Engine existingEngine = engineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Engine not found"));

        existingEngine.setCarRange(engineRequest.carRange());
        existingEngine.setNoOfCyclinders(engineRequest.noOfCyclinders());
        existingEngine.setDisplacement(engineRequest.displacement());

        Engine updatedEngine = engineRepository.save(existingEngine);
        return EngineMapper.toResponse(updatedEngine);
    }


    public void deleteEngine(Long id) {
        engineRepository.deleteById(id);
    }

    public EngineResponse findEngineByDisplacement(int displacement) {
        Engine engine = engineRepository.findByDisplacement(displacement)
                .orElseThrow(() -> new EntityNotFoundException("No engine found"));

        return EngineMapper.toResponse(engine);
    }

    public List<EngineResponse> getEnginesWithMoreThanXNoOfCyclinders(int count) {
        return engineRepository.findByNoOfCyclindersGreaterThan(count)
                .stream()
                .map(EngineMapper::toResponse)
                .toList();
    }

    public List<EngineResponse> searchEngines(Integer displacement, Integer minCylinders) {
        List<Engine> engines = engineRepository.findAll();

        return engines.stream()
                .filter(engine -> displacement == null || engine.getDisplacement() == displacement)
                .filter(engine -> minCylinders == null || engine.getNoOfCyclinders() > minCylinders)
                .map(EngineMapper::toResponse)
                .toList();
    }


    public List<EngineResponse> getAllEngines() {
        return engineRepository.findAll().stream()
                .map(EngineMapper::toResponse)
                .toList();
    }

    public List<EngineResponse> findByDisplacementAndCylinder(int displacement, int noOfCyclinders) {
        return engineRepository.findByDisplacementAndNoOfCyclinders(displacement, noOfCyclinders).stream()
                .map(EngineMapper::toResponse)
                .toList();
    }

    public EngineResponse getEngineById(Long id) {
        Engine engine = engineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No engine found"));

        return EngineMapper.toResponse(engine);
    }

}
