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

    public void addResult(Result match){
        this.results.add(match);
    }

    public void addResults(List<Result> mismatches) {this.results.addAll(mismatches);}

    public List<Result> getMismatches() {
        return results.stream().filter(res -> res.getResultType().equals(ResultType.MISMATCH)).collect(Collectors.toList());
    }

    public List<Result> getMatches() {
        return results.stream().filter(res -> res.getResultType().equals(ResultType.MATCH)).collect(Collectors.toList());
    }

    public List<Result> getWarnings() {
        return results.stream().filter(res -> res.getResultType().equals(ResultType.WARNING)).collect(Collectors.toList());
    }

    public List<Result> getCompoundMismatches() {
        return results.stream().filter(res -> res.getResultType().equals(ResultType.COMPOUNDMISMATCH)).collect(Collectors.toList());
    }

    public List<Result> getSectionMatchNotFound() {
        return results.stream().filter(res -> res.getResultType().equals(ResultType.SECTIONMATCHNOTFOUND)).collect(Collectors.toList());
    }

}
