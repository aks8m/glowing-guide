package com.github.aks8m.model.result;

import javafx.beans.property.SimpleBooleanProperty;

public class Result {

    private ResultType resultType;
    private String output;

    public Result(String output) {
        this.output = output;
        this.resultType = ResultType.SECTIONMATCHNOTFOUND;
    }

    public Result(String output, ResultType resultType) { this.output = output; this.resultType = resultType; }

    public Result() { }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public ResultType getResultType() { return resultType; }

    @Override
    public String toString() {
        return this.output;
    }
}
