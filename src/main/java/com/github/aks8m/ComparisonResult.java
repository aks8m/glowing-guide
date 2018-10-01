package com.github.aks8m;

public class ComparisonResult {

    public String resultString = new String();


    public void addMessage(String comparison_error) {
        resultString += comparison_error;
    }
}
