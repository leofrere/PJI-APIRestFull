package com.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "builds")
public class JenkinsBuild {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String url;

    @NotNull
    @Column(unique=true)
    private String projectName;

    @NotNull
    private String path;

    private String buildType;

    private String username;

    private String password;

    private boolean timeIsSet;

    public JenkinsBuild() {
    }

    public JenkinsBuild(String url, String projectName, String username, String password, String path, String buildType, boolean timeIsSet) {
        this.url = url;
        this.projectName = projectName;
        this.username = username;
        this.password = password;
        this.path = path;
        this.buildType = buildType;
        this.timeIsSet = timeIsSet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public boolean getTimeIsSet() {
        return timeIsSet;
    }

    public void setTimeIsSet(boolean timeIsSet) {
        this.timeIsSet = timeIsSet;
    }

}
