package com.github.aks8m.compare.engine;

import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.BiFunction;

public class ComparisonUtility {

    public static BiFunction<String, String, Result> StringComparison() {
        return (sourceString, targetString) -> {
            Result result = new Result();

            if (sourceString == null && targetString == null)
                result.setResultType((ResultType.MATCH));
            else if (sourceString == null || targetString == null)
                result.setResultType(ResultType.MISMATCH);
            else if (sourceString.equals(targetString))
                result.setResultType(ResultType.MATCH);
            else
                result.setResultType(ResultType.MISMATCH);

            return result;
        };
    }

    public static BiFunction<Boolean, Boolean, Result> BooleanComparison() {
        return (sourceBoolean, targetBoolean) -> {
            Result result = new Result();

            if (sourceBoolean == targetBoolean)
                result.setResultType(ResultType.MATCH);
            else
                result.setResultType(ResultType.MISMATCH);

            return result;
        };
    }

    public static BiFunction<Integer, Integer, Result> IntegerComparison() {
        return (sourceInt, targetInt) -> {
            Result result = new Result();

            if (sourceInt == targetInt)
                result.setResultType(ResultType.MATCH);
            else
                result.setResultType(ResultType.MISMATCH);

            return result;
        };
    }

    public static BiFunction<BigInteger, BigInteger, Result> BigIntegerComparison() {
        return (sourceBigInt, targetBigInt) -> {
            Result result = new Result();

            if (sourceBigInt.equals(targetBigInt))
                result.setResultType(ResultType.MATCH);
            else
                result.setResultType(ResultType.MISMATCH);

            return result;
        };
    }

    public static BiFunction<BigDecimal, BigDecimal, Result> BigDecimalComparison() {
        return (sourceBigDec, targetBigDec) -> {
            Result result = new Result();

            if (sourceBigDec.equals(targetBigDec))
                result.setResultType(ResultType.MATCH);
            else
                result.setResultType(ResultType.MISMATCH);

            return result;
        };
    }

    public static BiFunction<List<String>, List<List<String>>, Result> StringListComparison() {
        return (sourceStrings, stringsList) -> {
            Result result = new Result();

            for (List<String> list : stringsList) {
                //change this - requirement is not to be equal but instead to contain all source elements
                if (sourceStrings.equals(list)) {
                    result.setResultType(ResultType.MATCH);
                    return result;
                }
            }

            result.setResultType(ResultType.MISMATCH);
            return result;
        };
    }

}
