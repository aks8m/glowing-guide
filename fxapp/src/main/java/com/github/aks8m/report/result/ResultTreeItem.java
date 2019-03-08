package com.github.aks8m.report.result;

import com.github.aks8m.tree.XMLElement;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.List;

public class ResultTreeItem extends TreeItem<String> {

//    private SimpleBooleanProperty isSelected;

    private List<SimpleBooleanProperty> isSelectedList = new ArrayList<>();

    public ResultTreeItem(String label) {
        this(label, new SimpleBooleanProperty(false));
    }


    public ResultTreeItem(String label, SimpleBooleanProperty simpleBooleanProperty) {
        super(label);
//        this.isSelected = simpleBooleanProperty;
        this.isSelectedList.add(simpleBooleanProperty);
    }

//    public SimpleBooleanProperty getIsSelected(){ return this.isSelected; }
    public SimpleBooleanProperty getIsSelected(){
        for (SimpleBooleanProperty sbp : isSelectedList) {
            if (sbp.getValue()) {
                return sbp;
            }
        }
        return (isSelectedList == null)? null : isSelectedList.get(0);
    }


    public void setIsSelected(SimpleBooleanProperty isSelected) {
//        this.isSelected = isSelected;
//        this.isSelected.addListener((observable, oldValue, newValue) -> {
//
//            TreeItem parent = null;
//
//            if (newValue) {
//                do {
//                    this.setExpanded(true);
//
//                    if(parent == null)
//                        parent = super.getParent();
//                    else
//                        parent = parent.getParent();
//
//                    parent.setExpanded(true);
//                }while (parent.getParent() != null);
//            } else {
//                do {
//                    this.setExpanded(false);
//
//                    if(parent == null)
//                        parent = super.getParent();
//                    else
//                        parent = parent.getParent();
//
//                    parent.setExpanded(false);
//
//                }while (parent.getParent() != null);
//            }
//
//        });

        this.isSelectedList.add(isSelected);
        isSelected.addListener((observable, oldValue, newValue) -> {

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

    //    public void setItSelected(SimpleBooleanProperty sbp) {
//        this.isSelected = sbp;
//    }

}
