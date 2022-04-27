package com.api.controller;

import com.api.analyse.AnalyseCompile;
import com.api.model.Log;
import com.api.services.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyse/compile")
public class AnalyseCompileController {
    
    @Autowired
    private LogService logService;

    @GetMapping("/difference/{projectName}/{phaseName}")
    public float increaseNumberOfCompileClassBetweenTwoLogs(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam("log1") int n1, @RequestParam("log2") int n2, @RequestParam(defaultValue = "0") int moduleNumber) {
        Log log1 = logService.getLogByProject(projectName).get(n1);
        Log log2 = logService.getLogByProject(projectName).get(n2);

        return AnalyseCompile.increaseNumberOfCompileClassBetweenTwoLogs(log1, log2, phaseName, moduleNumber);
    }

    @GetMapping("/difference/percent/{projectName}/{phaseName}")
    public float increaseNumberOfCompileClassInPercentBetweenTwoLogs(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam("log1") int n1, @RequestParam("log2") int n2, @RequestParam(defaultValue = "0") int moduleNumber) {
        Log log1 = logService.getLogByProject(projectName).get(n1);
        Log log2 = logService.getLogByProject(projectName).get(n2);
        return AnalyseCompile.increaseNumberOfCompileClassInPercentBetweenTwoLogs(log1, log2, phaseName, moduleNumber);
    }

    @GetMapping("/mean/difference/{projectName}/{phaseName}")
    public float increaseMeanNumberOfCompileClassBetween(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam("log1") int n1, @RequestParam("log2") int n2, @RequestParam(defaultValue = "0") int moduleNumber) {
        return AnalyseCompile.increaseMeanNumberOfCompileClassBetween(logService.getLogByProject(projectName), n1, n2, phaseName, moduleNumber);
    }

    @GetMapping("/mean/difference/percent/{projectName}/{phaseName}")
    public float increaseMeanNumberOfCompileClassInPercentBetween(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam("log1") int n1, @RequestParam("log2") int n2, @RequestParam(defaultValue = "0") int moduleNumber) {
        return AnalyseCompile.increaseMeanNumberOfCompileClassInPercentBetween(logService.getLogByProject(projectName), n1, n2, phaseName, moduleNumber);
    }

    @GetMapping("/mean/{projectName}/{phaseName}")
    public float meanClassCompiled(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam("log1") int n1, @RequestParam("log2") int n2, @RequestParam(defaultValue = "0") int moduleNumber) {
        return AnalyseCompile.meanClassCompiled(logService.getLogByProject(projectName), n1, n2, phaseName, moduleNumber);
    }

}
