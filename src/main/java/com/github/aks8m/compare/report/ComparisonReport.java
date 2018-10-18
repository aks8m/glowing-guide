package com.github.aks8m.compare.report;

import com.github.aks8m.compare.report.result.Match;
import com.github.aks8m.compare.report.result.Mismatch;
import com.github.aks8m.compare.report.result.Warning;

import java.util.ArrayList;
import java.util.List;

public class ComparisonReport {

    private List<Match> matches;
    private List<Mismatch> mismatches;
    private List<Warning> warnings;

    public ComparisonReport(){
        this.matches = new ArrayList<>();
        this.mismatches = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }

    public void addMatch(Match match){
        this.matches.add(match);
    }

    public void addMismatch(Mismatch mismatch){
        this.mismatches.add(mismatch);
    }

    public void addWarning(Warning warning){
        this.warnings.add(warning);
    }

    public List<Mismatch> getMismatches() {
        return mismatches;
    }
}
