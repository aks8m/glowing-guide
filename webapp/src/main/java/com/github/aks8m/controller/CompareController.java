package com.github.aks8m.controller;

import org.springframework.web.bind.annotation.*;

/**
 * 2019-01-20
 * aks8m - https://github.com/aks8m
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/compare")
public class CompareController {

    @GetMapping(value = "/{sourceAnalysisID}/to/{targetAnalysisID}")
    private void compareSourceAndTargetAnalysis(@PathVariable String sourceAnalysisID,
                                                @PathVariable String targetAnalysisID){

    }

}
