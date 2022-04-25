package com.api.model;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.api.utils.Time;

@Entity
@Table(name = "tests")
public class TestPhase implements Phase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String status;

    private String time;

    private int testsRun;

    private int testsFailed;

    private int testsSkipped;

    private int testsError;

    private int numberOfTestClasses;

    private String errorsTrace = "";

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
        this.numberOfTestClasses = numberOfTestClasses;
    }

    public TestPhase(BufferedReader reader) {
        String line = null;
        testsByClasse = new LinkedList<TestClasse>();
        status = "ko";
        boolean secondErrorLine = false;
        String time1 = "";
        String testClass = "";
        try {
            while ((line = reader.readLine()) != null) {

                if(line.contains("--- maven-jar-plugin:")){
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
                    time1 = parts[0];
                    numberOfTestClasses = Integer.parseInt(parts[3]);
                    continue;
                }

                
                if(line.contains("Running ")){
                    testClass = line.split(" ")[1];
                }

                if(line.contains("Tests run:")){
                    testClass = parseTestsInformation(line, testClass);
                    continue;
                }
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String parseTestsInformation(String line, String testClass) {
        String parts[] = line.split(" ");
        testsRun = Integer.parseInt(parts[2].substring(0, parts[2].length()-1));
        testsFailed = Integer.parseInt(parts[4].substring(0, parts[4].length()-1));
        testsError = Integer.parseInt(parts[6].substring(0, parts[6].length()-1));
        if(parts[8].contains(",")){
            testsSkipped = Integer.parseInt(parts[8].substring(0, parts[8].length()-1));
        } else {
            testsSkipped = Integer.parseInt(parts[8]);
        }
        if(testClass.length() > 0){
            testsByClasse.add(new TestClasse(testClass, parts[11]+"s", testsRun, testsFailed, testsSkipped, testsError));
            testClass = "";
        }
        return testClass;
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

    public int getNumberOfTestClasses() {
        return numberOfTestClasses;
    }

    public void setNumberOfTestClasses(int numberOfTestClasses) {
        this.numberOfTestClasses = numberOfTestClasses;
    }

    public String getErrorsTrace() {
        return errorsTrace;
    }

    public void setErrorsTrace(String errorsTrace) {
        this.errorsTrace = errorsTrace;
    }

    public List<TestClasse> getTestsByClasse() {
        return testsByClasse;
    }

    public void setTestsByClasse(List<TestClasse> testsByClasse) {
        this.testsByClasse = testsByClasse;
    }

}
