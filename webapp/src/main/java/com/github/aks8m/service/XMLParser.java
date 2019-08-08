package com.github.aks8m.service;

import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 4/29/2019
 *
 * @author kmaulden
 */
public class XMLParser extends DefaultHandler {
    private String xmlString;
    private JSONObject currentObj;
    private JSONObject rootObj;
    private JSONArray currentChildrenArray;
    private Map<JSONObject, JSONObject> childToParentMap = new HashMap<>();
    AtomicInteger counter = null;
    private StringBuilder sb = null;

    XMLParser() {}

    XMLParser(String xmlString, AtomicInteger counter) { this.xmlString = xmlString; this.counter = counter;}

    public String getXmlString() {
        return xmlString;
    }

    public void setXmlString(String xmlString) {
        this.xmlString = xmlString;
    }

    public JSONObject toJSONObject() throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        parser.parse(new InputSource(new StringReader(this.xmlString)), this);

        return this.rootObj;
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        JSONObject jsonObject = new JSONObject();
        JSONArray childrenArray = new JSONArray();
        JSONArray attributeArray = new JSONArray();
        jsonObject.put("name", qName);
        for (int i = 0; i < attributes.getLength(); i++) {
            JSONObject attribute = new JSONObject();
            attribute.put("name", attributes.getQName(i));
            attribute.put("value", attributes.getValue(i));
            attribute.put("id", this.counter.getAndIncrement());
            attribute.put("open", false);
            attribute.put("error", false);
            attributeArray.put(attribute);
        }
        jsonObject.put("children", childrenArray);
        jsonObject.put("attributes", attributeArray);
        jsonObject.put("id", this.counter.getAndIncrement());
        jsonObject.put("open", false);
        jsonObject.put("error", false);
        jsonObject.put("folder",false);

        if (rootObj == null) {
            rootObj = jsonObject;
        } else {
            ((JSONArray) currentObj.get("children")).put(jsonObject);
            currentObj.put("folder",true);
            childToParentMap.put(jsonObject,currentObj);
        }
        currentObj = jsonObject;
        this.sb = new StringBuilder();

    }

    public void characters(char ch[], int start, int length) throws SAXException {
        if (this.sb == null) return;

        String addString = new String(ch, start, length).replace("\n", "").replace("\t", "");
        this.sb.append(addString);
    }

    public void endElement(String uri, String localName,
                           String qName) throws SAXException {

        if (this.sb != null && !this.sb.toString().equals("")) {
            this.currentObj.put("value",this.sb);
        }
        this.sb = null;

        String attributeString = "";
        JSONArray atts = this.currentObj.getJSONArray("attributes");

        for (Object att : atts) {
            attributeString = attributeString + " " + ((JSONObject) att).get("name") + "=" + ((JSONObject) att).get("value") + " ";
        }

        try {
            this.currentObj.put("displayname", "< " + this.currentObj.get("name") + attributeString + ">" + currentObj.get("value"));
        } catch (Exception e) {
            this.currentObj.put("displayname", "< " + this.currentObj.get("name") + attributeString + " >");
        }
        try {
            currentObj = childToParentMap.get(currentObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    }
