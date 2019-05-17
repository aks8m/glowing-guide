package com.github.aks8m.service;

import com.github.aks8m.model.NodePOJO;
import com.github.aks8m.model.result.Result;
import com.github.aks8m.model.result.ResultType;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TreeItem;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 5/17/2019
 *
 * @author kmaulden
 */
public class UnorderedCompareService {

    private NodePOJO sourceNode;
    private NodePOJO targetNode;
    private List<Result> resultList = new ArrayList<>();
    HashMap<NodePOJO, NodePOJO> nodeMap = new HashMap<>();

    public void setSourceNode(NodePOJO sourceNode) {
        this.sourceNode = sourceNode;
    }

    public void setTargetNode(NodePOJO targetNode) {
        this.targetNode = targetNode;
    }

    public List<Result> compare() {
//        List<Result> results = new ArrayList<>();
        if (this.sourceNode!= null && this.targetNode != null) {
//            results.add(new Result("Test 1"));
//            results.add(new Result("Test 2"));
//            results.add(new Result("Test 3"));
            unorderedCompare();
        } else {
            this.resultList.add(new Result("Source and Root Nodes were not initialized correctly", ResultType.SECTIONMATCHNOTFOUND));
        }

        for(Result res : this.resultList) {
            System.out.println("RESULT SOURCE: " + res.getSourceid());
            System.out.println("RESULT TARGET: " + res.getTargetid());

        }

        return this.resultList;

    }


    public void unorderedCompare() {
        Queue<NodePOJO> sourceQueue = new ArrayDeque<>();
        Queue<NodePOJO> targetQueue = new ArrayDeque<>();

        sourceQueue.add(this.sourceNode);
        targetQueue.add(this.targetNode);

        while (!sourceQueue.isEmpty()) {
            if (targetQueue.isEmpty()) {
                this.resultList.add(new Result("SECTION MISMATCH: Target document had no such element", ResultType.SECTIONMATCHNOTFOUND, sourceNode.getId(), this.nodeMap.get(sourceQueue.peek().getParent()).getId()));
            } else {
                NodePOJO sourceNode = sourceQueue.remove();
                NodePOJO targetNode = null;

                try {
                    List<NodePOJO> targetNodes = getTargetNodes(sourceNode, targetQueue);

                    if (targetNodes.size() == 0) {
                        this.resultList.add(new Result("SECTION MISMATCH: Target document had no such element", ResultType.SECTIONMATCHNOTFOUND, sourceNode.getId(), this.nodeMap.get(sourceNode.getParent()).getId()));
                    } else if (targetNodes.size() == 1) {
                        try {
                            targetNode = targetNodes.get(0);
                            targetQueue.remove(targetNode);
                        } catch (Exception e) {
                            this.resultList.add(new Result("SECTION MISMATCH: Target document had no such element", ResultType.SECTIONMATCHNOTFOUND, sourceNode.getId(), this.nodeMap.get(sourceNode.getParent()).getId()));
                        }

                        if (targetNode == null) {
                            this.resultList.add(new Result("SECTION MISMATCH: Target document had no such element", ResultType.SECTIONMATCHNOTFOUND, sourceNode.getId(), this.nodeMap.get(sourceNode.getParent()).getId()));
                        } else {
                            this.nodeMap.put(sourceNode, targetNode);

                            this.resultList.addAll(compareNodeValues(sourceNode,targetNode));

                            this.resultList.addAll(compareNodeAttributes(sourceNode,targetNode));

                            sourceQueue.addAll(getChilds(sourceNode));
                            targetQueue.addAll(getChilds(targetNode));
                        }
                    } else {
                        this.resultList.add(new Result("ATTRIBUTE MISMATCH: Source attribute " + sourceNode.getName() + " in " + sourceNode.getValue(), ResultType.ATTRIBUTEMISMATCH, sourceNode.getId(), this.nodeMap.get(sourceNode.getParent()).getId()));

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }

    private List<NodePOJO> getTargetNodes(NodePOJO sourceNode, Queue<NodePOJO> targetQueue) {

        List<NodePOJO> retList = new ArrayList<>();

        List<NodePOJO> targetSiblings;
        if (sourceNode.getParent() != null) {
            targetSiblings = this.nodeMap.get(sourceNode.getParent()).getChildren().stream().filter(sib -> sib.getName().equals(sourceNode.getName())).collect(Collectors.toList());
        } else {
            targetSiblings = Collections.singletonList(targetQueue.peek());
        }

        if (targetSiblings.size() == 0) {
            return retList;
        } else if (targetSiblings.size() == 1) {
            retList.add(targetSiblings.get(0));
            return retList;
        } else {
            for (NodePOJO sibling : targetSiblings) {
                if (Objects.equals(sourceNode.getValue(),sibling.getValue()) && compareNodeValues(sourceNode,sibling).size() == 0 && compareNodeAttributes(sourceNode,sibling).size() ==0) {
                    retList.add(sibling);
                    return retList;
                }
            }

            retList.addAll(targetSiblings);
            return retList;
        }

    }

    private List<Result> compareNodeValues(NodePOJO sourceNode, NodePOJO targetNode) {
        List<Result> retList = new ArrayList<>();
        if (sourceNode.getValue() != null) {
            if (!sourceNode.getValue().equals(targetNode.getValue())) {
                retList.add(new Result("VALUE MISMATCH: " + sourceNode.getName() + " Source Value: " + sourceNode.getValue() + " VS Target Value: " + targetNode.getValue(), ResultType.VALUEMISMATCH, sourceNode.getId(), targetNode.getId()));
            }
        }
        return retList;
    }

    private List<Result> compareNodeAttributes(NodePOJO sourceNode, NodePOJO targetNode) {
        List<Result> retList = new ArrayList<>();
        for (NodePOJO attribute : getAttributes(sourceNode)) {
            List<NodePOJO> targetMatches = getTargetAttributeMatches(attribute.getName(), targetNode);

            boolean matched = false;

            for (NodePOJO targetAttribute : targetMatches) {
                if (attribute.getValue().equals(targetAttribute.getValue())) {
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                if (targetMatches.size() == 1) {
                    retList.add(new Result("ATTRIBUTE MISMATCH: Source attribute " + attribute.getName() + " " + attribute.getValue() + " VS " + targetMatches.get(0).getValue(), ResultType.ATTRIBUTEMISMATCH, attribute.getId(), targetMatches.get(0).getId()));
                } else {
                    retList.add(new Result("ATTRIBUTE MISMATCH: Source attribute " + attribute.getName() + " in " + sourceNode.getValue(), ResultType.ATTRIBUTEMISMATCH, attribute.getId()));
                }
            }

        }
        return retList;

    }

    private List<NodePOJO> getTargetAttributeMatches(String name, NodePOJO targetNode) {
        List<NodePOJO> targetAttributeMatches = new ArrayList<>();

        for (NodePOJO nodePOJO : getAttributes(targetNode)) {
            if (nodePOJO.getName().equals(name)) {
                targetAttributeMatches.add(nodePOJO);
            }
        }

        return targetAttributeMatches;
    }

    private List<NodePOJO> getAttributes(NodePOJO node) {
        return node.getChildren().stream().filter(n -> n.getAttribute() == 1).collect(Collectors.toList());
    }

    private List<NodePOJO> getChilds(NodePOJO node) {
        return node.getChildren().stream().filter(n -> n.getAttribute() == 0).collect(Collectors.toList());
    }
}
