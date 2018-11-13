package com.github.aks8m.report.result;

import com.github.aks8m.compare.Comparison;
import com.github.aks8m.report.ComparisonLocation;

public class Result {

    private ResultType resultType;
    private Comparison comparison;

    public Result() {

    }

    public Result(Comparison comparison, ResultType resultType) {
        this.comparison = comparison;
        this.resultType = resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public ResultType getResultType() { return resultType; }

    public Comparison getComparison() { return comparison; }

    public String toString() {
        return this.comparison.toString();
    }

}
