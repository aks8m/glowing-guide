//package com.github.aks8m.service;
//
//import com.github.aks8m.model.NodePOJO;
//import com.github.aks8m.model.result.Result;
//import com.github.aks8m.model.result.ResultType;
//import javafx.scene.control.TreeItem;
//
//import javax.xml.soap.Node;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * 5/1/2019
// *
// * @author kmaulden
// */
//public class CompareService {
//
//    private NodePOJO sourceNode;
//    private NodePOJO targetNode;
//    private List<Result> resultList = new ArrayList<>();
//
//    public void setSourceNode(NodePOJO sourceNode) {
//        this.sourceNode = sourceNode;
//    }
//
//    public void setTargetNode(NodePOJO targetNode) {
//        this.targetNode = targetNode;
//    }
//
//    public List<Result> compare() {
//        if (this.sourceNode!= null && this.targetNode != null) {
//            orderedCompare();
//        } else {
//            this.resultList.add(new Result("Source and Root Nodes were not initialized correctly", ResultType.SECTIONMATCHNOTFOUND));
//        }
//
////        for(Result res : this.resultList) {
////            System.out.println("RESULT SOURCE: " + res.getSourceid());
////            System.out.println("RESULT TARGET: " + res.getTargetid());
////
////        }
//
//        return this.resultList;
//
//    }
//
//    private void orderedCompare() {
//
//        Queue<NodePOJO> sourceQueue = new ArrayDeque<>();
//        Queue<NodePOJO> targetQueue = new ArrayDeque<>();
//
//        sourceQueue.add(this.sourceNode);
//        targetQueue.add(this.targetNode);
//
//        while (!sourceQueue.isEmpty()) {
//            if (targetQueue.isEmpty()) {
//                this.resultList.add(new Result("SECTION MISMATCH: Target document had no such element", ResultType.SECTIONMATCHNOTFOUND));
//            } else {
//                NodePOJO sourceNode = sourceQueue.remove();
//                NodePOJO targetNode = null;
//
//                try {
//                    targetNode = targetQueue.remove();
//                } catch (Exception e) {
//                    this.resultList.add(new Result("SECTION MISMATCH: Target document had no such element", ResultType.SECTIONMATCHNOTFOUND));
//                }
//
//                try {
//                    //compare Node Values
//                    compareNodeValues(sourceNode, targetNode);
//
//                    //compare Node Attributes
//                    compareNodeAttributes(sourceNode, targetNode);
//
//                    //add all source children to sourceQueue
//                    sourceQueue.addAll(getChildrenNodes(sourceNode));
//                    //add all target children to targetQueue
//                    targetQueue.addAll(getChildrenNodes(targetNode));
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//
////        return results;
//    }
//
//
//    private void compareNodeValues(NodePOJO sourceNode, NodePOJO targetNode) {
//        if (sourceNode.getValue() != null) {
//            if (!sourceNode.getValue().equals(targetNode.getValue())) {
//                this.resultList.add(new Result("VALUE MISMATCH: " + sourceNode.getName() + " Source Value: " + sourceNode.getValue() + " VS Target Value: " + targetNode.getValue(), ResultType.VALUEMISMATCH, sourceNode.getId(), targetNode.getId()));
//            }
//        }
//    }
//
//    private void compareNodeAttributes(NodePOJO sourceNode, NodePOJO targetNode) {
//        for (NodePOJO sourceNodeAttribute : getChildrenAttributes(sourceNode)) {
//            List<NodePOJO> targetMatches = getTargetAttributeMatches(sourceNodeAttribute.getName(), targetNode);
//
//            boolean matched = false;
//
//            for (NodePOJO targetAttribute : targetMatches) {
//                if (sourceNodeAttribute.getValue().equals(targetAttribute.getValue())) {
//                    matched = true;
//                    break;
//                }
//            }
//
//            if (!matched) {
//                this.resultList.add(new Result("ATTRIBUTE MISMATCH: Source attribute " + sourceNodeAttribute.getName() + " in " + sourceNode.getName(), ResultType.ATTRIBUTEMISMATCH, sourceNodeAttribute.getId(), targetNode.getId()));
//            }
//        }
//    }
//
//    private List<NodePOJO> getTargetAttributeMatches(String name, NodePOJO targetNode) {
//        List<NodePOJO> targetAttributeMatches = new ArrayList<>();
//
//        for (NodePOJO attributeNode : getChildrenAttributes(targetNode)) {
//            if (attributeNode.getName().equals(name)) {
//                targetAttributeMatches.add(attributeNode);
//            }
//        }
//
//        return targetAttributeMatches;
//    }
//
//
//    private List<NodePOJO> getChildrenNodes(NodePOJO node) {
//        return node.getChildren().stream().filter(n -> n.getAttribute()==0).collect(Collectors.toList());
//    }
//
//    private List<NodePOJO> getChildrenAttributes(NodePOJO node) {
//        return node.getChildren().stream().filter(n -> n.getAttribute()==1).collect(Collectors.toList());
//    }
//}
