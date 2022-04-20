package com.api.model;

import java.io.BufferedReader;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//import com.api.utils.Time;

@Entity
@Table(name = "compiles")
public class Compile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String status;

    private String time;

    private int numberOfClasses;

    public Compile() {
    }

    public Compile(String status, String time, int numberOfClasses) {
        this.status = status;
        this.time = time;
        this.numberOfClasses = numberOfClasses;
    }

    public Compile(BufferedReader reader){
        String line = null;
        status = "ko";
        int tmp = 1;
        //String time1 = "";
        try {
            // pass validate phase
            while ((line = reader.readLine()) != null) {
                if(line.contains("--- maven-resources-plugin:")){
                    //time1 = line.split(" ")[0];
                    break;
                }
            }

            while ((line = reader.readLine()) != null) {

                if(line.contains("--- maven-resources-plugin:")){
                    //time = Time.differenceBetween(time1, line.split(" ")[0]);
                    break;
                }

                if(line.contains("[INFO] Compiling")){
                    String parts[] = line.split(" ");
                    numberOfClasses = Integer.parseInt(parts[3 - tmp]);
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

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(int numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

}
