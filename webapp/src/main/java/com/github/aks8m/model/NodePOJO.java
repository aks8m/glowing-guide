package com.github.aks8m.model;
import com.fasterxml.jackson.annotation.*;

import java.util.List;


/**
 * 5/1/2019
 *
 * @author kmaulden
 */
public class NodePOJO {
    public List<NodePOJO> getChildren() {
        return children;
    }

    public void setChildren(List<NodePOJO> children) {
        this.children = children;
    }

    public int getAttribute() {
        return attribute;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
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

    private List<NodePOJO> children;
    private int attribute;
    private String name;
    private String value;

}
