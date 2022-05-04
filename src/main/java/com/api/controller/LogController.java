package com.api.controller;

import java.io.BufferedReader;
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
import org.springframework.web.bind.annotation.RequestMapping;
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
    @GetMapping("/create/{projectName}/{buildNumber}")
    public void setLog(@PathVariable String projectName, @PathVariable int buildNumber) {

        JenkinsBuild jenkinsBuild = jenkinsBuildService.getJenkinsBuildByProjectName(projectName);
        if (jenkinsBuild == null) {
            return ;
        }

        try {
            BufferedReader reader = new BufferedReader(ReaderBuild.readBuild(jenkinsBuild, buildNumber));
            logService.addLog(reader, projectName, buildNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @CrossOrigin
    @GetMapping("/creates/{projectName}")
    public void addLogOfJenkinsBuild(@PathVariable String projectName){
        JenkinsBuild jenkinsBuild = jenkinsBuildService.getJenkinsBuildByProjectName(projectName);
        try {
            int lastBuild = ReaderBuild.lastBuild(jenkinsBuild);
            for (int i = 1; i <= lastBuild; i++) {
                if(!logService.buildAlreadyExist(projectName, i)){
                    try{
                        BufferedReader reader = new BufferedReader(ReaderBuild.readBuild(jenkinsBuild, i));
                        logService.addLog(reader, projectName ,i);
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
    public List<Log> getLogsByProject(@PathVariable String projectName) {
        return logService.getLogByProject(projectName);
    }

    @CrossOrigin
    @GetMapping("/project/{projectName}/{n}")
    public Log getLogByProject(@PathVariable String projectName, @PathVariable int n) {
        return logService.getLogByProject(projectName).get(n);
    }

    @CrossOrigin
    @GetMapping("/delete/{n}")
    public void deleteLog(@PathVariable int n) {
        logService.deleteLog(n);
    }
}
