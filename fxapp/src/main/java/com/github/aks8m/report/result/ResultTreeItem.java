package com.github.aks8m.report.result;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TreeItem;

import java.util.concurrent.atomic.AtomicInteger;

public class ResultTreeItem extends TreeItem<String> {

    private final String label;
    private final AtomicInteger position = new AtomicInteger();
    private SimpleBooleanProperty isSelected = new SimpleBooleanProperty(false);

    public ResultTreeItem(String label) {
        super(label);
        this.label = label;
    }


    public ResultTreeItem(String label, SimpleBooleanProperty simpleBooleanProperty) {
        this(label,simpleBooleanProperty,0);
    }



    public ResultTreeItem(String label, SimpleBooleanProperty simpleBooleanProperty, int position) {
        super(label);
        this.label = label;
        this.position.set(position);
        this.isSelected = simpleBooleanProperty;

        simpleBooleanProperty.addListener((observable, oldValue, newValue) -> {

            TreeItem parent = null;

            if (newValue) {
                do {
                    this.setExpanded(true);

                    if(parent == null)
                        parent = super.getParent();
                    else
                        parent = parent.getParent();

                    parent.setExpanded(true);
                }while (parent.getParent() != null);
            } else {
                do {
                    this.setExpanded(false);

                    if(parent == null)
                        parent = super.getParent();
                    else
                        parent = parent.getParent();

                    parent.setExpanded(false);

                }while (parent.getParent() != null);
            }

        });

    }

    public AtomicInteger getPosition() {
        return position;
    }

    public SimpleBooleanProperty getIsSelected(){ return this.isSelected; }

    @Override
    public String toString() {
        return this.label;
    }
}
