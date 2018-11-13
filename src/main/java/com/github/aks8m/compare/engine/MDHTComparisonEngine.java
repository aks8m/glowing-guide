package com.github.aks8m.compare.engine;

import com.github.aks8m.compare.precompare.MDHTPreCompareService;
import com.github.aks8m.compare.postcompare.MDHTPostCompareService;
import com.github.aks8m.report.ComparisonReport;
import com.github.aks8m.report.result.ResultType;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import com.github.aks8m.compare.Comparison;
import com.github.aks8m.report.result.Result;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;


public class MDHTComparisonEngine extends CompareEngine {

    private ComparisonReport comparisonReport;
    private List<Comparison> comparisons = new ArrayList<>();
    private final MDHTPreCompareService mdhtPreCompareService;
    private MDHTPostCompareService mdhtPostCompareService;

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

    public MDHTComparisonEngine(ClinicalDocument sourceClinicalDocument, ClinicalDocument targetClinicalDocument) {
        this.mdhtPreCompareService = new MDHTPreCompareService(sourceClinicalDocument, targetClinicalDocument);
    }

    @Override
    protected Task<ComparisonReport> createTask() {

        this.comparisonReport = new ComparisonReport();

        return new Task<ComparisonReport>() {

            @Override
            protected ComparisonReport call() throws Exception {
                //run MDHT pre processing
                CountDownLatch prelatch = new CountDownLatch(1);
                Platform.runLater(() -> {
                    try {
                        //run MDHT Pre processing
                        mdhtPreCompareService.start();
                        mdhtPreCompareService.stateProperty().addListener((observable, oldValue, newValue) -> {
                            switch (newValue) {
                                case SUCCEEDED:
                                    comparisons = mdhtPreCompareService.getValue();
                                    prelatch.countDown();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                prelatch.await();
                updateProgress(computeProgress(PROGRESS_INCREMENT),PROGRESS_MAX_VALUE);

                //run MDHT main processing
                for (Comparison compare : comparisons) {
                    ResultType resType = compare.compare().getResultType();
                    if (resType == ResultType.MATCH) {
                        comparisonReport.addMatch(new Result(compare, ResultType.MATCH));
                    } else if (resType == ResultType.MISMATCH) {
                        comparisonReport.addMismatch(new Result(compare,ResultType.MISMATCH));
                    }
                }
                updateProgress(computeProgress(PROGRESS_INCREMENT),PROGRESS_MAX_VALUE);


                //run MDHT postcompare processing
                mdhtPostCompareService = new MDHTPostCompareService(comparisonReport.getMismatches());
                CountDownLatch postlatch = new CountDownLatch(1);
                Platform.runLater(() -> {
                    try {
                        mdhtPostCompareService.start();
                        mdhtPostCompareService.stateProperty().addListener((observable, oldValue, newValue) -> {
                            switch (newValue) {
                                case SUCCEEDED:
                                    comparisonReport.setPostCompareMismatches(mdhtPostCompareService.getValue());
                                    postlatch.countDown();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                postlatch.await();
                updateProgress(computeProgress(PROGRESS_INCREMENT),PROGRESS_MAX_VALUE);



                return comparisonReport;
            }
        };
    }
}
