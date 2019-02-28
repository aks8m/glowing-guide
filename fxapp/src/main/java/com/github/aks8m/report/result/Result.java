package com.github.aks8m.report.result;

import com.github.aks8m.tree.ParserNode;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private SimpleBooleanProperty isSelected = new SimpleBooleanProperty(false);
    private ResultType resultType;
    private String output;

    private List<ParserNode> sourceNodes = new ArrayList<>();
    private List<ParserNode> targetNodes = new ArrayList<>();

    public Result(String output) {
        this.output = output;
    }

    public Result(String output, ResultType resultType) { this.output = output; this.resultType = resultType; }

    public Result() { }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public List<ParserNode> getSourceNodes() { return sourceNodes; }

    public void addSourceNode(ParserNode sourceNodes) { this.sourceNodes.add(sourceNodes); }

    public List<ParserNode> getTargetNodes() { return targetNodes; }

    public void addTargetNodes(ParserNode targetNodes) { this.targetNodes.add(targetNodes); }

    public ResultType getResultType() { return resultType; }

    public boolean isIsSelected() {
        return isSelected.get();
    }

    public SimpleBooleanProperty isSelectedProperty() {
        return isSelected;
    }

    @Override
    public String toString() {
        return this.output;
    }
}
