package com.github.aks8m.model;
import com.fasterxml.jackson.annotation.*;

import java.util.List;
import java.util.UUID;


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

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public boolean isOpen() { return open; }

    public void setOpen(boolean open) { this.open = open; }

    public List<NodePOJO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<NodePOJO> attributes) {
        this.attributes = attributes;
    }


    private List<NodePOJO> children;
    private List<NodePOJO> attributes;
    private String name;
    private String value;
    private String id;
    private boolean open;

    public NodePOJO getParent() {
        return parent;
    }

    public void setParent(NodePOJO parent) {
        this.parent = parent;
    }

    private NodePOJO parent;
}
