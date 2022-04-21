package com.api.services;

import java.io.BufferedReader;

import com.api.model.TestPhase;
import com.api.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestPhaseService {
    
    @Autowired
    private TestRepository testRepository;

    public TestPhase getTestByIdPhase(long id) {
        return testRepository.findById(id).get();
    }

    public TestPhase addTestPhase(BufferedReader reader) {
        return testRepository.save(new TestPhase(reader));
    }

    public void deleteTestPhase(long id) {
        testRepository.deleteById(id);
    }

}
