package com.api.controller;

import com.api.analyse.AnalyseTestsPhase;
import com.api.controller.abstracts.AnalyseController;
import com.api.model.Log;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyse/test/phase")
public class AnalyseTestsPhaseController extends AnalyseController {

    @GetMapping("/difference/{projectName}/{testType}")
    public float increaseNumberOfTestBetweenTwoLogs(@PathVariable String projectName, @PathVariable String testType, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        Log log1 = logService.getLogByProject(projectName).get(n1);
        Log log2 = logService.getLogByProject(projectName).get(getN2(n2, projectName));

        return AnalyseTestsPhase.increaseNumberOfTestBetweenTwoLogs(log1, log2, testType, module);
    }

    @GetMapping("/difference/percent/{projectName}/{testType}")
    public float increaseNumberOfTestInPercentBetweenTwoLogs(@PathVariable String projectName, @PathVariable String testType, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        Log log1 = logService.getLogByProject(projectName).get(n1);
        Log log2 = logService.getLogByProject(projectName).get(getN2(n2, projectName));
        return AnalyseTestsPhase.increaseNumberOfTestInPercentBetweenTwoLogs(log1, log2, testType, module);
    }

    @GetMapping("/mean/difference/{projectName}/{testType}")
    public float increaseMeanNumberOfTestBetween(@PathVariable String projectName, @PathVariable String testType, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTestsPhase.increaseMeanNumberOfTestBetween(logService.getLogByProject(projectName), n1, getN2(n2, projectName), testType, module);
    }

    @GetMapping("/mean/difference/percent/{projectName}/{testType}")
    public float increaseMeanNumberOfTestInPercentBetween(@PathVariable String projectName, @PathVariable String testType, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTestsPhase.increaseMeanNumberOfTestInPercentBetween(logService.getLogByProject(projectName), n1, getN2(n2, projectName), testType, module);
    }

    @GetMapping("/mean/{projectName}/{testType}")
    public float meanTest(@PathVariable String projectName, @PathVariable String testType, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTestsPhase.meanTest(logService.getLogByProject(projectName), n1, getN2(n2, projectName), testType, module);
    }

    @GetMapping("/median/{projectName}/{testType}")
    public float medianTest(@PathVariable String projectName, @PathVariable String testType, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTestsPhase.medianTest(logService.getLogByProject(projectName), n1, getN2(n2, projectName), testType, module);
    }

    @GetMapping("/quartile/first/{projectName}/{testType}")
    public float firstQuartileTest(@PathVariable String projectName, @PathVariable String testType, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTestsPhase.firstQuartileTest(logService.getLogByProject(projectName), n1, getN2(n2, projectName), testType, module);
    }

    @GetMapping("/quartile/third/{projectName}/{testType}")
    public float thirdQuartileTest(@PathVariable String projectName, @PathVariable String testType, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTestsPhase.thirdQuartileTest(logService.getLogByProject(projectName), n1, getN2(n2, projectName), testType, module);
    }

}
