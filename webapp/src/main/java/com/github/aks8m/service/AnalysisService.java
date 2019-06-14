package com.github.aks8m.service;

import com.github.aks8m.model.NodePOJO;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * 2019-01-20
 * aks8m - https://github.com/aks8m
 */
public class AnalysisService {

    Gson gson = new Gson();

    private String sourceDocumentString = null;
    private String targetDocumentString = null;

    private JSONObject sourceDocumentJSON = null;
    private JSONObject targetDocumentJSON = null;


    public String getSourceJSON(String sourceString) throws IOException, SAXException, ParserConfigurationException {
        XMLParser parser = new XMLParser(sourceString);
        this.sourceDocumentJSON = parser.toJSONObject();
        this.sourceDocumentString = sourceDocumentJSON.toString(4);
        return this.sourceDocumentString;
    }

    public String getTargetJSON(String targetString) throws IOException, SAXException, ParserConfigurationException {
        XMLParser parser = new XMLParser(targetString);
        this.targetDocumentJSON = parser.toJSONObject();
        this.targetDocumentString = targetDocumentJSON.toString(4);
        return this.targetDocumentString;
    }


    public NodePOJO getSectonNode(String section) {
        NodePOJO rootNode = this.gson.fromJson(section, NodePOJO.class);
        addParents(rootNode);
        return rootNode;
    }

    private void addParents(NodePOJO rootNode) {
        if (rootNode.getChildren() != null) {
            for (NodePOJO node : rootNode.getChildren()) {
                node.setParent(rootNode);
                addParents(node);
            }
        }
        if (rootNode.getAttributes() != null) {
            for (NodePOJO node : rootNode.getAttributes()) {
                node.setParent(rootNode);
            }
        }
    }

}
