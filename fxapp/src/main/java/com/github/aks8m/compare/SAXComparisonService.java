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

                if (sourceNode.getName().equals(targetNode.getName())) {
                    resultList.addAll(recursiveComparison(sourceNode, targetNode));
                } else {
                    Result addRes = new Result("Source and Target Documents did not have a matching root document type", ResultType.MISMATCH);
                    addRes.addSourceNode(sourceNode);
                    addRes.addTargetNodes(targetNode);
                    resultList.add(addRes);
                }

                return resultList;
            }

        };
    }

    private List<Result> recursiveComparison(ParserNode sourceNode, ParserNode targetNode) {

        List<Result> totalRet = new ArrayList<>();

        //check all node ATTRIBUTES
        totalRet.addAll(checkAttributes(sourceNode, targetNode));

        //check node VALUE
        totalRet.addAll(checkValue(sourceNode,targetNode));

        //check all CHILDREN
        totalRet.addAll(checkChildren(sourceNode,targetNode));

        return (List<Result>) ((ArrayList<Result>) totalRet).clone();
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


    private List<Result> checkAttributes(ParserNode sourceNode, ParserNode targetNode) {

        List<Result> totalRet = new ArrayList<>();

        for (ParserNode nodeAttribute : sourceNode.getAttributes()) {
            Boolean attributeMatch = false;
            List<ParserNode> targetAttributeMatches = getTargetAttributeMatches(nodeAttribute.getName(), targetNode);

            if (targetAttributeMatches.size() == 0) {
                Result addRes = new Result("Attribute Not Present in Target Document: " + sourceNode.getName() + ": " + nodeAttribute.getName() + " -> " + nodeAttribute.getValue(), ResultType.MISMATCH);
                addRes.addSourceNode(nodeAttribute);
                addRes.addTargetNodes(targetNode);
                totalRet.add(addRes);

            } else if (targetAttributeMatches.size() == 1) {
                if (nodeAttribute.getValue().equals(targetAttributeMatches.get(0).getValue())) {
                    attributeMatch = true;
                } else {
                    Result addRes = new Result(nodeAttribute.getName() + ": " + nodeAttribute.getValue()
                            + " VS " + targetAttributeMatches.get(0).getName() + ": " + targetAttributeMatches.get(0).getValue(), ResultType.MISMATCH);
                    addRes.addSourceNode(nodeAttribute);
                    addRes.addTargetNodes(targetAttributeMatches.get(0));
                    totalRet.add(addRes);
                }
            } else {
                for (ParserNode targetAttribute : targetAttributeMatches) {
                    if (nodeAttribute.getValue().equals(targetAttribute.getValue())) {
                        attributeMatch = true;
                        break;
                    }
                }

                if (!attributeMatch) {
                    Result addRes = new Result("Attribute Match Not Found in Target Document: " + nodeAttribute.getName() + ": " + nodeAttribute.getValue(), ResultType.MISMATCH);
                    addRes.addSourceNode(nodeAttribute);
                    targetAttributeMatches.forEach(tA -> addRes.addTargetNodes(tA));
                    totalRet.add(addRes);
                }
            }

        }
        return totalRet;
    }

    private List<Result> checkValue(ParserNode sourceNode, ParserNode targetNode) {

        List<Result> totalRet = new ArrayList<>();

        if (sourceNode.getValue() != null && !sourceNode.getValue().equals("")) {
            if (!sourceNode.getValue().equals(targetNode.getValue())) {
                Result addRes = new Result(sourceNode.getName() + ": " + sourceNode.getValue()
                        + " VS " + targetNode.getName() + ": " + targetNode.getValue(), ResultType.MISMATCH);
                addRes.addSourceNode(sourceNode);
                addRes.addTargetNodes(targetNode);
                totalRet.add(addRes);
            }
        }

        return totalRet;
    }

    private List<Result> checkChildren(ParserNode sourceNode, ParserNode targetNode) {

        List<Result> totalRet = new ArrayList<>();

        for (ParserNode sourceChildNode : sourceNode.getChildren()) {
            List<ParserNode> targetNameMatches = getTargetChildMatches(sourceChildNode.getName(), targetNode);

            if (targetNameMatches.size() == 0) {
                Result addRes = new Result("Section Match Not Found: Section Name Does not Exist in Target Document -> " + sourceChildNode.getName(), ResultType.SECTIONMATCHNOTFOUND);
                addRes.addSourceNode(sourceChildNode);
                addRes.addTargetNodes(targetNode);
                totalRet.add(addRes);
            } else if (targetNameMatches.size() == 1) {
                totalRet.addAll(recursiveComparison(sourceChildNode, targetNameMatches.get(0)));
            } else {
                boolean sectionMatchFound = false;
                List<Result> returnedResults = new ArrayList<>();
                for (ParserNode possibleTarget : targetNameMatches) {
                    returnedResults = recursiveComparison(sourceChildNode, possibleTarget);
                    if (returnedResults.size() == 0) {
                        sectionMatchFound = true;
                        break;
                    }
                }

                if (!sectionMatchFound) {
                    Result addRes = new Result("Section Match Not Found: All matching section types have errors -> " + sourceChildNode.getName(), ResultType.SECTIONMATCHNOTFOUND);
                    addRes.addSourceNode(sourceChildNode);
                    targetNameMatches.forEach(pT -> addRes.addTargetNodes(pT));
                    totalRet.add(addRes);
                }
            }

        }

        return totalRet;
    }
}
