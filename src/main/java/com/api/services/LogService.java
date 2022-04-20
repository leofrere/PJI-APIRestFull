package com.api.services;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;

import com.api.model.Compile;
import com.api.model.Log;
import com.api.model.Test;
import com.api.model.Package;
import com.api.repository.LogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private CompileService compileService;

    @Autowired
    private TestService testService;

    @Autowired
    private PackageService packageService;

    public Log getLogById(long id) {
        return logRepository.findById(id).get();
    }

    public List<Log> getAllLog() {
        return logRepository.findAll();
    }

    public List<Log> getLogByProject(String project) {
        List<Log> list = logRepository.findAll();
        LinkedList<Log> logOfProject = new LinkedList<Log>();

        for (Log log : list) {
            if (log.getProject().equals(project)) {
                logOfProject.add(log);
            }
        }

        return logOfProject;
    }

    public void addLog(BufferedReader reader, int buildNumber) {
        Compile compile = compileService.addCompile(reader);
        Test test = testService.addTest(reader);
        Package package1 = packageService.addPackage(reader);
        logRepository.save(new Log("mavenGL", buildNumber, compile, test, package1));
    }

}
