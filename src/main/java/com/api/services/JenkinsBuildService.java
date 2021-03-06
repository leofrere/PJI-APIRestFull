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

    public void addJenkinsBuild(JenkinsBuild jenkinsBuild) {
        jenkinsBuildRepository.save(jenkinsBuild);
    }

    public void deleteJenkinsBuildByProjectName(String projectName) {
        JenkinsBuild jenkinsBuild = getJenkinsBuildByProjectName(projectName);
        jenkinsBuildRepository.delete(jenkinsBuild);
    }

    public void updateJenkinsBuildPassword(String projectName, String password) {
        JenkinsBuild jenkinsBuild = getJenkinsBuildByProjectName(projectName);
        jenkinsBuild.setPassword(password);
        jenkinsBuildRepository.save(jenkinsBuild);
    }

    public void updateJenkinsBuildUrl(String projectName, String url) {
        JenkinsBuild jenkinsBuild = getJenkinsBuildByProjectName(projectName);
        jenkinsBuild.setUrl(url);
        jenkinsBuildRepository.save(jenkinsBuild);
    }

    public void updateJenkinsBuildUsername(String projectName, String username) {
        JenkinsBuild jenkinsBuild = getJenkinsBuildByProjectName(projectName);
        jenkinsBuild.setUsername(username);
        jenkinsBuildRepository.save(jenkinsBuild);
    }

    public void updateJenkinsBuildProjectName(String projectName, String newProjectName) {
        JenkinsBuild jenkinsBuild = getJenkinsBuildByProjectName(projectName);
        jenkinsBuild.setProjectName(newProjectName);
        jenkinsBuildRepository.save(jenkinsBuild);
    }

}
