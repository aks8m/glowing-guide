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

    XMLParser() {}

    XMLParser(String xmlString) { this.xmlString = xmlString; }

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
//            attribute.put("attribute", 1);
            attribute.put("id", UUID.randomUUID());
            attribute.put("open", false);
            attribute.put("error", false);
            attributeArray.put(attribute);
        }
        jsonObject.put("children", childrenArray);
        jsonObject.put("attributes", attributeArray);
//        jsonObject.put("attribute", 0);
        jsonObject.put("id", UUID.randomUUID());
        jsonObject.put("open", false);
        jsonObject.put("error", false);

        if (rootObj == null) {
            rootObj = jsonObject;
        } else {
            ((JSONArray) currentObj.get("children")).put(jsonObject);
            childToParentMap.put(jsonObject,currentObj);
        }
        currentObj = jsonObject;

    }

    public void characters(char ch[], int start, int length) throws SAXException {
        String addString = new String(ch, start, length).replace("\n", "").replace("\t", "").trim();
        if (!addString.equals("")) {
            currentObj.put("value", addString);
        }
    }

    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        String attributeString = "";
        JSONArray atts = currentObj.getJSONArray("attributes");
        for (Object att : atts) {
            attributeString = attributeString + " " + ((JSONObject) att).get("name") + "=" + ((JSONObject) att).get("value") + " ";
        }

        try {
            currentObj.put("displayname", "< " + currentObj.get("name") + attributeString + ">" + currentObj.get("value"));
        } catch (Exception e) {
            currentObj.put("displayname", "< " + currentObj.get("name") + attributeString + " >");
        }
        try {
            currentObj = childToParentMap.get(currentObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    }
