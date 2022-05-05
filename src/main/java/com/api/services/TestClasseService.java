package com.api.services;

import java.util.LinkedList;
import java.util.List;

import com.api.model.TestClasse;
import com.api.repository.TestClasseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class TestClasseService {
    
    @Autowired
    private TestClasseRepository testClasseRepository;

    public TestClasse addTestClasse(TestClasse testClasse, String projectName, int build, String module) {
        testClasse.setProject(projectName);
        testClasse.setBuild(build);
        testClasse.setModule(module);
        return testClasseRepository.save(testClasse);
    }

    @GraphQLQuery(name = "testClasses")
    public List<TestClasse> getAllTestClasse() {
        return testClasseRepository.findAll();
    }

    @GraphQLQuery(name = "testClassesOfProject")
    public List<TestClasse> getTestClasseByProject(@GraphQLArgument(name = "project") String projectName, @GraphQLArgument(name = "module") String module) {
        List<TestClasse> testClasses = testClasseRepository.findAll();
        List<TestClasse> testClassesByProject = new LinkedList<TestClasse>();
        for (TestClasse testClasse : testClasses) {
            if (testClasse.getProject().equals(projectName) && testClasse.getModule().equals(module)) {
                testClassesByProject.add(testClasse);
            }
        }
        return testClassesByProject;
    }

    @GraphQLQuery(name = "testClassesOfClass")
    public List<TestClasse> getTestClasseByClass(@GraphQLArgument(name = "class") String className, @GraphQLArgument(name = "project") String projectName, @GraphQLArgument(name = "module") String module) {
        List<TestClasse> testClasses = testClasseRepository.findAll();
        List<TestClasse> testClassesByClass = new LinkedList<TestClasse>();
        for (TestClasse testClasse : testClasses) {
            if (testClasse.getClasse().equals(className) && testClasse.getProject().equals(projectName) && testClasse.getModule().equals(module)) {
                testClassesByClass.add(testClasse);
            }
        }
        return testClassesByClass;
    }

    @GraphQLQuery(name = "testClassesOfTime")
    public List<TestClasse> getTestClasseByTime(@GraphQLArgument(name = "time") String time, @GraphQLArgument(name = "op") String op, @GraphQLArgument(name = "project") String projectName, @GraphQLArgument(name = "module") String module) {
        List<TestClasse> testClasses = testClasseRepository.findAll();
        List<TestClasse> testClassesByTime = new LinkedList<TestClasse>();
        for (TestClasse testClasse : testClasses) {
            if (testClasse.getProject().equals(projectName) && testClasse.getModule().equals(module)) {
                cmpTestClasse(testClasse.getTimeFloat(), Float.parseFloat(time), op, testClassesByTime, testClasse);
            }
            
        }
        return testClassesByTime;
    }

    @GraphQLQuery(name = "testClassesOfTest")
    public List<TestClasse> getTestClasseByTest(@GraphQLArgument(name = "test") String test, @GraphQLArgument(name = "op") String op, @GraphQLArgument(name = "nbTest") float nbTest, @GraphQLArgument(name = "project") String projectName, @GraphQLArgument(name = "module") String module) {
        List<TestClasse> testClasses = testClasseRepository.findAll();
        List<TestClasse> testClassesByTest = new LinkedList<TestClasse>();
        for (TestClasse testClasse : testClasses) {
            if (testClasse.getProject().equals(projectName) && testClasse.getModule().equals(module)) {
                switchTypeOfTest(test, op, nbTest, testClassesByTest, testClasse);
            }
            
        }
        return testClassesByTest;
    }

    private void switchTypeOfTest(String test, String op, float nbTest, List<TestClasse> testClassesByTest,
            TestClasse testClasse) {
        switch(test) {
            case "run":
                cmpTestClasse((float) testClasse.getTestsRun(), nbTest, op, testClassesByTest, testClasse);
                break;
            case "failed":
                cmpTestClasse((float) testClasse.getTestsFailed(), nbTest, op, testClassesByTest, testClasse);
                break;
            case "skipped":
                cmpTestClasse((float) testClasse.getTestsSkipped(), nbTest, op, testClassesByTest, testClasse);
                break;
            case "errors":
                cmpTestClasse((float) testClasse.getTestsError(), nbTest, op, testClassesByTest, testClasse);
        }
    }

    private void cmpTestClasse(Float a, Float b, String op, List<TestClasse> testClasses, TestClasse testClasse) {
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




}
