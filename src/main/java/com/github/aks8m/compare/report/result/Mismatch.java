package com.github.aks8m.compare.report.result;

public class Mismatch {

    private final String message;
    private final String sourceValue;
    private final String targetValue;

    public Mismatch(String message, String sourceValue, String targetValue) {
        this.message = message;
        this.sourceValue = sourceValue;
        this.targetValue = targetValue;
    }

    @Override
    public String toString() {
        return "Mismatch: " + this.message + " Source Value = " + this.sourceValue + " Target Value = " + this.targetValue;
    }
}
