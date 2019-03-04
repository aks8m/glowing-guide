package com.github.aks8m.tree;
import com.github.aks8m.report.result.ResultTreeItem;
import com.github.aks8m.report.result.Result;

import java.util.*;

public class ParserNode {
    private ParserNode parent = null;

    private String name;
    private String value = null;
    private List<ParserNode> children = new ArrayList<ParserNode>();
    private List<ParserNode> attributes = new ArrayList<ParserNode>();

    private Result result = new Result();

    private ResultTreeItem resultTreeItem = null;

    public ParserNode(String name) {
        this.name = name;
    }

    public ParserNode(String name, String value) {
        this.name = name;
        this.value = value;
    }


    public void addChild(ParserNode parserNode) {
        this.children.add(parserNode);
        parserNode.setParent(this);
    }


    public void addAttribute(ParserNode attributes) {
        this.attributes.add(attributes);
        attributes.setParent(this);
    }

    public List<ParserNode> getAttributes() {
        return this.attributes;
    }

    public ResultTreeItem getResultTreeItem() { return this.resultTreeItem; }

    public void addResultTreeItem(ResultTreeItem resultTreeItem) { this.resultTreeItem = resultTreeItem; }

    public ParserNode getParent() {
        return parent;
    }

    public void setParent(ParserNode parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ParserNode> getChildren() { return this.children; }

    public void setChildren(List<ParserNode> children) {
        this.children = children;
    }

    public Result getResult() { return result; }

    public void setResult(Result result) { this.result = result; }

    public String toString() { return "Name: " + this.name + "   Value: " + this.value; }

}
