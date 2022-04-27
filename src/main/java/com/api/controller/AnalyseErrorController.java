package com.api.controller;

import java.util.Map;

import com.api.analyse.AnalyseError;
import com.api.services.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyse/error")
public class AnalyseErrorController {

    @Autowired
    private LogService logService;

    @GetMapping("/proportion/{projectName}")
    public float proportionOfBuildError(@PathVariable String projectName, @RequestParam("log1") int n1, @RequestParam("log2") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseError.proportionOfBuildError(logService.getLogByProject(projectName), n1, n2, module);
    }

    @GetMapping("/types/number/{projectName}")
    public Map<String, Integer> numberOfErrorPerType(@PathVariable String projectName, @RequestParam("log1") int n1, @RequestParam("log2") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseError.numberOfErrorPerType(logService.getLogByProject(projectName), n1, n2, module);
    }
    
}
