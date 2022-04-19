package com.api.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import com.api.model.Compile;
import com.api.model.Log;
import com.api.model.Package;
import com.api.model.Test;
import com.api.repository.CompileRepository;
import com.api.repository.LogRepository;
import com.api.repository.PackageRepository;
import com.api.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    
    @Autowired
    private CompileRepository compileRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private LogRepository logRepository;

    @GetMapping("/log/create")
    public void setLog(@RequestParam(value="path") String path) {
        File file = new File(path);
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
            Compile compile = compileRepository.save(new Compile(reader));
            Test test = testRepository.save(new Test(reader));
            Package package1 = packageRepository.save(new Package(reader));
            logRepository.save(new Log( "mavenGL", compile, test, package1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/log")
    public Log getLog(@RequestParam(value="id") Long id) {
        return logRepository.findById(id).get();
    }

    @GetMapping("/log/project")
    public List<Log> getLogByProject(@RequestParam(value="project") String project) {
        List<Log> list = logRepository.findAll();
        LinkedList<Log> logOfProject = new LinkedList<Log>();

        for (Log log : list) {
            if (log.getProject().equals(project)) {
                logOfProject.add(log);
            }
        }

        return logOfProject;
    }

}
