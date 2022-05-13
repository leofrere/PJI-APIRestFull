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
        int offset = 0;
        String line = null;
        status = "ko";
        boolean secondErrorLine = false;
        String time1 = "";
        try {
            // pass validate phase
            while ((line = reader.readLine()) != null) {

                if(line.contains("BUILD SUCCESS") || line.contains("BUILD FAILURE")){
                    break;
                }

                if(line.contains("maven-resources-plugin:")){
                    time1 = line.split(" ")[0];
                    break;
                }
            }

            while ((line = reader.readLine()) != null) {

                if(line.contains("BUILD SUCCESS") || line.contains("BUILD FAILURE") || line.contains("--[ jar ]--")){
                    status = "finished";
                    time = Time.differenceBetween(time1, line.split(" ")[0]);
                    break;
                }

                if(line.contains("maven-resources-plugin:") && line.contains("test")){
                    time = Time.differenceBetween(time1, line.split(" ")[0]);
                    status = "ok";
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

                if(line.contains("Compiling") && line.contains("INFO")){
                    String parts[] = line.split(" ");
                    System.out.println(parts);
                    numberOfCompiledClasses = Integer.parseInt(parts[2 + offset]);
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
