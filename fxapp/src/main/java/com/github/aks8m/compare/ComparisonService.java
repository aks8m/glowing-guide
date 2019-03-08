package com.github.aks8m.compare;

import com.github.aks8m.report.result.Result;
import javafx.concurrent.Service;
import javafx.scene.control.TreeItem;

import java.util.List;


public abstract class ComparisonService extends Service<List<Result>> {

    public abstract void setSourceNode(TreeItem<String> sourceRoot);

    public abstract void setTargetNode(TreeItem<String> targetRoot);
}
