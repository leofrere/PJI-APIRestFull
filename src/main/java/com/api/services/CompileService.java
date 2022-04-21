package com.api.services;

import java.io.BufferedReader;

import com.api.model.CompilePhase;
import com.api.repository.CompileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompileService {
    
    @Autowired
    private CompileRepository compileRepository;

    public CompilePhase getCompileById(long id) {
        return compileRepository.findById(id).get();
    }

    public CompilePhase addCompile(BufferedReader reader) {
        return compileRepository.save(new CompilePhase(reader));
    }

    public void deleteCompile(long id) {
        compileRepository.deleteById(id);
    }

}
