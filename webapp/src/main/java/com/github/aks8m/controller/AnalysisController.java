package com.github.aks8m.controller;

import com.github.aks8m.service.XodusRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 2019-01-19
 * aks8m - https://github.com/aks8m
 */
@CrossOrigin
@RestController
@RequestMapping(value = "analyze")
public class AnalysisController {

    @PostMapping(headers="Accept=application/xml")
    private ResponseEntity<Void> createXodusTree(@RequestBody String xml){

        XodusRepository xodusRepository = new XodusRepository();
        xodusRepository.write();

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "users")
    private String getUser(){
        XodusRepository xodusRepository = new XodusRepository();

        return xodusRepository.read();
    }



}
