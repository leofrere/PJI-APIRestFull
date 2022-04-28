package com.api.controller.abstracts;

import com.api.services.LogService;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AnalyseController {
    
    @Autowired
    protected LogService logService;

    protected int getN2(int n, String projectName) {
        if (n == -1) {
            return logService.getLogByProject(projectName).size() - 1;
        }
        return n;
    }

}
