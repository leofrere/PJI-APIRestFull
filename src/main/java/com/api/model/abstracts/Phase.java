package com.api.model.abstracts;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Phase extends TimeAndModule {

    protected String status;

    protected String errorsTrace = "";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorsTrace() {
        return errorsTrace;
    }

    public void setErrorsTrace(String errorsTrace) {
        this.errorsTrace = errorsTrace;
    }

}

