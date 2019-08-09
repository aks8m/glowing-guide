package com.github.aks8m.controller;

import com.github.aks8m.service.AnalysisService;
import com.github.aks8m.service.UnorderedCompareService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * 2019-01-19
 * aks8m - https://github.com/aks8m
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/analysis")
public class Controller {

    private AnalysisService analysisService = new AnalysisService();

    @PostMapping(value = "/readSource")
    private String readSource(@RequestParam(value = "file") MultipartFile contents) throws IOException, ParserConfigurationException, SAXException {
        this.analysisService.loadSourceJSON(new String(contents.getBytes()));
        return this.analysisService.getRootSource();
    }

    @PostMapping(value = "/readTarget")
    private String readTarget(@RequestParam(value = "file") MultipartFile contents) throws IOException, ParserConfigurationException, SAXException {
        this.analysisService.loadTargetJSON(new String(contents.getBytes()));
        return this.analysisService.getTargetSource();
    }

    @GetMapping(value = "/openSourceNode/{id}")
    private String openSourceNode(@PathVariable String id) {
        return this.analysisService.openSourceNode(id);
    }

    @GetMapping(value = "/openTargetNode/{id}")
    private String openTargetNode(@PathVariable String id) {
        return this.analysisService.openTargetNode(id);
    }

    @GetMapping(value = "/closeSourceNode/{id}")
    private String closeSourceNode(@PathVariable String id) {
        return this.analysisService.closeSourceNode(id);
    }

    @GetMapping(value = "/closeTargetNode/{id}")
    private String closeTargetNode(@PathVariable String id) {
        return this.analysisService.closeTargetNode(id);
    }

    @GetMapping(value = "/openSourceResult/{id}")
    private String openSourceResult(@PathVariable String id) {
        return this.analysisService.openSourceResult(id);
    }

    @GetMapping(value = "/resolveSourceSection/{id}")
    private String resolveSourceSection(@PathVariable String id) {
        return this.analysisService.resolveSourceSection(id);
    }

    @GetMapping(value = "/resolveTargetSection/{id}")
    private String resolveTargetSection(@PathVariable String id) {
        return this.analysisService.resolveTargetSection(id);
    }

    @GetMapping(value = "/openTargetResult/{id}")
    private String openTargetResult(@PathVariable String id) {
        return this.analysisService.openTargetResult(id);
    }

    @GetMapping(value = "/compare/{sourceID}/{targetID}")
    private String compare(@PathVariable String sourceID, @PathVariable String targetID) {
        UnorderedCompareService compareService = new UnorderedCompareService();
        Gson gson = new Gson();

        compareService.setSourceNode(this.analysisService.getSourceNodePOJO(sourceID));
        compareService.setTargetNode(this.analysisService.getTargetNodePOJO(targetID));

        return gson.toJson(compareService.compare());
    }



}
