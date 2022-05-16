package com.api.services;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    public List<TestPhase> getTestPhasesByProject(@GraphQLArgument(name = "project") String project, @GraphQLArgument(name = "module") String module) {
        List<TestPhase> testPhases = testRepository.findAll();
        List<TestPhase> testPhasesByProject = new LinkedList<TestPhase>();
        for (TestPhase testPhase : testPhases) {
            if (testPhase.getProject().equals(project) && (testPhase.getModule().equals(module) || module.equals(""))) {
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
    public List<TestPhase> getTestPhaseByTime(@GraphQLArgument(name = "time") String time, @GraphQLArgument(name = "op") String op, @GraphQLArgument(name = "project") String projectName, @GraphQLArgument(name = "module") String moduleName) {
        List<TestPhase> testPhases = testRepository.findAll();
        List<TestPhase> testPhaseByTime = new LinkedList<TestPhase>();
        for (TestPhase testPhase : testPhases) {
            if (testPhase.getProject().equals(projectName) && testPhase.getModule().equals(moduleName)) {
                cmpTestPhase(testPhase.getTimeFloat(), Float.parseFloat(time), op, testPhaseByTime, testPhase);
            }
            
        }
        return testPhaseByTime;
    }

    @GraphQLQuery(name = "testPhasesOfTest")
    public List<TestPhase> getTestPhaseByTest(@GraphQLArgument(name = "test") String test, @GraphQLArgument(name = "op") String op, @GraphQLArgument(name = "nbTest") float nbTest, @GraphQLArgument(name = "project") String projectName, @GraphQLArgument(name = "module") String module) {
        List<TestPhase> testPhases = testRepository.findAll();
        List<TestPhase> testPhasesByTest = new LinkedList<TestPhase>();
        for (TestPhase testPhase : testPhases) {
            if (testPhase.getProject().equals(projectName) && (testPhase.getModule().equals(module) || module.equals(""))) {
                switchTypeOfTest(test, op, nbTest, testPhasesByTest, testPhase);
            }
            
        }
        return testPhasesByTest;
    }

    @GraphQLQuery(name = "testPhaseByCompiled")
    public List<TestPhase> getTestPhaseByCompiled(@GraphQLArgument(name = "compiled") int compiled, @GraphQLArgument(name = "op") String op, @GraphQLArgument(name = "project") String projectName, @GraphQLArgument(name = "module") String module) {
        List<TestPhase> testPhases = testRepository.findAll();
        List<TestPhase> list = new LinkedList<TestPhase>();
        for (TestPhase testPhase : testPhases) {
            if (testPhase.getProject().equals(projectName) && (testPhase.getModule().equals(module) || module.equals(""))) {
                cmpTestPhase((float) compiled,(float) testPhase.getCompiledClasses(), op, list, testPhase);
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    @GraphQLQuery(name = "testPhasesTimeByCompiledClass")
    public Map<String,Float>[] getTestPhasesTimeByCompiledClass(@GraphQLArgument(name = "project") String projectName, @GraphQLArgument(name = "module") String module) {
        List<TestPhase> compilePhases = getTestPhasesByProject(projectName, module);
        Map<String,Float>[] map = new HashMap[compilePhases.size()];

        for(int i = 0; i < compilePhases.size(); i++) {
            map[i] = new HashMap<String,Float>();
            map[i].put("build", (float) compilePhases.get(i).getBuild());
            map[i].put("time", (float) compilePhases.get(i).getTimeFloat() / compilePhases.get(i).getCompiledClasses());
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    @GraphQLQuery(name = "testPhasesTimeByTest")
    public Map<String,Float>[] getCompilePhasesTimeByCompiledClass(@GraphQLArgument(name = "project") String projectName, @GraphQLArgument(name = "module") String module, @GraphQLArgument(name = "test") String test) {
        List<TestPhase> testPhases = getTestPhasesByProject(projectName, module);
        Map<String,Float>[] map = new HashMap[testPhases.size()];

        for(int i = 0; i < testPhases.size(); i++) {
            map[i] = new HashMap<String,Float>();
            map[i].put("build", (float) testPhases.get(i).getBuild());
            switch (test) {
                case "run":
                    if(testPhases.get(i).getTestsRun() == 0) break;
                    map[i].put("time", (float) testPhases.get(i).getTimeFloat() / testPhases.get(i).getTestsRun());
                    break;
                case "failed":
                    if(testPhases.get(i).getTestsFailed() == 0) break;
                    map[i].put("time", (float) testPhases.get(i).getTimeFloat() / testPhases.get(i).getTestsFailed());
                    break;
                case "skipped":
                    if(testPhases.get(i).getTestsSkipped() == 0) break;
                    map[i].put("time", (float) testPhases.get(i).getTimeFloat() / testPhases.get(i).getTestsSkipped());
                    break;
                case "error":
                    if(testPhases.get(i).getTestsError() == 0) break;
                    map[i].put("time", (float) testPhases.get(i).getTimeFloat() / testPhases.get(i).getTestsError());
                    break;
            }
        }
        return map;
    }

    public TestPhase addTestPhase(BufferedReader reader, String projectName, int build, String module, boolean timeIsSet) {
        TestPhase testPhase = new TestPhase(reader, timeIsSet);
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
