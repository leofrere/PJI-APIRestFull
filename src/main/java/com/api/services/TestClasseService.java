package com.api.services;

import com.api.model.TestClasse;
import com.api.repository.TestClasseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestClasseService {
    
    @Autowired
    private TestClasseRepository testClasseRepository;

    public TestClasse addTestClasse(TestClasse testClasse, String projectName, int build) {
        testClasse.setProject(projectName);
        testClasse.setBuild(build);
        return testClasseRepository.save(testClasse);
    }

}
