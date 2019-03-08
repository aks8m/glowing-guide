package com.github.aks8m.tree;

import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultTreeItem;

public class ElementNode extends ResultTreeItem implements XMLElement {

    private String name;
    private String value;
    private Result result = new Result();


    public ElementNode(String name) { super(name); this.name = name; }

    public ElementNode(String name, String value) { super(name+": "+value); this.name = name; this.value = value; }


    public String getElementName() { return this.name; }

    public void setElementName(String name) { this.name = name; }

    public String getElementValue() {
        return this.value;
    }

    public void setElementValue(String value) { this.value = value; }


    public Result getElementResult() { return this.result; }

    public void setElementResult(Result result) { this.result = result; }

    public String toString() { return "Name: " + this.name + "   Value: " + this.value; }

}
