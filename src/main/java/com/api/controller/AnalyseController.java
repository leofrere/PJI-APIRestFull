package com.api.controller;

import com.api.analyse.Analyse;
import com.api.model.Log;
import com.api.services.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyseController {
    
    @Autowired
    private LogService logService;

    @GetMapping("/analyse/mean/time/{projectName}/{phaseName}")
    public float meanTimeForCompilePhase(@PathVariable String projectName, @PathVariable String phaseName) {
        return Analyse.meanTimeForCompilePhase(logService.getLogByProject(projectName), phaseName);
    }

    @GetMapping("/analyse/increase/tests/percent/{projectName}")
    public float increaseOfNumberOfTest(@PathVariable String projectName, @RequestParam("log1") int n1, @RequestParam("log2") int n2 ) {
        Log log1 = logService.getLog(n1);
        Log log2 = logService.getLog(n2);
        return Analyse.increaseOfNumberOfTest(log1, log2);
    }

    @GetMapping("/analyse/increase/time/percent/{projectName}/{phaseName}")
    public float increaseOfTimePerPhase(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam("log1") int n1, @RequestParam("log2") int n2 ) {
        Log log1 = logService.getLog(n1);
        Log log2 = logService.getLog(n2);
        return Analyse.increaseOfTimePerPhase(log1, log2, phaseName);
    }

}
