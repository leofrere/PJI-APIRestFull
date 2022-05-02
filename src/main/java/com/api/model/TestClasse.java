package com.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.api.model.interfaces.Test;

@Entity
@Table(name = "tests_classes")
public class TestClasse implements Test {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String classe;

    @NotNull
    private String project;

    @NotNull
    private int build;

    private String time;

    private int testsRun;

    private int testsFailed;

    private int testsSkipped;

    private int testsError;

    public TestClasse() {
    }

    public TestClasse(String classe, String time, int testsRun, int testsFailed, int testsSkipped, int testsError) {
        this.classe = classe;
        this.time = time;
        this.testsRun = testsRun;
        this.testsFailed = testsFailed;
        this.testsSkipped = testsSkipped;
        this.testsError = testsError;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTestsRun() {
        return testsRun;
    }

    public void setTestsRun(int testsRun) {
        this.testsRun = testsRun;
    }

    public int getTestsFailed() {
        return testsFailed;
    }

    public void setTestsFailed(int testsFailed) {
        this.testsFailed = testsFailed;
    }

    public int getTestsSkipped() {
        return testsSkipped;
    }

    public void setTestsSkipped(int testsSkipped) {
        this.testsSkipped = testsSkipped;
    }

    public int getTestsError() {
        return testsError;
    }

    public void setTestsError(int testsError) {
        this.testsError = testsError;
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

    public float getTimeFloat() {
        if(time == null){
            return 0;
        }
        return Float.parseFloat(time.replace(",", ".").replace("s", ""));
    }

}
