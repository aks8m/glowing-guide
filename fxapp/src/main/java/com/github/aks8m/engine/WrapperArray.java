package com.github.aks8m.engine;

import java.util.ArrayList;

public class WrapperArray<T> {

    private ArrayList<T> arrayList;

    public WrapperArray() {
        this.arrayList = new ArrayList<>();
    }

    public WrapperArray<T> add(T tObject){
        this.arrayList.add(tObject);
        return this;
    }

    public ArrayList<T> getArrayList() {
        return arrayList;
    }
}
