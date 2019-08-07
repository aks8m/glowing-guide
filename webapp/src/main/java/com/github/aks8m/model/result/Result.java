package com.github.aks8m.model.result;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private ResultType resultType;
    private String output;
    private String sourceid;
    private List<String> targetid = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    private boolean defect = false;
    private boolean removed = false;

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

    public Result(String output) {
        this.output = output;
        this.resultType = ResultType.SECTIONMATCHNOTFOUND;
    }

    public Result(String output, ResultType resultType, int id) { this.output = output; this.resultType = resultType; this.id = id; }

//    public Result(String output, ResultType resultType, String sourceid, int id) { this.output = output; this.resultType = resultType; this.sourceid = sourceid; this.id = id; }

    public Result(String output, ResultType resultType, String sourceid, String targetid, int id) { this.output = output; this.resultType = resultType; this.sourceid = sourceid; this.targetid.add(targetid); this.id = id; }

    public Result(String output, ResultType resultType, String sourceid, List<String> targetid, int id) { this.output = output; this.resultType = resultType; this.sourceid = sourceid; this.targetid = targetid; this.id = id; }

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
