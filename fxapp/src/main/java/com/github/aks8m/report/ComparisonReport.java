package com.github.aks8m.report;

import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultType;
import com.github.aks8m.tree.ElementNode;
import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ComparisonReport {

    private List<Result> results;

    private TreeItem<String> sourceRoot;

    private TreeItem<String> targetRoot;
    public ComparisonReport(){
        this.results = new ArrayList<Result>();
    }

    public void addResults(List<Result> mismatches) {this.results.addAll(mismatches);}

    public List<Result> getValueMismatches() {
        return results.stream().filter(res -> res.getResultType().equals(ResultType.VALUEMISMATCH)).collect(Collectors.toList());
    }

    public List<Result> getAttributeMismatches() {
        return results.stream().filter(res -> res.getResultType().equals(ResultType.ATTRIBUTEMISMATCH)).collect(Collectors.toList());
    }

    public List<Result> getSectionMatchNotFound() {
        return results.stream().filter(res -> res.getResultType().equals(ResultType.SECTIONMATCHNOTFOUND)).collect(Collectors.toList());
    }

    public List<Result> getAllMismatches() {
        return results;
    }

    public TreeItem<String> getSourceRoot() {
        return sourceRoot;
    }

    public void setSourceRoot(TreeItem<String> sourceRoot) {
        this.sourceRoot = sourceRoot;
    }

    public TreeItem<String> getTargetRoot() {
        return targetRoot;
    }

    public void setTargetRoot(TreeItem<String> targetRoot) {
        this.targetRoot = targetRoot;
    }
}
