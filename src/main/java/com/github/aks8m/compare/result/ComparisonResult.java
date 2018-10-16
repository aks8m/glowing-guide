package com.github.aks8m.compare.result;

public class ComparisonResult {

    public String resultString = new String();


    public void addMessage(String comparison_error) {
        resultString += comparison_error;
    }

}
