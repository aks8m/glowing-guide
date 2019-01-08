package com.github.aks8m.compare.tree;
import com.github.aks8m.traversal.MethodType.NodeValueType;

import java.util.*;

import com.github.aks8m.compare.comparisonobject.Comparison;


public class Node {
    private Node parent = null;
    private NodeValueType locationType;
    private Comparison comparison = null;
    private List<Node> siblings = new ArrayList<Node>();
    private List<Node> children = new ArrayList<Node>();



    public Node() {}

    public Node(NodeValueType locationType) {
        this.locationType = locationType;
    }

    public Node(NodeValueType locationType, Node parent, Comparison object) {
        this.locationType = locationType;
        this.parent = parent;
        this.comparison = object;
    }

    public Node(NodeValueType locationType, Node parent) {
        this.locationType = locationType;
        this.parent = parent;
    }

    public Node addChild(Node node) {
        this.children.add(node);
        return node;
    }

    public List<Node> getChildren() { return this.children; }


    public void addSiblings(List<Node> siblings) {
        this.siblings.addAll(siblings);
    }

    public void addComparison(Comparison comparison) {
        this.comparison = comparison;
    }

    public void addLocation(NodeValueType location) {
        this.locationType = location;
    }

    public List<Node> getSiblings() { return this.siblings; }

    public NodeValueType getLocationType() {
        return this.locationType;
    }

    public Comparison getComparison() { return this.comparison; }

    public void setComparison(Comparison comparison) {
        this.comparison = comparison;
    }

    public void setLocationType(NodeValueType locationType) {this.locationType = locationType; }

    public String toString() { return locationType != null ? this.locationType.toString() : ""; }

}
