package com.api.model;

import java.io.BufferedReader;

import javax.persistence.*;

import com.api.model.abstracts.Phase;
import com.api.model.interfaces.Compile;
import com.api.utils.Time;

@Entity
@Table(name = "compiles")
public class CompilePhase extends Phase implements Compile {

    private int numberOfClasses;

    public CompilePhase() {
    }

    public CompilePhase(String status, String time, int numberOfClasses) {
        this.status = status;
        this.time = time;
        this.numberOfClasses = numberOfClasses;
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
                    numberOfClasses = Integer.parseInt(parts[3]);
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(int numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

    @Override
    public int getCompiledClasses() {
        return numberOfClasses;
    }

}
