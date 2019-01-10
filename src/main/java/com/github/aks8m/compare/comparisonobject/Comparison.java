package com.github.aks8m.compare.comparisonobject;

import com.github.aks8m.report.ComparisonLocation;
import com.github.aks8m.report.result.Result;
import com.github.aks8m.report.result.ResultType;

import java.util.List;
import java.util.function.BiFunction;

public class Comparison<T> {

    private final Result result;
    private ComparisonLocation location;
    private final BiFunction<T,T,Boolean> biFunction;
    private final T source;
    private final T target;

    public Comparison(ComparisonLocation location, BiFunction<T, T, Boolean> biFunction, T source, T target) {
        this.location = location;
        this.biFunction = biFunction;
        this.source = source;
        this.target = target;
        this.result = new Result("SOURCE value: " + source + "\tTARGET value: " + target);
    }

    public Result compare(){

        if(this.biFunction.apply(this.source, this.target))
            this.result.setResultType(ResultType.MATCH);
        else
            this.result.setResultType(ResultType.MISMATCH);

        return this.result;

    }

    public T getSource() {
        return source;
    }

    public T getTarget() {
        return target;
    }

    public Result getResult() {
        return this.result;
    }

}
