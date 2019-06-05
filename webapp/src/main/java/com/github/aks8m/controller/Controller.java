package com.github.aks8m.controller;

import com.github.aks8m.service.AnalysisService;
import com.github.aks8m.service.UnorderedCompareService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;
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
        return this.analysisService.getSourceJSON(new String(contents.getBytes()));
    }

    @PostMapping(value = "/readTarget")
    private String readTarget(@RequestParam(value = "file") MultipartFile contents) throws IOException, ParserConfigurationException, SAXException {
        return this.analysisService.getTargetJSON(new String(contents.getBytes()));
    }

    @GetMapping(value = "/compare")
    private String compareDocuments() {
        UnorderedCompareService compareService = new UnorderedCompareService();
        Gson gson = new Gson();
        compareService.setSourceNode(this.analysisService.getSourceDocumentNode());
        compareService.setTargetNode(this.analysisService.getTargetDocumentNode());
        return gson.toJson(compareService.compare());
    }

    @PostMapping(value = "/compareSection")
    private String compareSection(@RequestBody String payload) {
        UnorderedCompareService compareService = new UnorderedCompareService();
        Gson gson = new Gson();
        JsonObject jObject = new JsonParser().parse(payload).getAsJsonObject();
        JsonObject source = jObject.getAsJsonObject("source");
        JsonObject target = jObject.getAsJsonObject("target");
        compareService.setSourceNode(this.analysisService.getSectonNode(source.toString()));
        compareService.setTargetNode(this.analysisService.getSectonNode(target.toString()));
        return gson.toJson(compareService.compare());
    }



}
