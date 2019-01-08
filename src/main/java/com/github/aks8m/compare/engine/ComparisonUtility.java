package com.github.aks8m.compare.engine;

import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.BiFunction;

public class ComparisonUtility {

    public static BiFunction<String, String, Boolean> StringComparison() {
        return (sourceString, targetString) -> {
            boolean result;

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

    public static BiFunction<List<Object>, List<Object>, Boolean> ObjectsListComparison() {
        return (sourceObjects, targetObjects) -> {
            boolean result;

            if (targetObjects.containsAll(sourceObjects)) {
                result = true;
            } else{
                result = false;
            }

            return result;
        };
    }

}
