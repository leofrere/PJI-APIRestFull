package com.api.controller;

import com.api.model.JenkinsBuild;
import com.api.services.JenkinsBuildService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jenkins/build")
public class JenkinsBuildController {
    
    @Autowired
    private JenkinsBuildService jenkinsBuildService;

    @GetMapping("/create")
    public void setJenkinsBuild(@RequestParam(value="url") String url, @RequestParam(value="pj") String projectName, @RequestParam(value="user") String username, @RequestParam(value="pass") String password) {
        jenkinsBuildService.addJenkinsBuild(new JenkinsBuild(url, projectName, username, password));
    }

    @GetMapping("/delete/{projectName}")
    public void deleteJenkinsBuild(@PathVariable String projectName) {
        jenkinsBuildService.deleteJenkinsBuildByProjectName(projectName);
    }

    @GetMapping("/update/password/{projectName}")
    public void updateJenkinsBuildPassword(@PathVariable String projectName, @RequestParam(value="pass") String password) {
        jenkinsBuildService.updateJenkinsBuildPassword(projectName, password);
    }

    @GetMapping("/update/username/{projectName}/{username}")
    public void updateJenkinsBuildUsername(@PathVariable String projectName, @PathVariable String username) {
        jenkinsBuildService.updateJenkinsBuildUsername(projectName, username);
    }

    @GetMapping("/update/url/{projectName}")
    public void updateJenkinsBuildUrl(@PathVariable String projectName, @RequestParam(value="url") String url) {
        jenkinsBuildService.updateJenkinsBuildUrl(projectName, url);
    }

    @GetMapping("/update/project/{projectName}/{newProjectName}")
    public void updateJenkinsBuildProject(@PathVariable String projectName, @PathVariable String newProjectName) {
        jenkinsBuildService.updateJenkinsBuildProjectName(projectName, newProjectName);
    }


}
