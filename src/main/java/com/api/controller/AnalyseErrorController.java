package com.api.controller;

import java.util.Map;

import com.api.analyse.AnalyseError;
import com.api.controller.abstracts.AnalyseController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/analyse/error")
public class AnalyseErrorController extends AnalyseController {


    @CrossOrigin
    @GetMapping("/proportion/{projectName}")
    public float proportionOfBuildError(@PathVariable String projectName, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseError.proportionOfBuildError(logService.getLogByProject(projectName), n1, getN2(n2, projectName), module);
    }

    @CrossOrigin
    @GetMapping("/types/number/{projectName}")
    public Map<String, Integer> numberOfErrorPerType(@PathVariable String projectName, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseError.numberOfErrorPerType(logService.getLogByProject(projectName), n1, getN2(n2, projectName), module);
    }
    
}
