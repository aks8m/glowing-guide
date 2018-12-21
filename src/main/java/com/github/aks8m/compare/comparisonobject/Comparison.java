package com.github.aks8m.compare.comparisonobject;

import com.github.aks8m.report.ComparisonLocation;
import com.github.aks8m.report.result.Result;

import java.util.function.BiFunction;

public class Comparison<T,U> {

    private Result result;
    private ComparisonLocation location;
    private final BiFunction<T,U,Result> biFunction;
    private final T source;
    private final U target;

    public Comparison(ComparisonLocation location, BiFunction<T, U, Result> biFunction, T source, U target) {
        this.location = location;
        this.biFunction = biFunction;
        this.source = source;
        this.target = target;
    }

    public Result compare(){
       return this.biFunction.apply(source, target);
    }

    public Comparison setComparisonLocation(ComparisonLocation location) {
        this.location = location;
        return this;
    }

    public T getSource() { return source;}

    public U getTarget() { return target;}


    public String toString() {
        return "SOURCE value: " + source + "\tTARGET value: " + target + "\t LOCATION: " + location.formattedLocation();
    }

}
