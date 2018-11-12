package com.github.aks8m.report;

import com.github.aks8m.report.result.ResultType;

import java.util.ArrayList;
import java.util.List;
import com.github.aks8m.report.result.Result;


public class ComparisonReport {

    private List<Result> matches;
    private List<Result> mismatches;
    private List<Result> warnings;

    public ComparisonReport(){
        this.matches = new ArrayList<Result>();
        this.mismatches = new ArrayList<Result>();
        this.warnings = new ArrayList<Result>();
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

    public List<Result> getMismatches() {
        return mismatches;
    }
}
