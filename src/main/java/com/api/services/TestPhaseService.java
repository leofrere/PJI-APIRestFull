package com.api.services;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;

import com.api.model.TestClasse;
import com.api.model.TestPhase;
import com.api.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class TestPhaseService {
    
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestClasseService testClasseService;

    @GraphQLQuery(name = "testPhases")
    public List<TestPhase> getTestPhases() {
        return testRepository.findAll();
    }

    @GraphQLQuery(name = "testPhasesByProject")
    public List<TestPhase> getTestPhasesByProject(@GraphQLArgument(name = "project") String project) {
        List<TestPhase> testPhases = testRepository.findAll();
        List<TestPhase> testPhasesByProject = new LinkedList<TestPhase>();
        for (TestPhase testPhase : testPhases) {
            if (testPhase.getProject().equals(project)) {
                testPhasesByProject.add(testPhase);
            }
        }
        return testPhasesByProject;
    }

    @GraphQLQuery(name = "testPhaseById")
    public TestPhase getTestByIdPhase(@GraphQLArgument(name = "id") long id) {
        return testRepository.findById(id).get();
    }

    @GraphQLQuery(name = "testPhaseOfTime")
    public List<TestPhase> getTestPhaseByTime(@GraphQLArgument(name = "time") String time, @GraphQLArgument(name = "op") String op, @GraphQLArgument(name = "project") String projectName) {
        List<TestPhase> testPhases = testRepository.findAll();
        List<TestPhase> testPhaseByTime = new LinkedList<TestPhase>();
        for (TestPhase testPhase : testPhases) {
            if (testPhase.getProject().equals(projectName)) {
                cmpTestPhase(testPhase.getTimeFloat(), Float.parseFloat(time), op, testPhaseByTime, testPhase);
            }
            
        }
        return testPhaseByTime;
    }

    @GraphQLQuery(name = "testPhasesOfTest")
    public List<TestPhase> getTestPhaseByTest(@GraphQLArgument(name = "test") String test, @GraphQLArgument(name = "op") String op, @GraphQLArgument(name = "nbTest") float nbTest, @GraphQLArgument(name = "project") String projectName) {
        List<TestPhase> testPhases = testRepository.findAll();
        List<TestPhase> testPhasesByTest = new LinkedList<TestPhase>();
        for (TestPhase testPhase : testPhases) {
            if (testPhase.getProject().equals(projectName)) {
                switchTypeOfTest(test, op, nbTest, testPhasesByTest, testPhase);
            }
            
        }
        return testPhasesByTest;
    }

    @GraphQLQuery(name = "testPhaseByCompiled")
    public List<TestPhase> getTestPhaseByCompiled(@GraphQLArgument(name = "compiled") int compiled, @GraphQLArgument(name = "op") String op, @GraphQLArgument(name = "project") String projectName) {
        List<TestPhase> testPhases = testRepository.findAll();
        List<TestPhase> list = new LinkedList<TestPhase>();
        for (TestPhase testPhase : testPhases) {
            if (testPhase.getProject().equals(projectName)){
                cmpTestPhase((float) compiled,(float) testPhase.getCompiledClasses(), op, list, testPhase);
            }
        }
        return list;
    }

    public TestPhase addTestPhase(BufferedReader reader, String projectName, int build, String module) {
        TestPhase testPhase = new TestPhase(reader);
        List<TestClasse> testClasses = testPhase.getTestsByClasse();
        List<TestClasse> testClassesToSave = new LinkedList<TestClasse>();
        for(TestClasse testClasse : testClasses) {
            testClassesToSave.add(testClasseService.addTestClasse(testClasse, projectName, build, module));
        }
        testPhase.setTestsByClasse(testClassesToSave);
        testPhase.setProject(projectName);
        testPhase.setBuild(build);
        testPhase.setModule(module);
        return testRepository.save(testPhase);
    }

    private void cmpTestPhase(Float a, Float b, String op, List<TestPhase> testClasses, TestPhase testClasse) {
        switch(op) {
            case ">":
                if (a > b) {
                    testClasses.add(testClasse);
                }
                break;
            case "<":
                if (a < b) {
                    testClasses.add(testClasse);
                }
                break;
            case "=":
                if (a == b) {
                    testClasses.add(testClasse);
                }
        }
    }

    private void switchTypeOfTest(String test, String op, float nbTest, List<TestPhase> testPhasesByTest,
            TestPhase testPhase) {
        switch(test) {
            case "run":
                cmpTestPhase((float) testPhase.getTestsRun(), nbTest, op, testPhasesByTest, testPhase);
                break;
            case "failed":
                cmpTestPhase((float) testPhase.getTestsFailed(), nbTest, op, testPhasesByTest, testPhase);
                break;
            case "skipped":
                cmpTestPhase((float) testPhase.getTestsSkipped(), nbTest, op, testPhasesByTest, testPhase);
                break;
            case "errors":
                cmpTestPhase((float) testPhase.getTestsError(), nbTest, op, testPhasesByTest, testPhase);
        }
    }

}
