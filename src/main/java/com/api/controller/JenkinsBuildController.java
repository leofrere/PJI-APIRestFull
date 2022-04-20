package com.api.controller;

import com.api.model.JenkinsBuild;
import com.api.repository.JenkinsBuildRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JenkinsBuildController {
    
    @Autowired
    private JenkinsBuildRepository jenkinsBuildRepository;

    @GetMapping("/jenkins/build/create")
    public void setJenkinsBuild(@RequestParam(value="url") String url, @RequestParam(value="pj") String projectName, @RequestParam(value="user") String username, @RequestParam(value="pass") String password) {
        jenkinsBuildRepository.save(new JenkinsBuild(url, projectName, username, password));
    }

}
