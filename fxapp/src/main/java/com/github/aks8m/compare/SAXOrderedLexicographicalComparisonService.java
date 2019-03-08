package com.github.aks8m.compare;

import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultTreeItem;
import com.github.aks8m.report.result.ResultType;
import com.github.aks8m.tree.AttributeNode;
import com.github.aks8m.tree.ElementNode;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.scene.control.TreeItem;

import java.util.*;
import java.util.stream.Collectors;

public class SAXOrderedLexicographicalComparisonService extends ComparisonService {

    TreeItem<String> sourceNode = null;
    TreeItem<String> targetNode = null;
    private List<Result> resultList = new ArrayList<>();

    public void setSourceNode(TreeItem<String> sourceNode) {
        this.sourceNode = sourceNode;
    }

    public void setTargetNode(TreeItem<String> targetNode) {
        this.targetNode = targetNode;
    }

    public SAXOrderedLexicographicalComparisonService() {
    }

    public SAXOrderedLexicographicalComparisonService(ElementNode sourceNode, ElementNode targetNode) {
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
    }


    @Override
    protected Task<List<Result>> createTask() {
        return new Task<List<Result>>() {

            @Override
            protected List<Result> call() throws Exception {

                if (sourceNode.getValue().equals(targetNode.getValue())) {
                    breadthFirstOrderedSearch(sourceNode, targetNode);
                } else {
                    resultList.add(new Result("Source and Target Documents did not have a matching root document type", ResultType.SECTIONMATCHNOTFOUND));
                }
                return resultList;
            }

        };
    }

    private void breadthFirstOrderedSearch(TreeItem<String> source, TreeItem<String> target) {
        Queue<TreeItem<String>> sourceQueue = new ArrayDeque<>();
        Queue<TreeItem<String>> targetQueue = new ArrayDeque<>();

        sourceQueue.add(source);
        targetQueue.add(target);

        while (!sourceQueue.isEmpty()) {

            if (targetQueue.isEmpty()) {
                this.resultList.add(new Result("SECTION MISMATCH: Target document had no such element", ResultType.SECTIONMATCHNOTFOUND));
            } else {
                TreeItem<String> sourceNode = sourceQueue.remove();
                TreeItem<String> targetNode = null;
                try {
                    targetNode = targetQueue.remove();
                } catch (Exception e) {
                    this.resultList.add(new Result("SECTION MISMATCH: Target document had no such element", ResultType.SECTIONMATCHNOTFOUND));
                }

                try {
                    //compare node values
                    compareNodeValues(sourceNode, targetNode);

                    //compare node attributes
                    compareNodeAttributes(sourceNode, targetNode);

                    sourceQueue.addAll(getChilds(sourceNode));
                    targetQueue.addAll(getChilds(targetNode));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }



        }

    }

    private void compareNodeValues(TreeItem<String> sourceNode, TreeItem<String> targetNode) {
        ElementNode sNode = (ElementNode) sourceNode;
        ElementNode tNode = (ElementNode) targetNode;
        if (sNode.getElementValue() != null) {
            if (!sNode.getElementValue().equals(tNode.getElementValue())) {
                SimpleBooleanProperty sbp = new SimpleBooleanProperty(false);
                ((ResultTreeItem) sourceNode).setIsSelected(sbp);
                ((ResultTreeItem) targetNode).setIsSelected(sbp);
                this.resultList.add(new Result("VALUE MISMATCH: " + sNode.getElementName() + " Source Value: " + sNode.getElementValue() + " VS Target Value: " + tNode.getElementValue(), ResultType.VALUEMISMATCH, sbp));
            }
        }
    }

    private void compareNodeAttributes(TreeItem<String> sourceNode, TreeItem<String> targetNode) {
        for (AttributeNode attribute : getAttributes(sourceNode)) {
            List<AttributeNode> targetMatches = getTargetAttributeMatches(attribute.getElementName(), targetNode);

            boolean matched = false;

            for (AttributeNode targetAttribute : targetMatches) {
                if (attribute.getElementValue().equals(targetAttribute.getElementValue())) {
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                SimpleBooleanProperty sbp = new SimpleBooleanProperty(false);
                attribute.setIsSelected(sbp);
                this.resultList.add(new Result("ATTRIBUTE MISMATCH: Source attribute " + attribute.getElementName() + " in " + sourceNode.getValue(), ResultType.ATTRIBUTEMISMATCH, sbp));
            }

        }
    }

    private List<AttributeNode> getTargetAttributeMatches(String name, TreeItem<String> targetNode) {
        List<AttributeNode> targetAttributeMatches = new ArrayList<>();

        for (AttributeNode attributeNode : getAttributes(targetNode)) {
            if (attributeNode.getElementName().equals(name)) {
                targetAttributeMatches.add(attributeNode);
            }
        }

        return targetAttributeMatches;
    }

    private List<AttributeNode> getAttributes(TreeItem<String> node) {
        return node.getChildren().stream().filter(n -> n instanceof AttributeNode).map(no -> (AttributeNode) no).collect(Collectors.toList());
    }

    private List<ElementNode> getChilds(TreeItem<String> node) {
        return node.getChildren().stream().filter(n -> n instanceof ElementNode).map(no -> (ElementNode) no).collect(Collectors.toList());
    }

}
