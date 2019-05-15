package com.github.aks8m.model.result;

public class Result {

    private ResultType resultType;
    private String output;
    private String sourceid;
    private String targetid;

    public Result(String output) {
        this.output = output;
        this.resultType = ResultType.SECTIONMATCHNOTFOUND;
    }

    public Result(String output, ResultType resultType) { this.output = output; this.resultType = resultType; }

    public Result(String output, ResultType resultType, String sourceid, String targetid) { this.output = output; this.resultType = resultType; this.sourceid = sourceid; this.targetid = targetid; }

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
