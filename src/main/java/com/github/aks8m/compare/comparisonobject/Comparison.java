package com.github.aks8m.compare.comparisonobject;

import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultType;

import java.util.List;
import java.util.function.BiFunction;

public class Comparison {

    private final Result result;
    private ComparisonValue sourceComparisonValue;
    private ComparisonValue targetComparisonValue;
    private List<ComparisonValue> sourceComparisonValues;
    private List<ComparisonValue> targetComparisonValues;
    private final boolean isListCompare;

    public Comparison(ComparisonValue sourceComparisonValue, ComparisonValue targetComparisonValue) {
        this.sourceComparisonValue = sourceComparisonValue;
        this.targetComparisonValue = targetComparisonValue;
        this.result = new Result(this.sourceComparisonValue.getValueName() + ": " + this.sourceComparisonValue.getValue()
                + " vs " + this.targetComparisonValue.getValueName() + ": " + this.targetComparisonValue.getValue());
        this.isListCompare = false;
    }

    public Comparison(List<ComparisonValue> sourceComparisonValues, List<ComparisonValue> targetComparisonValues) {
        this.sourceComparisonValues = sourceComparisonValues;
        this.targetComparisonValues = targetComparisonValues;
        this.result = new Result(this.sourceComparisonValue.getValueName() + ": " + this.sourceComparisonValue.getValue()
                + " vs " + this.targetComparisonValue.getValueName() + ": " + this.targetComparisonValue.getValue());
        this.isListCompare = true;
    }

    public Result compare(){

        if(isListCompare){
            String sourceString = (String) this.sourceComparisonValue.getValue();
            String targetString = (String) this.targetComparisonValue.getValue();

            if (sourceString == null && targetString == null)
                this.result.setResultType(ResultType.MATCH);
            else if (sourceString == null || targetString == null)
                this.result.setResultType(ResultType.MISMATCH);
            else if (sourceString.equals(targetString))
                this.result.setResultType(ResultType.MATCH);
            else
                this.result.setResultType(ResultType.MISMATCH);

        } else {
            if (this.targetComparisonValues.containsAll(this.sourceComparisonValues))
                this.result.setResultType(ResultType.MATCH);
            else
                this.result.setResultType(ResultType.MISMATCH);
        }

        return this.result;
    }

    public ComparisonValue getSourceComparisonValue() {
        return sourceComparisonValue;
    }

    public ComparisonValue getTargetComparisonValue() {
        return targetComparisonValue;
    }

    public List<ComparisonValue> getSourceComparisonValues() {
        return sourceComparisonValues;
    }

    public List<ComparisonValue> getTargetComparisonValues() {
        return targetComparisonValues;
    }

    public boolean isListCompare() {
        return isListCompare;
    }

    public Result getResult() {
        return this.result;
    }

}
