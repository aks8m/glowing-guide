package com.github.aks8m.compare.tree;

import com.github.aks8m.compare.comparisonobject.Comparison;
import com.github.aks8m.report.result.ResultTreeItem;
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
                    ResultTreeItem sourceValue = new ResultTreeItem(childComparison.getSource().toString(),
                            childComparison.getResult().isSelectedProperty(),
                            position + 1);
                    sourceChild.getChildren().add(sourceValue);
                    ResultTreeItem targetValue = new ResultTreeItem(childComparison.getTarget().toString(),
                            childComparison.getResult().isSelectedProperty(),
                            position + 1);
                    targetChild.getChildren().add(targetValue);
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
}
