package com.api.controller;

import com.api.model.JenkinsBuild;
import com.api.services.JenkinsBuildService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/jenkins/build")
public class JenkinsBuildController {
    
    @Autowired
    private JenkinsBuildService jenkinsBuildService;

    
    @CrossOrigin
    @PostMapping("/create")
    public void setJenkinsBuild(@RequestParam(value="path") String path, @RequestParam(value="url") String url, @RequestParam(value="pj") String projectName, @RequestParam(value="user", defaultValue = "") String username, @RequestParam(value="pass", defaultValue = "") String password) {
        jenkinsBuildService.addJenkinsBuild(new JenkinsBuild(url, projectName, username, password, path));
    }

    @CrossOrigin
    @PostMapping("/delete/{projectName}")
    public void deleteJenkinsBuild(@PathVariable String projectName) {
        jenkinsBuildService.deleteJenkinsBuildByProjectName(projectName);
    }

    @CrossOrigin
    @PostMapping("/update/password/{projectName}")
    public void updateJenkinsBuildPassword(@PathVariable String projectName, @RequestParam(value="pass") String password) {
        jenkinsBuildService.updateJenkinsBuildPassword(projectName, password);
    }

    @CrossOrigin
    @PostMapping("/update/username/{projectName}/{username}")
    public void updateJenkinsBuildUsername(@PathVariable String projectName, @PathVariable String username) {
        jenkinsBuildService.updateJenkinsBuildUsername(projectName, username);
    }

    @CrossOrigin
    @PostMapping("/update/url/{projectName}")
    public void updateJenkinsBuildUrl(@PathVariable String projectName, @RequestParam(value="url") String url) {
        jenkinsBuildService.updateJenkinsBuildUrl(projectName, url);
    }

    @CrossOrigin
    @PostMapping("/update/project/{projectName}/{newProjectName}")
    public void updateJenkinsBuildProject(@PathVariable String projectName, @PathVariable String newProjectName) {
        jenkinsBuildService.updateJenkinsBuildProjectName(projectName, newProjectName);
    }


}
