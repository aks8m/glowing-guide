package com.github.aks8m.compare.report;

import java.util.Stack;

public class Location {

    private Stack<String> locationStack = new Stack<>();

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
