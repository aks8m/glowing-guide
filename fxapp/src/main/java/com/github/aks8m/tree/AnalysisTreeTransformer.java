package com.github.aks8m.tree;

import com.github.aks8m.compare.comparisonobject.Comparison;
import com.github.aks8m.compare.comparisonobject.ComparisonValue;
import com.github.aks8m.report.result.ResultTreeItem;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TreeItem;

import java.util.ArrayDeque;
import java.util.Queue;

public class AnalysisTreeTransformer {

    private static Queue<ParserNode> saxTreeQueue = new ArrayDeque<>();
    private static Queue<Node> analyticTreeQueue = new ArrayDeque<>();
    private static Queue<ResultTreeItem> sourceTreeQueue = new ArrayDeque<>();
    private static Queue<ResultTreeItem> targetTreeQueue = new ArrayDeque<>();


    public static void SAXUITransformation(ParserNode saxTreeRoot, ResultTreeItem uiRoot) {
        saxTreeQueue.add(saxTreeRoot);

        uiRoot.setValue(saxTreeRoot.getName());
        addAttributeSubChildren(saxTreeRoot, uiRoot);
        sourceTreeQueue.add(uiRoot);
        saxTreeRoot.addResultTreeItem(uiRoot);

        while (!saxTreeQueue.isEmpty()) {

            ParserNode node = saxTreeQueue.remove();
            TreeItem source = sourceTreeQueue.remove();


            for (ParserNode childNode : node.getChildren()) {
                try {
                    ResultTreeItem treeChild;
                    if (childNode.getValue() != null && !childNode.getValue().equals("")) {
                        treeChild = new ResultTreeItem(childNode.getName()+ ": " + childNode.getValue(), childNode.getResult().isSelectedProperty());
                        addAttributeSubChildren(childNode, treeChild);
                    } else {
                        treeChild = new ResultTreeItem(childNode.getName(), new SimpleBooleanProperty(false));
                        addAttributeSubChildren(childNode, treeChild);
                    }

                    childNode.addResultTreeItem(treeChild);

                    source.getChildren().add(treeChild);

                    saxTreeQueue.add(childNode);
                    sourceTreeQueue.add(treeChild);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void addSubChild(ResultTreeItem parent, ComparisonValue comparisonValue,
                                    SimpleBooleanProperty simpleBooleanProperty, int position){
        ResultTreeItem tempResultTreeItem = new ResultTreeItem(comparisonValue.getValueName() + ":" + comparisonValue.getValue(),
                simpleBooleanProperty,position + 1);
        parent.getChildren().add(tempResultTreeItem);
    }

    private static void addAttributeSubChildren(ParserNode childNode, ResultTreeItem treeChild) {
        for (ParserNode childAttribute : childNode.getAttributes()) {
            ResultTreeItem tempTreeItem = new ResultTreeItem(childAttribute.getName() + ": " + childAttribute.getValue(), new SimpleBooleanProperty(false));
            childAttribute.addResultTreeItem(tempTreeItem);
            treeChild.getChildren().add(tempTreeItem);
        }
    }




    public static void MDHTUITransformation(Node analysisRoot, ResultTreeItem sourceUIRoot,
                                            ResultTreeItem targetUIRoot){

        analyticTreeQueue.add(analysisRoot);

        sourceUIRoot.setValue(analysisRoot.getLocationType().toString());
        targetUIRoot.setValue(analysisRoot.getLocationType().toString());
        sourceTreeQueue.add(sourceUIRoot);
        targetTreeQueue.add(targetUIRoot);

        int position = 0;
        while(!saxTreeQueue.isEmpty()){

                Node node = analyticTreeQueue.remove();
                TreeItem source = sourceTreeQueue.remove();
                TreeItem target = targetTreeQueue.remove();


                for (Node childNode : node.getChildren()) {

                    try {
                        Comparison childComparison = childNode.getComparison();

                        ResultTreeItem sourceChild = new ResultTreeItem(childNode.getLocationType().toString());
                        ResultTreeItem targetChild = new ResultTreeItem(childNode.getLocationType().toString());

                        if (childComparison != null) {

                            if (!childComparison.isListCompare()) {
                                if (childComparison.getResults().size() > 0) {
                                    addSubChild(sourceChild, childComparison.getSourceComparisonValue(), childComparison.getResults().get(0).isSelectedProperty(), position);
                                    addSubChild(targetChild, childComparison.getTargetComparisonValue(), childComparison.getResults().get(0).isSelectedProperty(), position);
                                }
                            } else {
//                        for(ComparisonValue comparisonValue : childComparison.getSourceComparisonValues()){
//                            for(Result result : childComparison.getResults()){
//                                addSubChild(sourceChild, comparisonValue, result.isSelectedProperty(), position);
//                            }
//                        }
                                for (int i = 0; i < childComparison.getSourceComparisonValues().size(); i++) {
                                    addSubChild(sourceChild, childComparison.getSourceComparisonValues().get(i), childComparison.getResults().get(i).isSelectedProperty(), position);
                                }

//                        for(ComparisonValue comparisonValue : childComparison.getTargetComparisonValues()){
//                            for(Result result : childComparison.getResults()){
//                                addSubChild(targetChild, comparisonValue, result.isSelectedProperty(), position);
//                            }
//                        }
                                for (int i = 0; i < childComparison.getTargetComparisonValues().size(); i++) {
                                    addSubChild(targetChild, childComparison.getTargetComparisonValues().get(i), childComparison.getResults().get(i).isSelectedProperty(), position);
                                }


                            }
                        }

                        source.getChildren().add(sourceChild);
                        target.getChildren().add(targetChild);

                        analyticTreeQueue.add(childNode);
                        sourceTreeQueue.add(sourceChild);
                        targetTreeQueue.add(targetChild);
                        position++;


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        }
    }




}
