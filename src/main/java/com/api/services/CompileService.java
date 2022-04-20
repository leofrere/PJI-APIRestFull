package com.api.services;

import java.io.BufferedReader;

import com.api.model.Compile;
import com.api.repository.CompileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompileService {
    
    @Autowired
    private CompileRepository compileRepository;

    public Compile getCompileById(long id) {
        return compileRepository.findById(id).get();
    }

    public Compile addCompile(BufferedReader reader) {
        return compileRepository.save(new Compile(reader));
    }

}
