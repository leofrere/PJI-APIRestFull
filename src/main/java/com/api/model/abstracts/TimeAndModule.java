package com.api.model.abstracts;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TimeAndModule extends Model {
    
    protected String time;

    protected String module;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public float getTimeFloat() {
        if(time == null){
            return 0;
        }
        return Float.parseFloat(time.replace(",", ".").replace("s", ""));
    }

}
