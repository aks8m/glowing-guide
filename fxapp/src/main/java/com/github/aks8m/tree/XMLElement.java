package com.github.aks8m.tree;

import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultTreeItem;

import java.util.List;

public interface XMLElement {


    String getElementName();

    void setElementName(String name);

    String getElementValue();

    void setElementValue(String value);


    Result getElementResult();

    void setElementResult(Result result);

    String toString();

}

