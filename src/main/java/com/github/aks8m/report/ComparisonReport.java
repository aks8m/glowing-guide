package com.github.aks8m.report;

import com.github.aks8m.report.result.ResultType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.aks8m.report.result.Result;


public class ComparisonReport {

    private List<Result> results;

    public ComparisonReport(){
        this.results = new ArrayList<Result>();
    }

    public void addResults(List<Result> mismatches) {this.results.addAll(mismatches);}

    public List<Result> getMismatches() {
        return results.stream().filter(res -> res.getResultType().equals(ResultType.MISMATCH)).collect(Collectors.toList());
    }
}
