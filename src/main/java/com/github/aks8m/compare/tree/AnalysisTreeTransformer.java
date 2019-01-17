package com.github.aks8m.compare.tree;

import com.github.aks8m.compare.comparisonobject.Comparison;
import com.github.aks8m.compare.comparisonobject.ComparisonValue;
import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultTreeItem;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TreeItem;

import java.util.ArrayDeque;
import java.util.Queue;

public class AnalysisTreeTransformer {

    private static Queue<Node> analyticTreeQueue = new ArrayDeque<>();
    private static Queue<ResultTreeItem> sourceTreeQueue = new ArrayDeque<>();
    private static Queue<ResultTreeItem> targetTreeQueue = new ArrayDeque<>();

    public static void UITransformation(Node analysisRoot, ResultTreeItem sourceUIRoot,
                                        ResultTreeItem targetUIRoot){

        analyticTreeQueue.add(analysisRoot);

        sourceUIRoot.setValue(analysisRoot.getLocationType().toString());
        targetUIRoot.setValue(analysisRoot.getLocationType().toString());
        sourceTreeQueue.add(sourceUIRoot);
        targetTreeQueue.add(targetUIRoot);

        int position = 0;
        while(!analyticTreeQueue.isEmpty()){

            Node node = analyticTreeQueue.remove();
            TreeItem source = sourceTreeQueue.remove();
            TreeItem target = targetTreeQueue.remove();

            for(Node childNode : node.getChildren()){
                Comparison childComparison = childNode.getComparison();

                ResultTreeItem sourceChild = new ResultTreeItem(childNode.getLocationType().toString());
                ResultTreeItem targetChild = new ResultTreeItem(childNode.getLocationType().toString());

                if(childComparison != null) {

                    if(!childComparison.isListCompare()){
                        addSubChild(sourceChild, childComparison.getSourceComparisonValue(), childComparison.getResults().get(0).isSelectedProperty(), position);
                        addSubChild(targetChild, childComparison.getTargetComparisonValue(), childComparison.getResults().get(0).isSelectedProperty(), position);
                    } else {
                        for(ComparisonValue comparisonValue : childComparison.getSourceComparisonValues()){
                            for(Result result : childComparison.getResults()){
                                addSubChild(sourceChild, comparisonValue, result.isSelectedProperty(), position);
                            }
                        }

                        for(ComparisonValue comparisonValue : childComparison.getTargetComparisonValues()){
                            for(Result result : childComparison.getResults()){
                                addSubChild(targetChild, comparisonValue, result.isSelectedProperty(), position);
                            }
                        }
                    }
                }

                source.getChildren().add(sourceChild);
                target.getChildren().add(targetChild);

                analyticTreeQueue.add(childNode);
                sourceTreeQueue.add(sourceChild);
                targetTreeQueue.add(targetChild);
                position++;
            }
        }
    }

    private static void addSubChild(ResultTreeItem parent, ComparisonValue comparisonValue,
                                    SimpleBooleanProperty simpleBooleanProperty, int position){
        ResultTreeItem tempResultTreeItem = new ResultTreeItem(comparisonValue.getValueName() + ":" + comparisonValue.getValue(),
                simpleBooleanProperty,position + 1);
        parent.getChildren().add(tempResultTreeItem);
    }
}
