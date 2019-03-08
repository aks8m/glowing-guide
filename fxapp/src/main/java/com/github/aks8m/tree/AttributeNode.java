package com.github.aks8m.tree;

import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultTreeItem;

public class AttributeNode extends ResultTreeItem implements XMLElement {

    private Result attributeResult = new Result();
    private String attributeName;
    private String attributeValue;

    public AttributeNode(String attributeName) {
        super(attributeName);
        this.attributeName = attributeName;
    }

    public AttributeNode(String attributeName, String attributeValue) {
        super(attributeName + ": " + attributeValue);
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public AttributeNode(String attributeName, String attributeValue, Result attributeResult) {
        super(attributeName + ": " + attributeValue);
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
        this.attributeResult = attributeResult;
    }

    public Result getElementResult() {
        return attributeResult;
    }

    public void setElementResult(Result result) {
        this.attributeResult = result;
    }

    public String getElementName() {
        return attributeName;
    }

    public void setElementName(String name) {
        this.attributeName = name;
    }

    public String getElementValue() {
        return attributeValue;
    }

    public void setElementValue(String value) {
        this.attributeValue = value;
    }


}
