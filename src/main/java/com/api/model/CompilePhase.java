package com.api.model;

import java.io.BufferedReader;

import javax.persistence.*;

import com.api.model.abstracts.Compile;
import com.api.utils.Time;

@Entity
@Table(name = "compiles")
public class CompilePhase extends Compile {

    public CompilePhase() {
    }

    public CompilePhase(String status, String time, int numberOfClasses) {
        this.status = status;
        this.time = time;
        this.numberOfCompiledClasses = numberOfClasses;
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
                    numberOfCompiledClasses = Integer.parseInt(parts[3]);
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
