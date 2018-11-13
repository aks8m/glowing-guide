package com.github.aks8m.compare.postcompare;

import com.github.aks8m.compare.Comparison;
import com.github.aks8m.report.ComparisonReport;
import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultType;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.apache.commons.text.similarity.LevenshteinDistance;


import java.util.ArrayList;
import java.util.List;

public class MDHTPostCompareService extends PostCompareService {

    private final List<Result> mainCompareResults;
    private List<Result> postCompareResults;
    private int levenshteinThreshold = 2;
    private final LevenshteinDistance levenshteinDistance = new LevenshteinDistance(levenshteinThreshold);


    public MDHTPostCompareService(List<Result> mainCompareResults) {
        this.mainCompareResults = mainCompareResults;
        this.postCompareResults = new ArrayList<>();
    }

    @Override
    protected Task<List<Result>> createTask() {

        return new Task<List<Result>>() {

            @Override
            protected List<Result> call() throws Exception {
                for (Result compare : mainCompareResults) {
                    if (compare.getComparison().getSource() instanceof String) {
                        int debugValue = levenshteinDistance.apply((String)compare.getComparison().getSource(),(String)compare.getComparison().getTarget());
                        if (debugValue == -1 || levenshteinThreshold < debugValue) {
                            postCompareResults.add(compare);
                        }

                    }
                }

                return postCompareResults;
            }
        };
    }
}
