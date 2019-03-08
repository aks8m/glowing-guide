package com.github.aks8m.engine;

import com.github.aks8m.compare.SAXUnorderedLexicographicalComparisonService;
import com.github.aks8m.compare.SAXOrderedLexicographicalComparisonService;

import com.github.aks8m.compare.ComparisonService;
import com.github.aks8m.report.ComparisonReport;
import com.github.aks8m.traversal.SAXTraversalService;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class SAXParserFileCompareEngine extends CompareEngine {
    private ComparisonReport comparisonReport;
    private TreeItem<String> sourceRoot = null;
    private TreeItem<String> targetRoot = null;
    private SAXTraversalService sourceTraversalService;
    private SAXTraversalService targetTraversalService;
    private ComparisonService comparisonService;

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

    public SAXParserFileCompareEngine(File sourceFilePath, File targetFilePath, HashMap<TreeItem<String>, TreeItem<String>> nodemap) {
        this.sourceTraversalService = new SAXTraversalService(sourceFilePath);
        this.targetTraversalService = new SAXTraversalService(targetFilePath);
//        this.comparisonService = new SAXOrderedLexicographicalComparisonService();
        this.comparisonService = new SAXUnorderedLexicographicalComparisonService(nodemap);

    }

    @Override
    protected Task<ComparisonReport> createTask() {

        this.comparisonReport = new ComparisonReport();

        return new Task<ComparisonReport>() {

            @Override
            protected ComparisonReport call() throws Exception {

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

                updateProgress(computeProgress(PROGRESS_INCREMENT/2),PROGRESS_MAX_VALUE);
                comparisonReport.setSourceRoot(sourceRoot);

                updateProgress(computeProgress(PROGRESS_INCREMENT/2),PROGRESS_MAX_VALUE);
                comparisonReport.setTargetRoot(targetRoot);


                return comparisonReport;
            }


        };
    }
}
