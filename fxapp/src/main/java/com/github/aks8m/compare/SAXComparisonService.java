package com.github.aks8m.compare;

import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultType;
import com.github.aks8m.tree.ParserNode;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;

public class SAXComparisonService extends ComparisonService {

    ParserNode sourceNode = null;
    ParserNode targetNode = null;
    private List<Result> resultList = new ArrayList<>();

    public void setSourceNode(ParserNode sourceNode) {
        this.sourceNode = sourceNode;
    }

    public void setTargetNode(ParserNode targetNode) {
        this.targetNode = targetNode;
    }


    @Override
    protected Task<List<Result>> createTask() {
        return new Task<List<Result>>() {

            @Override
            protected List<Result> call() throws Exception {

//                if (sourceNode.getName().equals(targetNode.getName())) {
//                    resultList.addAll(recursiveComparison(sourceNode, targetNode));
//                } else {
//                    Result addRes = new Result("Source and Target Documents did not have a matching root document type", ResultType.MISMATCH);
//                    addRes.setSourceNodes(sourceNode);
//                    addRes.setTargetNodes(targetNode);
//                    resultList.add(addRes);
//                }

                resultList = recursiveComparison(sourceNode,targetNode);

                return resultList;
            }

        };
    }

    private List<Result> recursiveComparison(ParserNode sourceNode, ParserNode targetNode) {
        List<Result> retList = new ArrayList<>();
        Result addRes;

        //compare attributes
        for (ParserNode attribute : sourceNode.getAttributes()) {
            List<ParserNode> targetMatches = getTargetAttributeMatches(attribute.getName(),targetNode);

            boolean matched = false;

            for (ParserNode targetAttribute : targetMatches) {
                if (attribute.getValue().equals(targetAttribute.getValue())) {
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                addRes = new Result("ATTRIBUTE MISMATCH: Source attribute " + attribute.getName() + " in " + attribute.getParent().getName(), ResultType.MISMATCH, attribute, targetMatches);
                retList.add(addRes);
            }

        }

        //compare value
        if (sourceNode.getValue() != null && !sourceNode.getValue().equals("")) {
            if (!sourceNode.getValue().equals(targetNode.getValue())) {
                addRes = new Result("VALUE MISMATCH: " + sourceNode.getName() + " Source Value: " + sourceNode.getValue() + " VS Target Value: " + targetNode.getValue(), ResultType.MISMATCH, sourceNode, targetNode);
                retList.add(addRes);
            }
        }

        //recursively compare children in the order that they appear in the document
        for (int i=0; i<Math.min(sourceNode.getChildren().size(), targetNode.getChildren().size()); i++) {
            retList.addAll(recursiveComparison(sourceNode.getChildren().get(i),targetNode.getChildren().get(i)));
        }

        return retList;
    }

    private List<ParserNode> getTargetChildMatches(String name, ParserNode targetNode) {
        List<ParserNode> targetChildMatches = new ArrayList<>();

        for (ParserNode childNode : targetNode.getChildren()) {
            if (childNode.getName().equals(name)) {
                targetChildMatches.add(childNode);
            }
        }

        return targetChildMatches;
    }

    private List<ParserNode> getTargetAttributeMatches(String name, ParserNode targetNode) {
        List<ParserNode> targetAttributeMatches = new ArrayList<>();

        for (ParserNode attributeNode : targetNode.getAttributes()) {
            if (attributeNode.getName().equals(name)) {
                targetAttributeMatches.add(attributeNode);
            }
        }

        return targetAttributeMatches;
    }

}
