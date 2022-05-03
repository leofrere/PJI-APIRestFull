package com.api.model.abstracts;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Compile extends Phase {

    protected int numberOfCompiledClasses;

    public int getCompiledClasses() {
        return numberOfCompiledClasses;
    }

    public void setCompiledClasses(int numberOfCompiledClasses) {
        this.numberOfCompiledClasses = numberOfCompiledClasses;
    }

}
