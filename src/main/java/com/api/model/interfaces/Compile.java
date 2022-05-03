package com.api.model.interfaces;

import com.api.model.abstracts.Phase;

public abstract class Compile extends Phase {

    protected int numberOfCompiledClasses;

    public int getCompiledClasses() {
        return numberOfCompiledClasses;
    }

    public void setCompiledClasses(int numberOfCompiledClasses) {
        this.numberOfCompiledClasses = numberOfCompiledClasses;
    }

}
