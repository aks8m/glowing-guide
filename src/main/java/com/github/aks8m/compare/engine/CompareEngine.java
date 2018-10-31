package com.github.aks8m.compare.engine;

import com.github.aks8m.compare.postcompare.PostCompareService;
import com.github.aks8m.compare.precompare.PreCompareService;
import com.github.aks8m.report.ComparisonReport;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class CompareEngine extends Service<ComparisonReport> {

    private final PreCompareService preCompareService;
    private final PostCompareService postCompareService;

    public CompareEngine(PreCompareService preCompareService, PostCompareService postCompareService) {
        this.preCompareService = preCompareService;
        this.postCompareService = postCompareService;
    }

    @Override
    protected Task<ComparisonReport> createTask() {

        return new Task<ComparisonReport>() {

            @Override
            protected ComparisonReport call() throws Exception {

                preCompareService.start();

                //batch Comparison.compare()

                postCompareService.start();

                return null;
            }
        };
    }
}
