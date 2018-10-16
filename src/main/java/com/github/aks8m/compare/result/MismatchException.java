package com.github.aks8m.compare.result;

public class MismatchException extends RuntimeException {

    private String comparisonErrorMessage = null;

    public MismatchException(String comparisonErrorMessage){
        this.comparisonErrorMessage = comparisonErrorMessage;
    }

    public MismatchException(String comparisonErrorMessage, MismatchException mismatchException ){

    }


    @Override
    public String getMessage() {
        return this.comparisonErrorMessage;
    }
}
