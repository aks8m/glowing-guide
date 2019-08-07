package com.github.aks8m.service;

import com.github.aks8m.model.NodePOJO;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    private JSONObject sourceReturnJSON = null;
    private JSONObject targetReturnJSON = null;

    private String sourceRootID = null;
    private String targetRootID = null;

    public AtomicInteger counter = new AtomicInteger(0);

    public void loadSourceJSON(String sourceString) throws IOException, SAXException, ParserConfigurationException {
        XMLParser parser = new XMLParser(sourceString, this.counter);
        this.sourceDocumentJSON = parser.toJSONObject();
        this.sourceReturnJSON = new JSONObject(this.sourceDocumentJSON.toString());
        this.sourceRootID = this.sourceDocumentJSON.get("id").toString();
//        this.sourceDocumentString = sourceDocumentJSON.toString(4);
    }

    public void loadTargetJSON(String targetString) throws IOException, SAXException, ParserConfigurationException {
        XMLParser parser = new XMLParser(targetString, this.counter);
        this.targetDocumentJSON = parser.toJSONObject();
        this.targetReturnJSON = new JSONObject(this.targetDocumentJSON.toString());
        this.targetRootID = this.targetDocumentJSON.get("id").toString();
//        this.targetDocumentString = targetDocumentJSON.toString(4);
    }


//    public NodePOJO getSectionNode(String section) {
//        NodePOJO rootNode = this.gson.fromJson(section, NodePOJO.class);
//        addParents(rootNode);
//        return rootNode;
//    }

    public NodePOJO getSourceNodePOJO(String id) {
        JSONObject node = findJSONNode(this.sourceDocumentJSON, id);
        NodePOJO rootNode = this.gson.fromJson(node.toString(), NodePOJO.class);
        addParents(rootNode);
        return rootNode;
    }

    public NodePOJO getTargetNodePOJO(String id) {
        JSONObject node = findJSONNode(this.targetDocumentJSON, id);
        NodePOJO rootNode = this.gson.fromJson(node.toString(), NodePOJO.class);
        addParents(rootNode);
        return rootNode;
    }

    private JSONObject findJSONNode(JSONObject node, String id) {
        if (node.get("id").toString().equals(id)) {
            return node;
        } else {
            for (Object jsonObject : (JSONArray) node.get("children")) {
                JSONObject ret;
                ret = findJSONNode((JSONObject) jsonObject, id);
                if (ret != null) {
                    return ret;
                }
            }
        }
        return null;
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

    public String getRootSource() {
        this.sourceReturnJSON.put("children",new JSONArray());
        return this.sourceReturnJSON.toString(4);
    }

    public String getTargetSource() {
        this.targetReturnJSON.put("children",new JSONArray());
        return this.targetReturnJSON.toString(4);
    }


    public String openSourceNode(String id) {
        recursiveOpen(this.sourceReturnJSON, this.sourceDocumentJSON, id,false);
        return this.sourceReturnJSON.toString(4);
    }

    public String openTargetNode(String id) {
        recursiveOpen(this.targetReturnJSON, this.targetDocumentJSON, id, false);
        return this.targetReturnJSON.toString(4);
    }

    private void recursiveOpen(JSONObject display, JSONObject document, String id, boolean forError) {
        if (display.get("id").toString().equals(id)) {
            display.put("open", true);
            if (forError) {
                display.put("error",true);
            }
            display.put("children", getChildrenFromDocument(id, document));
            removeGrandchildren((JSONArray) display.get("children"));
        } else {
            for (Object jsonObject : (JSONArray) display.get("children")) {
                recursiveOpen((JSONObject) jsonObject, document, id, forError);
            }
        }

    }

    //this can be optimized? dont search whole document only from display node down
    private JSONArray getChildrenFromDocument(String id, JSONObject document) {
        if (document.get("id").toString().equals(id)) {
            return new JSONArray(document.get("children").toString());
        } else {
            for (Object obj : (JSONArray) document.get("children")) {
                JSONArray ret = getChildrenFromDocument(id, (JSONObject) obj);
                if (ret != null) {
                    return ret;
                }
            }
        }
        return null;
    }

    private void removeGrandchildren(JSONArray array) {
        for (Object obj : array) {
            ((JSONObject) obj).put("children", new JSONArray());
        }
    }

    public String closeSourceNode(String id) {
        recursiveClose(this.sourceReturnJSON, id);
        return this.sourceReturnJSON.toString(4);
    }

    public String closeTargetNode(String id) {
        recursiveClose(this.targetReturnJSON, id);
        return this.targetReturnJSON.toString(4);
    }

    private void recursiveClose(JSONObject display, String id) {
        if (display.get("id").toString().equals(id)) {
            display.put("children", new JSONArray());
            display.put("open", false);
        } else {
            for (Object jsonObject : (JSONArray) display.get("children")) {
                recursiveClose((JSONObject) jsonObject, id);
            }
        }
    }

    public String openSourceResult(String id) {
        recursiveResultOpen(this.sourceReturnJSON, this.sourceDocumentJSON, id);
        return this.sourceReturnJSON.toString(4);
    }

    public String openTargetResult(String id) {
        List<String> ids = Arrays.asList(id.split(","));
        recursiveResultOpen(this.targetReturnJSON, this.targetDocumentJSON, ids.get(0));

        if (ids.size() > 1) {
            for (int i=1; i<ids.size(); i++) {
                recursiveOpen(this.targetReturnJSON, this.targetDocumentJSON, ids.get(i), true);
            }
        }
        return this.targetReturnJSON.toString(4);
    }

    private void recursiveResultOpen(JSONObject display, JSONObject document, String id) {

        List<String> parentList = new ArrayList<>();
        getIDPath(parentList,document,id);
        openIDPath(display,document,parentList);

    }

    private boolean getIDPath(List<String> parentList, JSONObject document, String id) {
        parentList.add(document.get("id").toString());
        if (document.get("id").toString().equals(id)) {
            return true;
        }
        boolean flag = false;
        for (Object obj : (JSONArray) document.get("children")) {
            if (getIDPath(parentList, (JSONObject) obj, id)) {
                flag = true;
                break;
            }
        }
        if (flag) {
            return true;
        }
        parentList.remove(parentList.size()-1);
        return false;
    }

    private void openIDPath(JSONObject display, JSONObject document, List<String> idPath) {
        if (idPath != null && idPath.size() != 0 && idPath.get(0).equals(document.get("id").toString())) {
            display.put("children",new JSONArray(document.get("children").toString()));
            removeGrandchildren((JSONArray) display.get("children"));
            display.put("open", true);

            if (idPath.size() > 1) {
                idPath.remove(0);
                //get display child
                JSONObject childDisplay = new JSONObject();
                for (Object obj : (JSONArray) display.get("children")) {
                    if (((JSONObject) obj).get("id").toString().equals(idPath.get(0))) {
                        childDisplay = (JSONObject) obj;
                        break;
                    }
                }
                for (Object obj : (JSONArray) document.get("children")) {
                    if (((JSONObject) obj).get("id").toString().equals(idPath.get(0))) {
                        openIDPath(childDisplay, (JSONObject) obj, idPath);
                        break;
                    }
                }
            } else {
                display.put("error", true);
            }
        }
    }


}
