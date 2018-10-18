package com.github.aks8m.compare.report.result;

public class Warning {

    private final String message;
    private final String sourceValue;
    private final String targetValue;

    public Warning(String message, String sourceValue, String targetValue) {
        this.message = message;
        this.sourceValue = sourceValue;
        this.targetValue = targetValue;
    }

    @Override
    public String toString() {
        return "Warning: " + this.message + " Source Value = " + this.sourceValue + " Target Value = " + this.targetValue;
    }
}
