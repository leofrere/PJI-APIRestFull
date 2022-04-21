package com.api.services;

import java.io.BufferedReader;

import com.api.model.TestPhase;
import com.api.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    
    @Autowired
    private TestRepository testRepository;

    public TestPhase getTestById(long id) {
        return testRepository.findById(id).get();
    }

    public TestPhase addTest(BufferedReader reader) {
        return testRepository.save(new TestPhase(reader));
    }

    public void deleteTest(long id) {
        testRepository.deleteById(id);
    }

}
