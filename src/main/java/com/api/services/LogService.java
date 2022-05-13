package com.api.services;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;

import com.api.model.Log;
import com.api.model.Order;
import com.api.repository.LogRepository;
import com.api.repository.OrderRepository;
import com.api.utils.LogSort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class LogService {
    
    @Autowired
    private OrderService orderService;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private OrderRepository orderRepository;

    public String setOrdersName(BufferedReader reader, List<String> ordersName){
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                if(line.contains("Build Order")){
                    while ((line = reader.readLine()) != null) {
                        if(line.contains("jar")){
                            ordersName.add(line.split(" ")[2]);
                            continue;
                        }

                        if(line.contains("[INFO] Building")){
                            break;
                        }
                    }
                    break;                   
                }

                if(line.contains("[INFO] Building")){
                    break;
                }
            } 

            return line;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ""; 
    }

    public void addLog(BufferedReader reader, String projectName, int build, String type) {
        LinkedList<Order> orders = new LinkedList<Order>();
        LinkedList<String> ordersName = new LinkedList<String>();
        String line = null;
        int offset = 1;
        String statusTmp = "ABORTED";
        LinkedList<Order> finalOrders = new LinkedList<Order>();

        try {

            if(type.equals("multiBuilds") || type.equals("singleBuild")){
                while ((line = reader.readLine()) != null) {
                    if(line.contains("Building") && !line.contains("jar") && line.contains("INFO")){
                        String orderName = line.split(" ")[2 + offset];
                        Order order = orderService.addOrder(reader, orderName, projectName, build);

                        orders.add(order);
                        continue;
                    }
                    if(line.contains("Finished:") && !line.contains("INFO")){
                        statusTmp = line.split(" ")[1];
                    }
                }
                logRepository.save(new Log(projectName, build, orders, orders.size(), statusTmp));
            } else {
                line = setOrdersName(reader, ordersName);
                for(String name : ordersName){
                        Order order = orderService.addOrderModule(reader, name, projectName, build);
                        orders.add(order);
                }

                int i = 0;
                while((line = reader.readLine()) != null){
                    if(line.contains(ordersName.get(i)) && line.contains("INFO") && line.contains("......")){
                        String[] parts = line.split(" ");
                        orders.get(i).setTimeOfBuild(timeOfBuild(parts[7 + offset], parts[6 + offset]));
                    }

                    if(line.contains("Finished:") && !line.contains("INFO")){
                        statusTmp = line.split(" ")[1];
                    }

                    if(i+1 < orders.size()) i++;
        
                }


                System.out.println(finalOrders.size());
                    

                for(Order order : orders){
                    finalOrders.add(orderRepository.save(order));
                }
                logRepository.save(new Log(projectName, build, finalOrders, orders.size(), statusTmp)); 
            }

                           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GraphQLQuery(name = "logs")
    public List<Log> getAllLog() {
        return logRepository.findAll();
    }

    @GraphQLQuery(name = "logsByProject")
    public List<Log> getLogByProject(@GraphQLArgument(name = "project") String project) {
        List<Log> list = logRepository.findAll();
        LinkedList<Log> logOfProject = new LinkedList<Log>();

        for (Log log : list) {
            if (log.getProject().equals(project)) {
                logOfProject.add(log);
            }
        }
        LogSort.sortBuild(logOfProject);
        return  logOfProject;
    }

    public boolean buildAlreadyExist(String project, int buildNumber) {
        List<Log> list = logRepository.findAll();
        for (Log log : list) {
            if (log.getProject().equals(project) && log.getBuild() == buildNumber) {
                return true;
            }
        }
        return false;
    }

    @GraphQLQuery(name = "log")
    public Log getLog(@GraphQLArgument(name = "n") int n) {
        return logRepository.findAll().get(n);
    }

    public void deleteLog(int n) {
        Log log = logRepository.findAll().get(n);
        logRepository.delete(log);
    }

    private float timeOfBuild(String typeOfTime, String time){
        float res = 0;
        System.out.println(time);
        if(typeOfTime.equals("min")){
            String[] parts = time.split(":");
            int minutes = Integer.parseInt(parts[0]);
            int seconds = Integer.parseInt(parts[1]);
            res = seconds + minutes * 60;
        } else if(typeOfTime.equals("h")){
            String[] parts = time.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            res = minutes * 60 + hours * 3600;
        } else{
            res = Float.parseFloat(time);
        }

        return res;
    }
}
