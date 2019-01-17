package com.github.aks8m.compare.engine;

import com.github.aks8m.compare.comparisonobject.ComparisonValue;
import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.BiFunction;

public class ComparisonUtility {

    public static BiFunction<ComparisonValue, ComparisonValue, Boolean> StringComparison() {
        return (sourceComparisonValue, targetComparisonValue) -> {
            boolean result;

            String sourceString = (String) sourceComparisonValue.getValue();
            String targetString = (String) targetComparisonValue.getValue();

            if (sourceString == null && targetString == null)
                result = true;
            else if (sourceString == null || targetString == null)
                result = false;
            else if (sourceString.equals(targetString))
                result = true;
            else
                result = false;

            return result;
        };
    }

    public static BiFunction<List<ComparisonValue>, List<ComparisonValue>, Boolean> ObjectsListComparison() {
        return (sourceComparisonValues, targetComparisonValues) -> {
            boolean result;

            if (targetComparisonValues.containsAll(sourceComparisonValues)) {
                result = true;
            } else{
                result = false;
            }

            return result;
        };
    }

}
