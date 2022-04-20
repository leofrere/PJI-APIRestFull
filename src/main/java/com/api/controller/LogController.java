package com.api.controller;

import java.io.BufferedReader;
import java.util.List;

import com.api.model.JenkinsBuild;
import com.api.model.Log;
import com.api.services.JenkinsBuildService;
import com.api.services.LogService;
import com.api.utils.ReaderBuild;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @Autowired
    private JenkinsBuildService jenkinsBuildService;

    @Autowired
    private LogService logService;

    @GetMapping("/log/create")
    public void setLog(@RequestParam(value="pj") String projectName, @RequestParam(value="build") int buildNumber) {

        JenkinsBuild jenkinsBuild = jenkinsBuildService.getJenkinsBuildByProjectName(projectName);
        if (jenkinsBuild == null) {
            return ;
        }

        try {
            BufferedReader reader = new BufferedReader(ReaderBuild.readBuild(jenkinsBuild, buildNumber));
            logService.addLog(reader, buildNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @GetMapping("/log")
    public Log getLog(@RequestParam(value="id") Long id) {
        return logService.getLogById(id);
    }

    @GetMapping("/log/project")
    public List<Log> getLogByProject(@RequestParam(value="project") String project) {
        return logService.getLogByProject(project);
    }

}
