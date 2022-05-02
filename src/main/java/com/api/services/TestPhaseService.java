package com.api.services;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;

import com.api.model.TestClasse;
import com.api.model.TestPhase;
import com.api.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestPhaseService {
    
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestClasseService testClasseService;

    public TestPhase getTestByIdPhase(long id) {
        return testRepository.findById(id).get();
    }

    public TestPhase addTestPhase(BufferedReader reader, String projectName, int build) {
        TestPhase testPhase = new TestPhase(reader);
        List<TestClasse> testClasses = testPhase.getTestsByClasse();
        List<TestClasse> testClassesToSave = new LinkedList<TestClasse>();
        for(TestClasse testClasse : testClasses) {
            testClassesToSave.add(testClasseService.addTestClasse(testClasse, projectName, build));
        }
        testPhase.setTestsByClasse(testClassesToSave);
        testPhase.setProject(projectName);
        testPhase.setBuild(build);
        return testRepository.save(testPhase);
    }

}
