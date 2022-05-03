package com.api.model;

import java.util.List;

import javax.persistence.*;

import com.api.model.abstracts.Model;

@Entity
@Table(name = "logs")
public class Log extends Model {

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Order> orders;

    public Log() {
    }

    public Log(int build) {
        this.build = build;
    }

    public Log(String project, int build, List<Order> orders) {
        this.project = project;
        this.build = build;
        this.orders = orders;
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
