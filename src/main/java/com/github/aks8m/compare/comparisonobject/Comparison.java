package com.github.aks8m.compare.comparisonobject;

import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultType;

import java.util.ArrayList;
import java.util.List;

public class Comparison {

    private final List<Result> results = new ArrayList<>();
    private ComparisonValue sourceComparisonValue;
    private ComparisonValue targetComparisonValue;
    private List<ComparisonValue> sourceComparisonValues;
    private List<ComparisonValue> targetComparisonValues;
    private final boolean isListCompare;

    public Comparison(ComparisonValue sourceComparisonValue, ComparisonValue targetComparisonValue) {
        this.sourceComparisonValue = sourceComparisonValue;
        this.targetComparisonValue = targetComparisonValue;
        this.isListCompare = false;
    }

    public Comparison(List<ComparisonValue> sourceComparisonValues, List<ComparisonValue> targetComparisonValues) {
        this.sourceComparisonValues = sourceComparisonValues;
        this.targetComparisonValues = targetComparisonValues;
        this.isListCompare = true;
    }

    public List<Result> compare(){

        try {

            if (!isListCompare) {

                Result tempResult = new Result(this.sourceComparisonValue.getValueName() + ": " + this.sourceComparisonValue.getValue()
                        + " vs " + this.targetComparisonValue.getValueName() + ": " + this.targetComparisonValue.getValue());

                String sourceString = this.sourceComparisonValue.getValue().toString();
                String targetString = this.targetComparisonValue.getValue().toString();

//                System.out.println(sourceString + " vs " + targetString);

                if (sourceString == null && targetString == null)
                    tempResult.setResultType(ResultType.MATCH);
                else if (sourceString == null || targetString == null)
                    tempResult.setResultType(ResultType.MISMATCH);
                else if (sourceString.equals(targetString))
                    tempResult.setResultType(ResultType.MATCH);
                else
                    tempResult.setResultType(ResultType.MISMATCH);
                this.results.add(tempResult);

            } else {

                for (int i=0; i<this.sourceComparisonValues.size();i++) {
                    Result tempResult = new Result(sourceComparisonValues.get(i).getValueName() + ": " + sourceComparisonValues.get(i).getValue()
                                + " vs " + targetComparisonValues.get(i).getValueName() + ": " + targetComparisonValues.get(i).getValue());

//                    System.out.println(sourceComparisonValues.get(i).getValue() + " vs " + targetComparisonValues.get(i).getValue());

                    if (sourceComparisonValues.get(i).getValue() == null && targetComparisonValues.get(i).getValue() == null)
                        tempResult.setResultType(ResultType.MATCH);
                    else if (sourceComparisonValues.get(i).getValue() == null || targetComparisonValues.get(i).getValue() == null)
                        tempResult.setResultType(ResultType.MISMATCH);
                    else if (targetComparisonValues.get(i).getValue().equals(sourceComparisonValues.get(i).getValue()))
                        tempResult.setResultType(ResultType.MATCH);
                    else
                        tempResult.setResultType(ResultType.MISMATCH);

                    this.results.add(tempResult);
                }

//                for (ComparisonValue sourceCompValue : this.sourceComparisonValues) {
//                    for (ComparisonValue targetCompValue : this.targetComparisonValues) {
//                        Result tempResult = new Result(sourceCompValue.getValueName() + ": " + sourceCompValue.getValue()
//                                + " vs " + targetCompValue.getValueName() + ": " + targetCompValue.getValue());
//
//                        System.out.println(sourceCompValue.getValue() + " vs " + targetCompValue.getValue());
//
//                        if (sourceCompValue.getValue() == null && targetCompValue.getValue() == null)
//                            tempResult.setResultType(ResultType.MATCH);
//                        else if (sourceCompValue.getValue() == null || targetCompValue.getValue() == null)
//                            tempResult.setResultType(ResultType.MISMATCH);
//                        else if (targetCompValue.getValue().equals(sourceCompValue.getValue()))
//                            tempResult.setResultType(ResultType.MATCH);
//                        else
//                            tempResult.setResultType(ResultType.MISMATCH);
//
//                        this.results.add(tempResult);
//                    }
//                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return this.results;
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

    public List<Result> getResults() {
        return results;
    }
}
