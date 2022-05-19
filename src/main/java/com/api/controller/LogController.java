package com.api.controller;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;

import com.api.model.JenkinsBuild;
import com.api.model.Log;
import com.api.services.JenkinsBuildService;
import com.api.services.LogService;
import com.api.utils.ReaderBuild;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private JenkinsBuildService jenkinsBuildService;

    @Autowired
    private LogService logService;

    @CrossOrigin
    @PostMapping("/create/{projectName}/{buildNumber}")
    public void setLog(@PathVariable String projectName, @PathVariable int buildNumber) {

        JenkinsBuild jenkinsBuild = jenkinsBuildService.getJenkinsBuildByProjectName(projectName);
        if (jenkinsBuild == null) {
            return ;
        }

        if(logService.buildAlreadyExist(projectName, buildNumber)) return;

        try {
            BufferedReader reader = new BufferedReader(ReaderBuild.readBuild(jenkinsBuild, buildNumber));
            logService.addLog(reader, projectName, buildNumber, jenkinsBuild.getBuildType(), jenkinsBuild.getTimeIsSet());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @CrossOrigin
    @PostMapping("/creates/{projectName}")
    public void addLogOfJenkinsBuild(@PathVariable String projectName, @RequestParam(name="begin", defaultValue = "1") int begin){
        JenkinsBuild jenkinsBuild = jenkinsBuildService.getJenkinsBuildByProjectName(projectName);
        try {
            int lastBuild = ReaderBuild.lastBuild(jenkinsBuild);
            for (int i = begin; i <= lastBuild; i++) {
                if(!logService.buildAlreadyExist(projectName, i)){
                    try{
                        BufferedReader reader = new BufferedReader(ReaderBuild.readBuild(jenkinsBuild, i));
                        logService.addLog(reader, projectName ,i, jenkinsBuild.getBuildType(), jenkinsBuild.getTimeIsSet());
                    } catch (NullPointerException e) {
                        System.out.println("Build not Exist");
                    }
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @CrossOrigin
    @GetMapping("/{n}")
    public Log getLog(@PathVariable int n) {
        return logService.getLog(n);
    }

    @CrossOrigin
    @GetMapping("/project/{projectName}")
    public List<Log> getLogsByProject(@PathVariable String projectName, @RequestParam(name="begin", defaultValue = "0") int begin, @RequestParam(name="end", defaultValue = "-1") int end) {
        List<Log> logs = logService.getLogByProject(projectName);
        int size = end;
        if(end == -1 || end > logs.size()) size = logs.size();
        List<Log> test = new LinkedList<Log>();
        for(int i = begin; i < size; i++){
            test.add(logs.get(i));
        }
        return test;
    }

    @CrossOrigin
    @GetMapping("/project/{projectName}/{n}")
    public Log getLogByProject(@PathVariable String projectName, @PathVariable int n) {
        return logService.getLogByProject(projectName).get(n);
    }

    @CrossOrigin
    @PostMapping("/delete/{n}")
    public void deleteLog(@PathVariable int n) {
        logService.deleteLog(n);
    }
}
