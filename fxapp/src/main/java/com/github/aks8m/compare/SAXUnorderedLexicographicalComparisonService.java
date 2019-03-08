package com.github.aks8m.compare;

import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultTreeItem;
import com.github.aks8m.report.result.ResultType;
import com.github.aks8m.tree.AttributeNode;
import com.github.aks8m.tree.ElementNode;
import com.github.aks8m.tree.XMLElement;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.scene.control.TreeItem;

import java.util.*;
import java.util.stream.Collectors;

public class SAXUnorderedLexicographicalComparisonService extends ComparisonService {

    TreeItem<String> sourceNode = null;
    TreeItem<String> targetNode = null;
    private List<Result> resultList = new ArrayList<>();
    HashMap<TreeItem<String>, TreeItem<String>> nodeMap;

    public void setSourceNode(TreeItem<String> sourceNode) {
        this.sourceNode = sourceNode;
    }

    public void setTargetNode(TreeItem<String> targetNode) {
        this.targetNode = targetNode;
    }

    public SAXUnorderedLexicographicalComparisonService(HashMap<TreeItem<String>, TreeItem<String>> nodeMap) { this.nodeMap = nodeMap; }

    public SAXUnorderedLexicographicalComparisonService(TreeItem<String> sourceNode, TreeItem<String> targetNode, HashMap<TreeItem<String>, TreeItem<String>> nodeMap) { this.sourceNode = sourceNode; this.targetNode = targetNode; this.nodeMap = nodeMap; }

    protected Task<List<Result>> createTask() {
        return new Task<List<Result>>() {

            @Override
            protected List<Result> call() throws Exception {

                if (sourceNode.getValue().equals(targetNode.getValue())) {
                    breadthFirstUnorderedSearch(sourceNode, targetNode);
                } else {
                    resultList.add(new Result("Source and Target Documents did not have a matching root document type", ResultType.SECTIONMATCHNOTFOUND));
                }
                return resultList;
            }

        };
    }

