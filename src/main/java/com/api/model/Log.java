package com.api.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "logs")
public class Log {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String project;

    @NotNull
    private int build;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Order> logs;

    public Log(int build) {
        this.build = build;
    }

    public Log(String project, int build, List<Order> logs) {
        this.project = project;
        this.build = build;
        this.logs = logs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Order> getLogs() {
        return logs;
    }

    public void setLogs(List<Order> logs) {
        this.logs = logs;
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

}
