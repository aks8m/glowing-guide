package com.github.aks8m.compare.engine;

import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultType;

import java.util.function.BiFunction;

public class ComparisonUtility {

    public static BiFunction<String, String, Result> StringComparison(){
        return (sourceString, targetString) -> {
            Result result = new Result();

            if(sourceString.equals(targetString))
                result.setResultType(ResultType.MATCH);
            else
                result.setResultType(ResultType.MISMATCH);

            return  result;
        };
    }

    public static BiFunction<Boolean, Boolean, Result> BooleanComparison(){
        return (sourceBoolean, targetBoolean) -> {
            Result result = new Result();

            if(sourceBoolean == targetBoolean)
                result.setResultType(ResultType.MATCH);
            else
                result.setResultType(ResultType.MISMATCH);

            return  result;
        };
    }
}
