package com.github.aks8m.report.result;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TreeItem;

import java.util.concurrent.atomic.AtomicInteger;

public class ResultTreeItem extends TreeItem<String> {

    private final String label;
    private final AtomicInteger position = new AtomicInteger();

    public ResultTreeItem(String label) {
        super(label);
        this.label = label;
    }

    public ResultTreeItem(String label, SimpleBooleanProperty simpleBooleanProperty, int position) {
        super(label);
        this.label = label;
        this.position.set(position);

        simpleBooleanProperty.addListener((observable, oldValue, newValue) -> {

            TreeItem parent = null;

            do {
                if(parent == null)
                    parent = super.getParent();
                else
                    parent = parent.getParent();

                parent.setExpanded(true);
            }while (parent.getParent() != null);
        });
    }

    public AtomicInteger getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
