package com.github.aks8m.compare.report.result;

public class Match {

    private final String message;
    private final String sourceValue;
    private final String targetValue;

    public Match(String message, String sourceValue, String targetValue) {
        this.message = message;
        this.sourceValue = sourceValue;
        this.targetValue = targetValue;
    }

    @Override
    public String toString() {
        return "Match: " + this.message + " Source Value = " + this.sourceValue + " Target Value = " + this.targetValue;
    }
}
