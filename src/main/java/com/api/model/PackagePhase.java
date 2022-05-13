package com.api.model;

import java.io.BufferedReader;

import javax.persistence.*;

import com.api.model.abstracts.Phase;
import com.api.utils.Time;

@Entity
@Table(name = "packages")
public class PackagePhase extends Phase {

    private String jarPath;

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
        int offset = 0;
        boolean secondErrorLine = false;
        String time1 = "";
        try {
            while ((line = reader.readLine()) != null) {

                if(line.contains("maven-install-plugin:") || line.contains("BUILD SUCCESS") || line.contains("--[ jar ]--") || (line.contains("Summary") && line.contains("INFO"))){
                    time = Time.differenceBetween(time1, line.split(" ")[0]);
                    status = "finished";
                    break;
                }

                if(line.contains("ERROR ")){
                    if(secondErrorLine && errorsTrace.length() == 0){
                        errorsTrace = line;
                        continue;
                    }
                    secondErrorLine = true;
                    continue;
                }

                if (line.contains("Building jar:")) {
                    time1 = line.split(" ")[0];
                    jarPath = line.split(" ")[3 + offset];
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getJarPath() {
        return jarPath;
    }

    public void setJarPath(String jarPath) {
        this.jarPath = jarPath;
    }

}
