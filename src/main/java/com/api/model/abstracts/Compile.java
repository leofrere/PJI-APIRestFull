package com.api.model.abstracts;

public abstract class Compile extends Phase {

    protected int numberOfCompiledClasses;

    public int getCompiledClasses() {
        return numberOfCompiledClasses;
    }

    public void setCompiledClasses(int numberOfCompiledClasses) {
        this.numberOfCompiledClasses = numberOfCompiledClasses;
    }

}
