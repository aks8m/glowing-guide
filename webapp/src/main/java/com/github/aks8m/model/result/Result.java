package com.github.aks8m.model.result;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private ResultType resultType;
    private String output;

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public List<String> getTargetid() {
        return targetid;
    }

    public void addTargetid(String targetid) {
        this.targetid.add(targetid);
    }

    private String sourceid;
    private List<String> targetid = new ArrayList<>();

    public Result(String output) {
        this.output = output;
        this.resultType = ResultType.SECTIONMATCHNOTFOUND;
    }

    public Result(String output, ResultType resultType) { this.output = output; this.resultType = resultType; }

    public Result(String output, ResultType resultType, String sourceid) { this.output = output; this.resultType = resultType; this.sourceid = sourceid; }

    public Result(String output, ResultType resultType, String sourceid, String targetid) { this.output = output; this.resultType = resultType; this.sourceid = sourceid; this.targetid.add(targetid); }

    public Result(String output, ResultType resultType, String sourceid, List<String> targetid) { this.output = output; this.resultType = resultType; this.sourceid = sourceid; this.targetid = targetid; }

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
