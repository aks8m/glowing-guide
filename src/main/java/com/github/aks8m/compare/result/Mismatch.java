package com.github.aks8m.compare.result;

public class Mismatch {

    private final String mismatchDescription;

    public Mismatch(String mismatchDescription) {
        this.mismatchDescription = mismatchDescription;
    }

    public String getMismatchDescription() {
        return mismatchDescription;
    }
}
