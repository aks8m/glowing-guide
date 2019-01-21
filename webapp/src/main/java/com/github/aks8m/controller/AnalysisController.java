package com.github.aks8m.controller;

import com.github.aks8m.service.AnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 2019-01-19
 * aks8m - https://github.com/aks8m
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/analyze")
public class AnalysisController {

    @PostMapping(headers="Accept=application/xml")
    private ResponseEntity<Void> createXodusTree(@RequestBody String xmlData){

        AnalysisService analysisService = new AnalysisService();
        boolean parseCompleted = analysisService.analyzeAndStoreAsXodusEntities(xmlData);

        return parseCompleted ? ResponseEntity.accepted().build() : ResponseEntity.badRequest().build();
    }

    @GetMapping(value = "/analysis")
    private String getAllAnalysis(){

        return "";
    }

    @GetMapping(value = "/analysis/{id}")
    private String getAnalysis(@PathVariable String id){

        return "";
    }

    @DeleteMapping(value = "/analysis")
    private ResponseEntity deleteAllAnalysis(){
        return ResponseEntity.noContent().build();
    }

}
