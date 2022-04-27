package com.api.controller;

import com.api.analyse.AnalyseTime;
import com.api.model.Log;
import com.api.services.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyse/time")
public class AnalyseTimeController {
    
    @Autowired
    private LogService logService;

    @GetMapping("/difference/{projectName}/{phaseName}")
    public float increaseTimeBetweenTwoLogs(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam("log1") int n1, @RequestParam("log2") int n2, @RequestParam(defaultValue = "0") int module) {
        Log log1 = logService.getLogByProject(projectName).get(n1);
        Log log2 = logService.getLogByProject(projectName).get(n2);
        return AnalyseTime.increaseTimeBetweenTwoLogs(log1, log2, phaseName, module);
    }

    @GetMapping("/difference/percent/{projectName}/{phaseName}")
    public float increaseTimeBetweenTwoLogsInPercent(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam("log1") int n1, @RequestParam("log2") int n2, @RequestParam(defaultValue = "0") int module) {
        Log log1 = logService.getLogByProject(projectName).get(n1);
        Log log2 = logService.getLogByProject(projectName).get(n2);
        return AnalyseTime.increaseTimeBetweenTwoLogsInPercent(log1, log2, phaseName, module);
    }

    @GetMapping("/mean/difference/{projectName}/{phaseName}")
    public float increaseMeanTimeBetween(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam("log1") int n1, @RequestParam("log2") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTime.increaseMeanTimeBetween(logService.getLogByProject(projectName), n1, n2, phaseName, module);
    }

    @GetMapping("/mean/difference/percent/{projectName}/{phaseName}")
    public float increaseMeanTimeBetweenInPercent(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam("log1") int n1, @RequestParam("log2") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTime.increaseMeanTimeBetweenInPercent(logService.getLogByProject(projectName), n1, n2, phaseName, module);
    }

    @GetMapping("/mean/{projectName}/{phaseName}")
    public float meanTimeBetween(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam("log1") int n1, @RequestParam("log2") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTime.meanTimeBetween(logService.getLogByProject(projectName), n1, n2, phaseName, module);
    }

}
