package com.api.services;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;

import com.api.model.CompilePhase;
import com.api.model.Log;
import com.api.model.TestPhase;
import com.api.model.PackagePhase;
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
        CompilePhase compile = compileService.addCompile(reader);
        TestPhase test = testService.addTest(reader);
        PackagePhase package1 = packageService.addPackage(reader);
        logRepository.save(new Log("mavenGL", buildNumber, compile, test, package1));
    }

    public boolean buildAlreadyExist(String project, int buildNumber) {
        List<Log> list = logRepository.findAll();
        for (Log log : list) {
            if (log.getProject().equals(project) && log.getBuild() == buildNumber) {
                return true;
            }
        }
        return false;
    }

    public void deleteLog(long id) {
        Log log = logRepository.findById(id).get();
        long compileId = log.getCompile().getId();
        long testId = log.getTest().getId();
        long packageId = log.getPackag().getId();
        logRepository.deleteById(id);
        compileService.deleteCompile(compileId);
        testService.deleteTest(testId);
        packageService.deletePackage(packageId);
    }

}
