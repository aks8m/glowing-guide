package com.github.aks8m.engine;

import com.github.aks8m.report.ComparisonReport;
import com.github.aks8m.report.result.ResultTreeItem;
import com.github.aks8m.saxparser.SaxParser;
import com.github.aks8m.traversal.SAXTraversalService;
import com.github.aks8m.compare.SAXComparisonService;
import com.github.aks8m.tree.AnalysisTreeTransformer;
import com.github.aks8m.tree.ParserNode;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;

import java.io.File;

import java.util.concurrent.CountDownLatch;

public class SAXParserCompareEngine extends CompareEngine {
    private ComparisonReport comparisonReport;
    private ParserNode sourceRoot = null;
    private ParserNode targetRoot = null;
    private SAXTraversalService sourceTraversalService;
    private SAXTraversalService targetTraversalService;
    private SAXComparisonService comparisonService;

    private final ResultTreeItem sourceUIRoot;
    private final ResultTreeItem targetUIRoot;



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

    public SAXParserCompareEngine(File sourceFilePath, File targetFilePath, ResultTreeItem sourceUIRoot, ResultTreeItem targetUIRoot) {
        SaxParser sparser = new SaxParser();
        this.sourceTraversalService = new SAXTraversalService(sparser,sourceFilePath);
        this.targetTraversalService = new SAXTraversalService(sparser,targetFilePath);
        this.comparisonService = new SAXComparisonService();
        this.sourceUIRoot = sourceUIRoot;
        this.targetUIRoot = targetUIRoot;


    }

    @Override
    protected Task<ComparisonReport> createTask() {

        this.comparisonReport = new ComparisonReport();

        return new Task<ComparisonReport>() {

            @Override
            protected ComparisonReport call() throws Exception {
                //run MDHT pre processing to build traversal tree
                CountDownLatch sourceTraversalLatch = new CountDownLatch(1);
                Platform.runLater(() -> {
                    try {
                        sourceTraversalService.start();
                        sourceTraversalService.stateProperty().addListener((observable, oldValue, newValue) -> {
                            switch (newValue) {
                                case SUCCEEDED:
                                    sourceRoot = sourceTraversalService.getValue();
                                    comparisonService.setSourceNode(sourceRoot);
                                    sourceTraversalLatch.countDown();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                sourceTraversalLatch.await();
                CountDownLatch targetTraversalLatch = new CountDownLatch(1);
                Platform.runLater(() -> {
                    try {
                        targetTraversalService.start();
                        targetTraversalService.stateProperty().addListener((observable, oldValue, newValue) -> {
                            switch (newValue) {
                                case SUCCEEDED:
                                    targetRoot = targetTraversalService.getValue();
                                    comparisonService.setTargetNode(targetRoot);
                                    targetTraversalLatch.countDown();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                targetTraversalLatch.await();

                updateProgress(computeProgress(PROGRESS_INCREMENT),PROGRESS_MAX_VALUE);

                CountDownLatch comparisonLatch = new CountDownLatch(1);
                Platform.runLater(() -> {
                    try {
                        comparisonService.start();
                        comparisonService.stateProperty().addListener((observable, oldValue, newValue) -> {
                            switch (newValue) {
                                case SUCCEEDED:
                                    comparisonReport.addResults(comparisonService.getValue());
                                    comparisonLatch.countDown();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                comparisonLatch.await();

                updateProgress(computeProgress(PROGRESS_INCREMENT),PROGRESS_MAX_VALUE);

                AnalysisTreeTransformer.SAXUITransformation(sourceRoot, sourceUIRoot);
                updateProgress(computeProgress(PROGRESS_INCREMENT/2),PROGRESS_MAX_VALUE);

                AnalysisTreeTransformer.SAXUITransformation(targetRoot, targetUIRoot);
                updateProgress(computeProgress(PROGRESS_INCREMENT/2),PROGRESS_MAX_VALUE);



                return comparisonReport;
            }


        };
    }
}
