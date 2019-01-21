package com.github.aks8m.controller;

import com.github.aks8m.model.Analysis;
import com.github.aks8m.service.AnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 2019-01-19
 * aks8m - https://github.com/aks8m
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/analysis")
public class AnalysisController {

    private final AnalysisService analysisService = new AnalysisService();

    @PostMapping(value = "/{name}", headers="Content-Type=text/xml")
    private Analysis performAnalysis(@PathVariable String name, @RequestBody String xmlData){
        return analysisService.performAnalysis(name, xmlData);
    }

    @GetMapping
    private ResponseEntity<List<Analysis>> getAllAnalysis(){
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<Analysis> getAnalysis(@PathVariable String id){
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    private ResponseEntity deleteAllAnalysis(){
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity deleteAnalysis(@PathVariable String id){
        return ResponseEntity.noContent().build();
    }

}
