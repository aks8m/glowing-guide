package com.github.aks8m.compare.comparisonobject;

public class ComparisonValue {

    private Object value;
    private String valueName;

    public ComparisonValue(Object value, String valueName) {
        this.value = value;
        this.valueName = valueName;
    }

    public Object getValue() {
        return value;
    }

    public String getValueName() {

        StringBuilder stringBuilder = new StringBuilder();

        String[] splitArray = this.valueName.replace("get" , "").split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");

        for(int i = 0; i < splitArray.length - 1; i++){
            stringBuilder.append(splitArray[1] + " ");
        }

        return stringBuilder.append(splitArray[splitArray.length - 1]).toString();
    }

    @Override
    public boolean equals(Object obj) {
        return this.value.equals(obj);
    }
}
