package com.api.model.abstracts;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Time extends Model {
    
    protected String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getTimeFloat() {
        if(time == null){
            return 0;
        }
        return Float.parseFloat(time.replace(",", ".").replace("s", ""));
    }

}
