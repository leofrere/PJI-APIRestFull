package com.api.services;

import java.io.BufferedReader;

import com.api.model.Test;
import com.api.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    
    @Autowired
    private TestRepository testRepository;

    public Test getTestById(long id) {
        return testRepository.findById(id).get();
    }

    public Test addTest(BufferedReader reader) {
        return testRepository.save(new Test(reader));
    }

}