package com.api.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "logs")
public class Log {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Order> logs;

    public Log() {
    }

    public Log(List<Order> logs) {
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

}