    private void breadthFirstUnorderedSearch(TreeItem<String> source, TreeItem<String> target) {
        Queue<TreeItem<String>> sourceQueue = new ArrayDeque<>();
        Queue<TreeItem<String>> targetQueue = new ArrayDeque<>();

        sourceQueue.add(source);
        targetQueue.add(target);

        TreeItem<String> targetParent;

        while(!sourceQueue.isEmpty()) {

            if (targetQueue.isEmpty()) {
                this.resultList.add(new Result("SECTION MISMATCH: Target document had no such element", ResultType.SECTIONMATCHNOTFOUND));
            } else {
                TreeItem<String> sourceNode = sourceQueue.remove();
                TreeItem<String> targetNode = null;
                try {
                    List<TreeItem<String>> targetNodes = getTargetNode(sourceNode,targetQueue);

                    if (targetNodes.size() == 0) {
                        targetNode = null;
                        SimpleBooleanProperty sbp = new SimpleBooleanProperty(false);
                        ((ResultTreeItem) sourceNode).setIsSelected(sbp);
                        this.resultList.add(new Result("SECTION MISMATCH: Target document had no such element", ResultType.SECTIONMATCHNOTFOUND, sbp));
                    } else if (targetNodes.size() == 1) {
                        try {
                            targetNode = targetNodes.get(0);
                            targetQueue.remove(targetNode);
                        } catch (Exception e) {
                            SimpleBooleanProperty sbp = new SimpleBooleanProperty(false);
                            ((ResultTreeItem) sourceNode).setIsSelected(sbp);
                            this.resultList.add(new Result("SECTION MISMATCH: Target document had no such element", ResultType.SECTIONMATCHNOTFOUND, sbp));
                        }

                        if (targetNode == null) {
                            SimpleBooleanProperty sbp = new SimpleBooleanProperty(false);
                            ((ResultTreeItem) sourceNode).setIsSelected(sbp);
                            this.resultList.add(new Result("SECTION MISMATCH: Target document had no such element", ResultType.SECTIONMATCHNOTFOUND, sbp));
                        } else {
                            this.nodeMap.put(sourceNode,targetNode);
                            //compare node values
                            this.resultList.addAll(compareNodeValues(sourceNode, targetNode));

                            //compare node attributes
                            this.resultList.addAll(compareNodeAttributes(sourceNode, targetNode));

                            sourceQueue.addAll(getChilds(sourceNode));
                            targetQueue.addAll(getChilds(targetNode));
                        }

                    } else {
                        SimpleBooleanProperty sbp = new SimpleBooleanProperty(false);
                        ((ResultTreeItem) sourceNode).setIsSelected(sbp);
                        targetNodes.stream().forEach(tar -> ((ResultTreeItem) tar).setIsSelected(sbp));
                        this.resultList.add(new Result("ATTRIBUTE MISMATCH: Source attribute " + ((XMLElement) sourceNode).getElementName() + " in " + sourceNode.getValue(), ResultType.ATTRIBUTEMISMATCH, sbp));
                    }

//                    targetNode = targetQueue.remove();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }

    private List<TreeItem<String>> getTargetNode(TreeItem<String> sourceNode, Queue<TreeItem<String>> targetQueue) {

        List<TreeItem<String>> retList = new ArrayList<>();

        List<TreeItem<String>> targetSiblings;
        if (sourceNode.getParent() != null) {
            targetSiblings = this.nodeMap.get(sourceNode.getParent()).getChildren().stream().filter(sib -> ((XMLElement) sib).getElementName().equals(((XMLElement) sourceNode).getElementName())).collect(Collectors.toList());
        } else {
            targetSiblings = Collections.singletonList(targetQueue.peek());
        }

        if (targetSiblings.size() == 0) {
            return retList;
        } else if (targetSiblings.size() == 1) {
            retList.add(targetSiblings.get(0));
            return retList;
        } else {
            for (TreeItem<String> sibling : targetSiblings) {
                if (sourceNode.getValue().equals(sibling.getValue()) && compareNodeValues(sourceNode,sibling).size() == 0 && compareNodeAttributes(sourceNode,sibling).size() ==0) {
                    retList.add(sibling);
                    return retList;
                }
            }

            retList.addAll(targetSiblings);
            return retList;
        }

    }

    private List<Result> compareNodeValues(TreeItem<String> sourceNode, TreeItem<String> targetNode) {
        List<Result> retList = new ArrayList<>();
        ElementNode sNode = (ElementNode) sourceNode;
        ElementNode tNode = (ElementNode) targetNode;
        if (sNode.getElementValue() != null) {
            if (!sNode.getElementValue().equals(tNode.getElementValue())) {
                SimpleBooleanProperty sbp = new SimpleBooleanProperty(false);
                ((ResultTreeItem) sourceNode).setIsSelected(sbp);
                ((ResultTreeItem) targetNode).setIsSelected(sbp);
                retList.add(new Result("VALUE MISMATCH: " + sNode.getElementName() + " Source Value: " + sNode.getElementValue() + " VS Target Value: " + tNode.getElementValue(), ResultType.VALUEMISMATCH, sbp));
            }
        }
        return retList;
    }

    private List<Result> compareNodeAttributes(TreeItem<String> sourceNode, TreeItem<String> targetNode) {
        List<Result> retList = new ArrayList<>();
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
                targetMatches.stream().forEach(tar -> tar.setIsSelected(sbp));
                if (targetMatches.size() == 1) {
                    retList.add(new Result("ATTRIBUTE MISMATCH: Source attribute " + attribute.getElementName() + " " + attribute.getElementValue() + " VS " + targetMatches.get(0).getElementValue(), ResultType.ATTRIBUTEMISMATCH, sbp));
                } else {
                    retList.add(new Result("ATTRIBUTE MISMATCH: Source attribute " + attribute.getElementName() + " in " + sourceNode.getValue(), ResultType.ATTRIBUTEMISMATCH, sbp));
                }
            }

        }
        return retList;

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
