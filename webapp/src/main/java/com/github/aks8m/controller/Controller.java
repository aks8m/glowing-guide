package com.github.aks8m.controller;

import com.github.aks8m.model.NodePOJO;
import com.github.aks8m.model.result.Result;
import com.github.aks8m.service.AnalysisService;
import com.github.aks8m.service.CompareService;
import com.github.aks8m.service.UnorderedCompareService;
import com.google.gson.Gson;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 2019-01-19
 * aks8m - https://github.com/aks8m
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/analysis")
public class Controller {

    private AnalysisService analysisService = new AnalysisService();
//    private CompareService compareService = new CompareService();

//    @PostMapping(value = "/{name}", headers="Content-Type=text/xml")
//    private Analysis performAnalysis(@PathVariable String name, @RequestBody String xmlData){
//        return analysisService.performAnalysis(name, xmlData);
//    }

//    @PostMapping(value = "/readSource")
//    private String readSource(@RequestBody String contents) throws IOException, ParserConfigurationException, SAXException {
//        String decodedContents = URLDecoder.decode(contents, "UTF-8");
//        return this.analysisService.getSourceJSON(decodedContents);
//    }

//    @PostMapping(value = "/readTarget")
//    private String readTarget(@RequestBody String contents) throws IOException, ParserConfigurationException, SAXException {
//        String decodedContents = URLDecoder.decode(contents, "UTF-8");
//        return this.analysisService.getTargetJSON(decodedContents);
//    }

    @PostMapping(value = "/readSource")
    private String readSource(@RequestParam(value = "file") MultipartFile contents) throws IOException, ParserConfigurationException, SAXException {
        return this.analysisService.getSourceJSON(new String(contents.getBytes()));

    }


    @PostMapping(value = "/readTarget")
    private String readTarget(@RequestParam(value = "file") MultipartFile contents) throws IOException, ParserConfigurationException, SAXException {
        return this.analysisService.getTargetJSON(new String(contents.getBytes()));

    }

    @PostMapping(value = "/hello")
    private String parseFile() {
        return "Welcome to Spring Boot backend!";
    }

    @GetMapping(value = "/compare")
//    @ResponseBody
    private String compareDocuments() {
//        CompareService compareService = new CompareService();
//
//        Gson gson = new Gson();
//        System.out.println("Compared on back end");
//        compareService.setSourceNode(this.analysisService.getSourceNode());
//        compareService.setTargetNode(this.analysisService.getTargetNode());
////        System.out.println("SOURCENODE: " + this.analysisService.getSourceNode().getId());
////        System.out.println("TARGETNODE: " + this.analysisService.getTargetNode().getId());
//
//        return gson.toJson(compareService.compare());

        UnorderedCompareService compareService = new UnorderedCompareService();

        Gson gson = new Gson();
        System.out.println("Compared on back end");
        compareService.setSourceNode(this.analysisService.getSourceNode());
        compareService.setTargetNode(this.analysisService.getTargetNode());
//        System.out.println("SOURCENODE: " + this.analysisService.getSourceNode().getId());
//        System.out.println("TARGETNODE: " + this.analysisService.getTargetNode().getId());

        return gson.toJson(compareService.compare());

    }



//    @GetMapping
//    private ResponseEntity<List<Analysis>> getAllAnalysis(){
//        return ResponseEntity.ok(new ArrayList<>());
//    }
//
//    @GetMapping(value = "/{id}")
//    private ResponseEntity<Analysis> getAnalysis(@PathVariable String id){
//        return ResponseEntity.ok(null);
//    }
//
//    @DeleteMapping
//    private ResponseEntity deleteAllAnalysis(){
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping(value = "/{id}")
//    private ResponseEntity deleteAnalysis(@PathVariable String id){
//        return ResponseEntity.noContent().build();
//    }

}
