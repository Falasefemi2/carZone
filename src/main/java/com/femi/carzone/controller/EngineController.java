package com.femi.carzone.controller;

import com.femi.carzone.dto.ApiResponse;
import com.femi.carzone.dto.EngineRequest;
import com.femi.carzone.dto.EngineResponse;
import com.femi.carzone.service.EngineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/engines")
@RequiredArgsConstructor
public class EngineController {

    private final EngineService engineService;

    @PostMapping
    public ResponseEntity<ApiResponse<EngineResponse>> create(@Valid @RequestBody EngineRequest engineRequest) {
        EngineResponse engineResponse = engineService.createEngine(engineRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Engine created successfully", engineResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EngineResponse>> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody EngineRequest engineRequest) {

        EngineResponse engineResponse = engineService.updateEngine(id, engineRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Engine updated successfully", engineResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable("id") Long id) {
        engineService.deleteEngine(id);
        return ResponseEntity.ok(ApiResponse.success("Engine deleted successfully", null));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<EngineResponse>>> searchEngines(
            @RequestParam(value = "displacement", required = false) Integer displacement,
            @RequestParam(value = "minCylinders", required = false) Integer minCylinders) {

        List<EngineResponse> engines = engineService.searchEngines(displacement, minCylinders);
        return ResponseEntity.ok(ApiResponse.success("Engines found", engines));
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<EngineResponse>>> getAllEngines() {
        List<EngineResponse> engines = engineService.getAllEngines();
        return ResponseEntity.ok(ApiResponse.success("All engines retrieved", engines));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EngineResponse>> getEngineById(@PathVariable("id") Long id) {
        EngineResponse engine = engineService.getEngineById(id);
        return ResponseEntity.ok(ApiResponse.success("Engine retrieved", engine));
    }

    @GetMapping("/by-displacement-and-cylinder")
    public ResponseEntity<ApiResponse<List<EngineResponse>>> getByDisplacementAndCylinder(
            @RequestParam int displacement,
            @RequestParam int noOfCyclinders) {

        List<EngineResponse> results = engineService.findByDisplacementAndCylinder(displacement, noOfCyclinders);
        return ResponseEntity.ok(ApiResponse.success("Engines found", results));
    }
}
