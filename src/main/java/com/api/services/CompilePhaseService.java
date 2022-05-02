package com.api.services;

import java.io.BufferedReader;

import com.api.model.CompilePhase;
import com.api.repository.CompileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompilePhaseService {
    
    @Autowired
    private CompileRepository compileRepository;

    public CompilePhase getCompilePhaseById(long id) {
        return compileRepository.findById(id).get();
    }

    public CompilePhase addCompilePhase(BufferedReader reader, String projectName, int build) {
        CompilePhase compilePhase = new CompilePhase(reader);
        compilePhase.setProject(projectName);
        compilePhase.setBuild(build);
        return compileRepository.save(compilePhase);
    }

    public void deleteCompilePhase(long id) {
        compileRepository.deleteById(id);
    }

}
