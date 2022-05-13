package com.api.model;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import com.api.model.abstracts.Compile;
import com.api.model.interfaces.Test;
import com.api.utils.Time;

@Entity
@Table(name = "tests")
public class TestPhase extends Compile implements Test {

    private int testsRun;

    private int testsFailed;

    private int testsSkipped;

    private int testsError;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<TestClasse> testsByClasse;

    public TestPhase() {
    }

    public TestPhase(String status, String time, int testsRun, int testsFailed, int testsSkipped, int testsPassed, int testsError, int numberOfTestClasses) {
        this.status = status;
        this.time = time;
        this.testsRun = testsRun;
        this.testsFailed = testsFailed;
        this.testsSkipped = testsSkipped;
        this.testsError = testsError;
        this.numberOfCompiledClasses = numberOfTestClasses;
    }

    public TestPhase(BufferedReader reader) {
        int offset = 1;
        String line = null;
        testsByClasse = new LinkedList<TestClasse>();
        status = "ko";
        boolean secondErrorLine = false;
        String time1 = "";
        String testClass = "";
        try {
            while ((line = reader.readLine()) != null) {

                if(line.contains("BUILD SUCCESS") || line.contains("BUILD FAILURE") || line.contains("--[ jar ]--") || (line.contains("Summary") && line.contains("INFO"))){
                    status = "finished";
                    time = Time.differenceBetween(time1, line.split(" ")[0]);
                    break;
                }

                //System.out.println(line);
                if(line.contains("maven-jar-plugin:")){
                    time = Time.differenceBetween(time1, line.split(" ")[0]);
                    status = "ok";
                    break;
                }

                if(line.contains("Compiling") && line.contains("INFO")){
                    String parts[] = line.split(" ");
                    time1 = parts[0];
                    numberOfCompiledClasses = Integer.parseInt(parts[2 + offset]);
                    continue;
                }

                if(line.contains("Tests run:")  && (line.contains("INFO") || line.contains("ERROR"))){
                    testClass = parseTestsInformation(line, testClass, 2);
                    continue;
                }

                if(line.contains("Running ")&& (line.contains("INFO") || line.contains("ERROR"))){
                    testClass = line.split(" ")[2 + offset];
                }

                if(line.contains("ERROR ")){
                    if(secondErrorLine && errorsTrace.length() == 0){
                        errorsTrace = line;
                        continue;
                    }
                    secondErrorLine = true;
                    continue;
                }

                if(line.contains("Running ")){
                    testClass = line.split(" ")[1];
                }

                if(line.contains("Tests run:")){
                    testClass = parseTestsInformation(line, testClass, 0);
                    continue;
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String parseTestsInformation(String line, String testClass, int offset) {
        String parts[] = line.split(" ");
        testsRun = Integer.parseInt(parts[2+offset].substring(0, parts[2+offset].length()-1));
        testsFailed = Integer.parseInt(parts[4+offset].substring(0, parts[4+offset].length()-1));
        testsError = Integer.parseInt(parts[6+offset].substring(0, parts[6+offset].length()-1));
        if(parts[8+offset].contains(",")){
            testsSkipped = Integer.parseInt(parts[8+offset].substring(0, parts[8+offset].length()-1));
        } else {
            testsSkipped = Integer.parseInt(parts[8+offset]);
        }
        if(testClass.length() > 0){
            testsByClasse.add(new TestClasse(testClass, parts[11+offset]+"s", testsRun, testsFailed, testsSkipped, testsError));
            testClass = "";
        }
        return testClass;
    }

    public int getTestsRun() {
        return testsRun;
    }

    public void setTestsRun(int testsRun) {
        this.testsRun = testsRun;
    }

    public int getTestsFailed() {
        return testsFailed;
    }

    public void setTestsFailed(int testsFailed) {
        this.testsFailed = testsFailed;
    }

    public int getTestsSkipped() {
        return testsSkipped;
    }

    public void setTestsSkipped(int testsSkipped) {
        this.testsSkipped = testsSkipped;
    }

    public int getTestsError() {
        return testsError;
    }

    public void setTestsError(int testsError) {
        this.testsError = testsError;
    }

    public List<TestClasse> getTestsByClasse() {
        return testsByClasse;
    }

    public void setTestsByClasse(List<TestClasse> testsByClasse) {
        this.testsByClasse = testsByClasse;
    }
}
