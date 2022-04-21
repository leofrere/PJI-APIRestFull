package com.api.controller;

import java.io.BufferedReader;
import java.util.List;

import com.api.model.JenkinsBuild;
import com.api.model.Order;
import com.api.services.JenkinsBuildService;
import com.api.services.OrderService;
import com.api.utils.ReaderBuild;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private JenkinsBuildService jenkinsBuildService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/log/create")
    public void setLog(@RequestParam(value="pj") String projectName, @RequestParam(value="build") int buildNumber) {

        JenkinsBuild jenkinsBuild = jenkinsBuildService.getJenkinsBuildByProjectName(projectName);
        if (jenkinsBuild == null) {
            return ;
        }

        try {
            BufferedReader reader = new BufferedReader(ReaderBuild.readBuild(jenkinsBuild, buildNumber));
            orderService.addOrder(reader, buildNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @GetMapping("/log/creates")
    public void addLogOfJenkinsBuild(@RequestParam(value="pj") String projectName){
        JenkinsBuild jenkinsBuild = jenkinsBuildService.getJenkinsBuildByProjectName(projectName);
        try {
            int lastBuild = ReaderBuild.lastBuild(jenkinsBuild);
            for (int i = 1; i <= lastBuild; i++) {
                if(!orderService.buildAlreadyExist(projectName, i)){
                    try{
                        BufferedReader reader = new BufferedReader(ReaderBuild.readBuild(jenkinsBuild, i));
                        orderService.addOrder(reader, i);
                    } catch (NullPointerException e) {
                        System.out.println("Build not Exist");
                    }
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/log")
    public Order getLog(@RequestParam(value="id") Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/log/project")
    public List<Order> getLogByProject(@RequestParam(value="project") String project) {
        return orderService.getOrderByProject(project);
    }

    @GetMapping("/log/delete")
    public void deleteLog(@RequestParam(value="id") Long id) {
        orderService.deleteOrder(id);
    }
    
}
