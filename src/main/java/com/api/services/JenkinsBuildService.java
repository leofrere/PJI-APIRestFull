package com.api.services;

import java.util.List;

import com.api.model.JenkinsBuild;
import com.api.repository.JenkinsBuildRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JenkinsBuildService {
    
    @Autowired
    private JenkinsBuildRepository jenkinsBuildRepository;

    public JenkinsBuild getJenkinsBuildById(long id) {
        return jenkinsBuildRepository.findById(id).get();
    }

    public JenkinsBuild getJenkinsBuildByProjectName(String projectName) {
        List<JenkinsBuild> jenkinsBuilds = jenkinsBuildRepository.findAll();
        for (JenkinsBuild jenkinsBuild : jenkinsBuilds) {
            if (jenkinsBuild.getProjectName().equals(projectName)) {
                return jenkinsBuild;
            }
        }
        return null;
    }

    public List<JenkinsBuild> getAllJenkinsBuilds() {
        return jenkinsBuildRepository.findAll();
    }

}
