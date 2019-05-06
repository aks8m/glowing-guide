package com.github.aks8m.service;

import com.github.aks8m.model.Analysis;
import com.github.aks8m.model.NodePOJO;
import com.github.aks8m.repository.XodusRepository;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.XML;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * 2019-01-20
 * aks8m - https://github.com/aks8m
 */
public class AnalysisService {

//    private final XodusRepository xodusRepository;
//    private final ArrayList<Long> parentIDs = new ArrayList<>();
//    private boolean isNewParent = false;

    private String sourceString = null;
    private String targetString = null;

    private JSONObject sourceJSON = null;
    private JSONObject targetJSON = null;

//    public AnalysisService() {
//        xodusRepository = new XodusRepository();
//    }

//    public Analysis performAnalysis(String name, String xmlData){
//        final LocalDateTime localDateTime = LocalDateTime.now();
//        final String id = UUID.randomUUID().toString();
//        final Analysis analysis = new Analysis(name, id, localDateTime);
////        this.xodusRepository.beginTransactions(analysis);
//
//        try {
//            SAXParserFactory.newInstance().newSAXParser()
//                    .parse(new InputSource(new StringReader(xmlData)),
//                            new DefaultHandler(){
//                                @Override
//                                public void startDocument() throws SAXException {
//                                    super.startDocument();
//                                }
//
//                                @Override
//                                public void endDocument() throws SAXException {
//                                    super.endDocument();
//                                }
//
//                                @Override
//                                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//                                   super.startElement(uri,localName,qName,attributes);
//
//                                   xodusRepository.addChild(qName);
//                                   for(int i = 0; i < attributes.getLength(); i++){
//                                       xodusRepository.addProperty(attributes.getQName(i), attributes.getValue(i));
//                                   }
//                                   parentIDs.add(xodusRepository.getCurrentEntityID());
//                                }
//
//                                @Override
//                                public void endElement(String uri, String localName, String qName) throws SAXException {
//                                    super.endElement(uri, localName, qName);
//                                    //detect if start == end, then new parent, but need to remember the current parent
//                                }
//
//                                @Override
//                                public void characters(char[] ch, int start, int length) throws SAXException {
//                                    StringBuilder stringBuilder = new StringBuilder();
//                                    stringBuilder.append(new String(ch, start, length));
//
//                                    if(!stringBuilder.toString().contains("\n")){//Has valid value
//                                        xodusRepository.addProperty("value", stringBuilder.toString());
//                                    }
//                                }
//
//                            });
//
//        }catch (SAXException | ParserConfigurationException | IOException exception){
//            exception.printStackTrace();
//        }
//
//        this.xodusRepository.endTransactions();
//        return analysis;
//    }

    public String getSourceJSON(String sourceString) throws IOException, SAXException, ParserConfigurationException {
        XMLParser parser = new XMLParser(sourceString);
        this.sourceJSON = parser.toJSONObject();
        this.sourceString = sourceJSON.toString(4);
        return this.sourceString;
    }

    public String getTargetJSON(String targetString) throws IOException, SAXException, ParserConfigurationException {
        XMLParser parser = new XMLParser(targetString);
        this.targetJSON = parser.toJSONObject();
        this.targetString = targetJSON.toString(4);
        return this.targetString;
    }

    public String getSourceString() {
        return this.sourceString;
    }

    public String getTargetString() {
        return this.targetString;
    }

    public NodePOJO getSourceNode() {
        Gson gson = new Gson();
        return gson.fromJson(this.sourceJSON.toString(), NodePOJO.class);
    }

    public NodePOJO getTargetNode() {
        Gson gson = new Gson();
        return gson.fromJson(this.targetJSON.toString(), NodePOJO.class);
    }

}
