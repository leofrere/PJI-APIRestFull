package com.api.controller;

import com.api.analyse.AnalyseTime;
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
@RequestMapping("/analyse/time")
public class AnalyseTimeController extends AnalyseController {

    @CrossOrigin
    @GetMapping("/difference/{projectName}/{phaseName}")
    public float increaseTimeBetweenTwoLogs(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam(name="log1", defaultValue = "0" ) int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        Log log1 = logService.getLogByProject(projectName).get(n1);
        Log log2 = logService.getLogByProject(projectName).get(getN2(n2, projectName));
        return AnalyseTime.increaseTimeBetweenTwoLogs(log1, log2, phaseName, module);
    }

    @CrossOrigin
    @GetMapping("/difference/percent/{projectName}/{phaseName}")
    public float increaseTimeBetweenTwoLogsInPercent(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam(name="log1", defaultValue = "0" ) int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        Log log1 = logService.getLogByProject(projectName).get(n1);
        Log log2 = logService.getLogByProject(projectName).get(getN2(n2, projectName));
        return AnalyseTime.increaseTimeBetweenTwoLogsInPercent(log1, log2, phaseName, module);
    }

    @CrossOrigin
    @GetMapping("/mean/difference/{projectName}/{phaseName}")
    public float increaseMeanTimeBetween(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam(name="log1", defaultValue = "0" ) int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTime.increaseMeanTimeBetween(logService.getLogByProject(projectName), n1, getN2(n2, projectName), phaseName, module);
    }

    @CrossOrigin
    @GetMapping("/mean/difference/percent/{projectName}/{phaseName}")
    public float increaseMeanTimeBetweenInPercent(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam(name="log1", defaultValue = "0" ) int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTime.increaseMeanTimeBetweenInPercent(logService.getLogByProject(projectName), n1, getN2(n2, projectName), phaseName, module);
    }

    @CrossOrigin
    @GetMapping("/mean/{projectName}/{phaseName}")
    public float meanTimeBetween(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam(name="log1", defaultValue = "0" ) int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTime.meanTimeBetween(logService.getLogByProject(projectName), n1, getN2(n2, projectName), phaseName, module);
    }

    @CrossOrigin
    @GetMapping("median/{projectName}/{phaseName}")
    public float medianTimeBetween(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam(name="log1", defaultValue = "0" ) int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTime.medianTimeBetween(logService.getLogByProject(projectName), n1, getN2(n2, projectName), phaseName, module);
    }

    @CrossOrigin
    @GetMapping("/quartile/first/{projectName}/{phaseName}")
    public float firstQuartileTimeBetween(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam(name="log1", defaultValue = "0" ) int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTime.firstQuartileBetween(logService.getLogByProject(projectName), n1, getN2(n2, projectName), phaseName, module);
    }

    @CrossOrigin
    @GetMapping("/quartile/third/{projectName}/{phaseName}")
    public float thirdQuartileTimeBetween(@PathVariable String projectName, @PathVariable String phaseName, @RequestParam(name="log1", defaultValue = "0" ) int n1, @RequestParam(name="log2", defaultValue = "-1") int n2, @RequestParam(defaultValue = "0") int module) {
        return AnalyseTime.thirdQuartileBetween(logService.getLogByProject(projectName), n1, getN2(n2, projectName), phaseName, module);
    }

}
