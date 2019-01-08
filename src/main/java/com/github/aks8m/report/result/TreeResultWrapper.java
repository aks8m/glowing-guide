package com.github.aks8m.report.result;

import javafx.beans.property.SimpleBooleanProperty;

public class TreeResultWrapper {

    private final String label;

    public TreeResultWrapper(String label, SimpleBooleanProperty simpleBooleanProperty) {
        this.label = label;

        if(simpleBooleanProperty != null)
            simpleBooleanProperty.addListener((observable, oldValue, newValue) -> System.out.println(newValue));

    }

    @Override
    public String toString() {
        return this.label;
    }
}
