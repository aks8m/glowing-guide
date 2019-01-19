package com.github.aks8m.report.result;

import javafx.beans.property.SimpleBooleanProperty;

public class Result {

    private SimpleBooleanProperty isSelected = new SimpleBooleanProperty(false);
    private ResultType resultType;
    private String output;

    public Result(String output) {
        this.output = output;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public ResultType getResultType() { return resultType; }

    public boolean isIsSelected() {
        return isSelected.get();
    }

    public SimpleBooleanProperty isSelectedProperty() {
        return isSelected;
    }

    @Override
    public String toString() {
        return this.output;
    }
}
