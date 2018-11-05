package com.github.aks8m.report;

import java.util.Stack;

public class ComparisonLocation {

    private Stack<String> locationStack;

    public ComparisonLocation() {
        locationStack = new Stack<>();
    }

    public void enter(String location){
        this.locationStack.push(location);
    }

    public void exit(){
        this.locationStack.pop();
    }

    public String formattedLocation(){

        final StringBuilder outputBuilder = new StringBuilder();




        return outputBuilder.toString();
    }

}
