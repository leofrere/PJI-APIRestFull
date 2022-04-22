package com.api.services;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;

import com.api.model.Log;
import com.api.model.Order;
import com.api.repository.LogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    
    @Autowired
    private OrderService orderService;

    @Autowired
    private LogRepository logRepository;

    public void addLog(BufferedReader reader, String projectName, int build) {
        LinkedList<Order> orders = new LinkedList<Order>();
        LinkedList<String> ordersName = new LinkedList<String>();
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

            if(ordersName.size() == 0){
                String logName = line.split(" ")[3];
                Order order = orderService.addOrder(reader, logName);
                orders.add(order);
            } else {
                for (String orderName : ordersName) {
                    while((line = reader.readLine()) != null){
                        if(line.contains("Building " + orderName)){
                            Order order = orderService.addOrder(reader, orderName);
                            orders.add(order);
                            break;
                        }
                    }
                }
            }

            logRepository.save(new Log(projectName, build, orders));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Log> getLogByProject(String project) {
        List<Log> list = logRepository.findAll();
        LinkedList<Log> logOfProject = new LinkedList<Log>();

        for (Log log : list) {
            if (log.getProject().equals(project)) {
                logOfProject.add(log);
            }
        }

        return logOfProject;
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

    public Log getLogById(Long id) {
        return logRepository.findById(id).get();
    }

    public void deleteLog(long id) {
        Log log = logRepository.findById(id).get();
        List<Order> orders = log.getOrders();
        logRepository.delete(log);
        for (Order order : orders) {
            orderService.deleteOrder(order.getId());
        }
    }

}
