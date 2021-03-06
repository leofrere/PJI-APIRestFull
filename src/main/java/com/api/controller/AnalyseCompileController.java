package com.api.controller;

import com.api.analyse.AnalyseCompile;
import com.api.controller.abstracts.AnalyseController;
import com.api.model.Log;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/analyse/compile")
public class AnalyseCompileController extends AnalyseController {

    @CrossOrigin
    @GetMapping("/difference/{projectName}/{phaseName}")
    public float increaseNumberOfCompileClassBetweenTwoLogs(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        Log log1 = logService.getLogByProject(projectName).get(n1);
        Log log2 = logService.getLogByProject(projectName).get(getN2(n2, projectName));

        return AnalyseCompile.increaseNumberOfCompileClassBetweenTwoLogs(log1, log2, phaseName, module);
    }

    @CrossOrigin
    @GetMapping("/difference/percent/{projectName}/{phaseName}")
    public float increaseNumberOfCompileClassInPercentBetweenTwoLogs(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        Log log1 = logService.getLogByProject(projectName).get(n1);
        Log log2 = logService.getLogByProject(projectName).get(getN2(n2, projectName));
        return AnalyseCompile.increaseNumberOfCompileClassInPercentBetweenTwoLogs(log1, log2, phaseName, module);
    }

    @CrossOrigin
    @GetMapping("/mean/difference/{projectName}/{phaseName}")
    public float increaseMeanNumberOfCompileClassBetween(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseCompile.increaseMeanNumberOfCompileClassBetween(logService.getLogByProject(projectName), n1, getN2(n2, projectName), phaseName, module);
    }

    @CrossOrigin
    @GetMapping("/mean/difference/percent/{projectName}/{phaseName}")
    public float increaseMeanNumberOfCompileClassInPercentBetween(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseCompile.increaseMeanNumberOfCompileClassInPercentBetween(logService.getLogByProject(projectName), n1, getN2(n2, projectName), phaseName, module);
    }

    @CrossOrigin
    @GetMapping("/mean/{projectName}/{phaseName}")
    public float meanClassCompiled(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseCompile.meanClassCompiled(logService.getLogByProject(projectName), n1, getN2(n2, projectName), phaseName, module);
    }

    @CrossOrigin
    @GetMapping("/median/{projectName}/{phaseName}")
    public float medianClassCompiled(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseCompile.medianClassCompiled(logService.getLogByProject(projectName), n1, getN2(n2, projectName), phaseName, module);
    }

    @CrossOrigin
    @GetMapping("/quartile/first/{projectName}/{phaseName}")
    public float firstQuartileClassCompiled(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseCompile.firstQuartileBetween(logService.getLogByProject(projectName), n1, getN2(n2, projectName), phaseName, module);
    }

    @CrossOrigin
    @GetMapping("/quartile/third/{projectName}/{phaseName}")
    public float thirdQuartileClassCompiled(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseCompile.thirdQuartileBetween(logService.getLogByProject(projectName), n1, getN2(n2, projectName), phaseName, module);
    }

}
