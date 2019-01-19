package com.github.aks8m.engine;

import com.github.aks8m.compare.MDHTComparisonService;
import com.github.aks8m.tree.AnalysisTreeTransformer;
import com.github.aks8m.report.ComparisonReport;
import com.github.aks8m.tree.Node;
import com.github.aks8m.report.result.ResultTreeItem;
import com.github.aks8m.traversal.MDHTTraversalService;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

import java.util.concurrent.CountDownLatch;


public class MDHTComparisonEngine extends CompareEngine {

    private ComparisonReport comparisonReport;
    private Node analysisRoot = null;
    private final ResultTreeItem sourceRoot;
    private final ResultTreeItem targetRoot;
    private final MDHTTraversalService traversalService;
    private final MDHTComparisonService comparisonService;

    private final double PROGRESS_MAX_VALUE = 99.9;
    private double PROGRESS_INCREMENT = 33.3;
    private double currentProgressValue = 0.0;

    @FXML
    private double computeProgress(double incrementValue){
        if (this.currentProgressValue == 0) {
            this.currentProgressValue = incrementValue;
        } else {
            this.currentProgressValue += incrementValue;
        }
        return this.currentProgressValue;
    }

    public MDHTComparisonEngine(ClinicalDocument sourceClinicalDocument, ClinicalDocument targetClinicalDocument,
                                ResultTreeItem sourceRoot, ResultTreeItem targetRoot) {
        this.traversalService = new MDHTTraversalService(sourceClinicalDocument,targetClinicalDocument);
        this.comparisonService = new MDHTComparisonService();
        this.sourceRoot = sourceRoot;
        this.targetRoot = targetRoot;
    }

    @Override
    protected Task<ComparisonReport> createTask() {

        this.comparisonReport = new ComparisonReport();

        return new Task<ComparisonReport>() {

            @Override
            protected ComparisonReport call() throws Exception {

                //run MDHT pre processing to build traversal tree
                CountDownLatch traversalLatch = new CountDownLatch(1);
                Platform.runLater(() -> {
                    try {
                        traversalService.start();
                        traversalService.stateProperty().addListener((observable, oldValue, newValue) -> {
                            switch (newValue) {
                                case SUCCEEDED:
                                    analysisRoot = traversalService.getValue();
                                    comparisonService.setRootNode(analysisRoot);
                                    traversalLatch.countDown();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                traversalLatch.await();

                updateProgress(computeProgress(PROGRESS_INCREMENT),PROGRESS_MAX_VALUE);

                //run MDHT main processing by traversing the tree and executing the comparisons
                CountDownLatch compareLatch = new CountDownLatch(1);
                Platform.runLater(() -> {
                    try {
                        comparisonService.start();
                        comparisonService.stateProperty().addListener((observable, oldValue, newValue) -> {
                            switch (newValue) {
                                case SUCCEEDED:
                                    comparisonReport.addResults(comparisonService.getValue());
                                    compareLatch.countDown();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                compareLatch.await();

                updateProgress(computeProgress(PROGRESS_INCREMENT),PROGRESS_MAX_VALUE);

                //Build Source and Target Trees
                AnalysisTreeTransformer.UITransformation(analysisRoot, sourceRoot, targetRoot);

                updateProgress(computeProgress(PROGRESS_INCREMENT),PROGRESS_MAX_VALUE);


                return comparisonReport;
            }
        };
    }
}