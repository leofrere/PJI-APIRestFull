package com.api.model;

import java.util.List;

import javax.persistence.*;

import com.api.model.abstracts.Model;

@Entity
@Table(name = "logs")
public class Log extends Model {

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Order> orders;

    private float timeOfBuild;

    private String status = "ABORTED";

    public Log() {
    }

    public Log(int build) {
        this.build = build;
    }

    public Log(String project, int build, List<Order> orders, float timeOfBuild, String status) {
        this.project = project;
        this.build = build;
        this.orders = orders;
        this.timeOfBuild = timeOfBuild;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public float getTimeOfBuild() {
        return timeOfBuild;
    }

    public void setTimeOfBuild(float timeOfBuild) {
        this.timeOfBuild = timeOfBuild;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
