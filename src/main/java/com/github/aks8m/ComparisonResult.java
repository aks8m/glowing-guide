package com.github.aks8m;

public class ComparisonResult {

    public String resultString = new String();


    public void addMessage(String realmCode_comparison_error) {
        resultString += realmCode_comparison_error;
    }
}
