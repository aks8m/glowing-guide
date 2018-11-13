package com.github.aks8m.report;

import com.github.aks8m.report.result.ResultType;

import java.util.ArrayList;
import java.util.List;
import com.github.aks8m.report.result.Result;


public class ComparisonReport {

    private List<Result> matches;
    private List<Result> mismatches;
    private List<Result> warnings;
    private List<Result> postCompareMismatches;

    public ComparisonReport(){
        this.matches = new ArrayList<Result>();
        this.mismatches = new ArrayList<Result>();
        this.warnings = new ArrayList<Result>();
        this.postCompareMismatches = new ArrayList<>();
    }

    public void addMatch(Result match){
        this.matches.add(match);
    }

    public void addMismatch(Result mismatch){
        this.mismatches.add(mismatch);
    }

    public void addWarning(Result warning){
        this.warnings.add(warning);
    }

    public void setPostCompareMismatches(List<Result> postCompareMismatches) {
        this.postCompareMismatches = postCompareMismatches;
    }

    public List<Result> getMismatches() {
        return mismatches;
    }

    public List<Result> getPostCompareMismatches() {
        return postCompareMismatches;
    }

    public List<Result> getWarnings() {
        return warnings;
    }
}
