package com.api.model;

import java.io.BufferedReader;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.api.model.interfaces.Phase;
import com.api.utils.Time;

@Entity
@Table(name = "packages")
public class PackagePhase implements Phase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String status;

    private String time;

    private String jarPath;

    private String errorsTrace = "";

    public PackagePhase() {
    }

    public PackagePhase(String status, String time, String jarPath) {
        this.status = status;
        this.time = time;
        this.jarPath = jarPath;
    }

    public PackagePhase(BufferedReader reader){
        String line = null;
        status = "ko";
        boolean secondErrorLine = false;
        String time1 = "";
        try {
            while ((line = reader.readLine()) != null) {
                if(line.contains("--- maven-install-plugin:")){
                    time = Time.differenceBetween(time1, line.split(" ")[0]);
                    status = "ok";
                    break;
                }

                if(line.contains("[ERROR] ")){
                    if(secondErrorLine && errorsTrace.length() == 0){
                        errorsTrace = line;
                        continue;
                    }
                    secondErrorLine = true;
                    continue;
                }

                if (line.contains("Building jar:")) {
                    time1 = line.split(" ")[0];
                    jarPath = line.split(" ")[4];
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getJarPath() {
        return jarPath;
    }

    public void setJarPath(String jarPath) {
        this.jarPath = jarPath;
    }

    public String getErrorsTrace() {
        return errorsTrace;
    }

    public void setErrorsTrace(String errorsTrace) {
        this.errorsTrace = errorsTrace;
    }

}
