package com.github.aks8m.compare.tree;
import com.github.aks8m.traversal.MethodType.NodeValueType;

import java.util.*;

import com.github.aks8m.compare.comparisonobject.Comparison;


public class Node {
    private Node parent = null;
    private NodeValueType location;
    private Comparison comparison = null;
    private List<Node> siblings = new ArrayList<Node>();
    private List<Node> children = new ArrayList<Node>();



    public Node() {}

    public Node(NodeValueType location) {
        this.location = location;
    }

    public Node(NodeValueType location, Node parent, Comparison object) {
        this.location = location;
        this.parent = parent;
        this.comparison = object;
    }

    public Node(NodeValueType location, Node parent) {
        this.location = location;
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
        this.location = location;
    }

    public List<Node> getSiblings() { return this.siblings; }

    public NodeValueType getLocation() {
        return this.location;
    }

    public Comparison getComparison() { return this.comparison; }

    public void setComparison(Comparison comparison) {
        this.comparison = comparison;
    }

    public void setLocation(NodeValueType location) {this.location = location; }

    public String toString() { return location != null ? this.location.toString() : ""; }

}
