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

//    public static BiFunction<Boolean, Boolean, Result> BooleanComparison() {
//        return (sourceBoolean, targetBoolean) -> {
//            Result result = new Result();
//
//            if (sourceBoolean == targetBoolean)
//                result.setResultType(ResultType.MATCH);
//            else
//                result.setResultType(ResultType.MISMATCH);
//
//            return result;
//        };
//    }
//
//    public static BiFunction<Integer, Integer, Result> IntegerComparison() {
//        return (sourceInt, targetInt) -> {
//            Result result = new Result();
//
//            if (sourceInt == targetInt)
//                result.setResultType(ResultType.MATCH);
//            else
//                result.setResultType(ResultType.MISMATCH);
//
//            return result;
//        };
//    }
//
//    public static BiFunction<BigInteger, BigInteger, Result> BigIntegerComparison() {
//        return (sourceBigInt, targetBigInt) -> {
//            Result result = new Result();
//
//            if (sourceBigInt.equals(targetBigInt))
//                result.setResultType(ResultType.MATCH);
//            else
//                result.setResultType(ResultType.MISMATCH);
//
//            return result;
//        };
//    }
//
//    public static BiFunction<BigDecimal, BigDecimal, Result> BigDecimalComparison() {
//        return (sourceBigDec, targetBigDec) -> {
//            Result result = new Result();
//
//            if (sourceBigDec.equals(targetBigDec))
//                result.setResultType(ResultType.MATCH);
//            else
//                result.setResultType(ResultType.MISMATCH);
//
//            return result;
//        };
//    }
//
//    public static BiFunction<List<Object>, List<List<Object>>, Result> ObjectListComparison() {
//        return (sourceObjects, objectsList) -> {
//            Result result = new Result();
//
//            for (List<Object> list : objectsList) {
//                if (list.containsAll(sourceObjects)) {
//                    result.setResultType(ResultType.MATCH);
//                    return result;
//                }
//            }
//
//            result.setResultType(ResultType.MISMATCH);
//            return result;
//        };
    }

    public static BiFunction<List<Object>, List<Object>, Result> ObjectsListComparison() {
        return (sourceObjects, targetObjects) -> {
            Result result = new Result();

            if (targetObjects.containsAll(sourceObjects)) {
                result.setResultType(ResultType.MATCH);
                return result;
            }

            result.setResultType(ResultType.MISMATCH);
            return result;
        };
    }

}
