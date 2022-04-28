package com.api.controller;

import com.api.analyse.AnalyseTestsClasse;
import com.api.controller.abstracts.AnalyseController;
import com.api.model.Log;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyse/test/classe")
public class AnalyseTestsClasseController extends AnalyseController {

    @GetMapping("/difference/{projectName}/{testType}")
    public float increaseNumberOfTestBetweenTwoLogs(@PathVariable String projectName, @PathVariable String testType, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam("classe") String testClasse, @RequestParam(defaultValue = "0") int module) {
        Log log1 = logService.getLogByProject(projectName).get(n1);
        Log log2 = logService.getLogByProject(projectName).get(getN2(n2, projectName));

        return AnalyseTestsClasse.increaseNumberOfTestBetweenTwoLogs(log1, log2, testType, testClasse, module);
    }

    @GetMapping("/difference/percent/{projectName}/{testType}")
    public float increaseNumberOfTestInPercentBetweenTwoLogs(@PathVariable String projectName, @PathVariable String testType, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam("classe") String testClasse, @RequestParam(defaultValue = "0") int module) {
        Log log1 = logService.getLogByProject(projectName).get(n1);
        Log log2 = logService.getLogByProject(projectName).get(getN2(n2, projectName));
        return AnalyseTestsClasse.increaseNumberOfTestInPercentBetweenTwoLogs(log1, log2, testType, testClasse, module);
    }

    @GetMapping("/mean/difference/{projectName}/{testType}")
    public float increaseMeanNumberOfTestBetween(@PathVariable String projectName, @PathVariable String testType, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam("classe") String testClasse, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTestsClasse.increaseMeanNumberOfTestBetween(logService.getLogByProject(projectName), n1, getN2(n2, projectName), testType, testClasse, module);
    }

    @GetMapping("/mean/difference/percent/{projectName}/{testType}")
    public float increaseMeanNumberOfTestInPercentBetween(@PathVariable String projectName, @PathVariable String testType, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam("classe") String testClasse, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTestsClasse.increaseMeanNumberOfTestInPercentBetween(logService.getLogByProject(projectName), n1, getN2(n2, projectName), testType, testClasse, module);
    }

    @GetMapping("/mean/{projectName}/{testType}")
    public float meanTest(@PathVariable String projectName, @PathVariable String testType, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam("classe") String testClasse, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTestsClasse.meanTest(logService.getLogByProject(projectName), n1, getN2(n2, projectName), testType, testClasse, module);
    }

    @GetMapping("/median/{projectName}/{testType}")
    public float medianTest(@PathVariable String projectName, @PathVariable String testType, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam("classe") String testClasse, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTestsClasse.medianTest(logService.getLogByProject(projectName), n1, getN2(n2, projectName), testType, testClasse, module);
    }

    @GetMapping("/quartile/first/{projectName}/{testType}")
    public float firstQuartileTest(@PathVariable String projectName, @PathVariable String testType, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam("classe") String testClasse, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTestsClasse.firstQuartileTest(logService.getLogByProject(projectName), n1, getN2(n2, projectName), testType, testClasse, module);
    }

    @GetMapping("/quartile/third/{projectName}/{testType}")
    public float thirdQuartileTest(@PathVariable String projectName, @PathVariable String testType, @RequestParam(name="log1", defaultValue = "0") int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam("classe") String testClasse, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTestsClasse.thirdQuartileTest(logService.getLogByProject(projectName), n1, getN2(n2, projectName), testType, testClasse, module);
    }
}
