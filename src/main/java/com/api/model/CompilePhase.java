package com.api.model;

import java.io.BufferedReader;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.api.model.interfaces.Compile;
import com.api.model.interfaces.Phase;
import com.api.utils.Time;

@Entity
@Table(name = "compiles")
public class CompilePhase implements Phase, Compile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String status;

    @NotNull
    private String project;

    @NotNull
    private int build;

    private String time;

    private int numberOfClasses;

    private String errorsTrace = "";

    public CompilePhase() {
    }

    public CompilePhase(String status, String time, int numberOfClasses) {
        this.status = status;
        this.time = time;
        this.numberOfClasses = numberOfClasses;
    }

    public CompilePhase(BufferedReader reader){
        String line = null;
        status = "ko";
        boolean secondErrorLine = false;
        String time1 = "";
        try {
            // pass validate phase
            while ((line = reader.readLine()) != null) {
                if(line.contains("--- maven-resources-plugin:")){
                    time1 = line.split(" ")[0];
                    break;
                }
            }

            while ((line = reader.readLine()) != null) {

                if(line.contains("--- maven-resources-plugin:")){
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

                if(line.contains("[INFO] Compiling")){
                    String parts[] = line.split(" ");
                    numberOfClasses = Integer.parseInt(parts[3]);
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

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(int numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

    public String getErrorsTrace() {
        return errorsTrace;
    }

    public void setErrorsTrace(String errorsTrace) {
        this.errorsTrace = errorsTrace;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public int getBuild() {
        return build;
    }

    public void setBuild(int build) {
        this.build = build;
    }

    @Override
    public int getCompiledClasses() {
        return numberOfClasses;
    }

    public float getTimeFloat() {
        if(time == null){
            return 0;
        }
        return Float.parseFloat(time.replace(",", ".").replace("s", ""));
    }

}
