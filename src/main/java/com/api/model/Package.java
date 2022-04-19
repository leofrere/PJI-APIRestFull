package com.api.model;

import java.io.BufferedReader;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.api.utils.Time;

@Entity
@Table(name = "packages")
public class Package {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String status;

    private String time;

    private String jarPath;

    public Package() {
    }

    public Package(String status, String time, String jarPath) {
        this.status = status;
        this.time = time;
        this.jarPath = jarPath;
    }

    public Package(BufferedReader reader){
        String line = null;
        status = "ko";
        String time1 = "";
        try {
            while ((line = reader.readLine()) != null) {
                if(line.contains("--- maven-install-plugin:")){
                    time = Time.differenceBetween(time1, line.split(" ")[0]);
                    break;
                }
                if (line.contains("Building jar:")) {
                    time1 = line.split(" ")[0];
                    jarPath = line.split(" ")[4];
                    continue;
                }
            }
            status = "ok";
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

}
