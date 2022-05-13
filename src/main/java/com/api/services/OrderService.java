package com.api.services;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;

import com.api.model.CompilePhase;
import com.api.model.Order;
import com.api.model.TestPhase;
import com.api.model.PackagePhase;
import com.api.repository.OrderRepository;
import com.api.repository.PackageRepository;
import com.api.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CompilePhaseService compileService;

    @Autowired
    private TestPhaseService testService;

    @Autowired
    private PackagePhaseService packageService;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private PackageRepository packageRepository;

    @GraphQLQuery(name = "order")
    public Order getOrderById(@GraphQLArgument(name = "id") long id) {
        return orderRepository.findById(id).get();
    }

    @GraphQLQuery(name = "orders")
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @GraphQLQuery(name = "ordersByProject")
    public List<Order> getAllOrderByProject(@GraphQLArgument(name = "project") String project) {
        List<Order> orders = orderRepository.findAll();
        List<Order> ordersByProject = new LinkedList<Order>();
        for (Order order : orders) {
            if (order.getProject().equals(project)) {
                ordersByProject.add(order);
            }
        }
        return ordersByProject;
    }

    public Order addOrder(BufferedReader reader, String name, String projectName, int build) {
        Order order;
        CompilePhase compilePhase = compileService.addCompilePhase(reader, projectName, build, name);
        if(compilePhase.getStatus().equals("finished")){
            TestPhase testPhase = testRepository.save(new TestPhase("no information", "0s", 0, 0, 0, 0, 0, 0));
            PackagePhase packagePhase = packageRepository.save(new PackagePhase("no information", "0s", ""));
            order = new Order(compilePhase, testPhase, packagePhase, name, projectName, build);
            order.setTimeOfBuild(finalTime(reader));
            return orderRepository.save(order);
        }
        TestPhase testPhase = testService.addTestPhase(reader, projectName, build, name);
        if(testPhase.getStatus().equals("finished")){
            PackagePhase packagePhase = packageRepository.save(new PackagePhase("no information", "0s", ""));
            order = new Order(compilePhase, testPhase, packagePhase, name, projectName, build);
            order.setTimeOfBuild(finalTime(reader));
            return orderRepository.save(order);
        }
        PackagePhase packagePhase = packageService.addPackagePhase(reader, projectName, build, name);
        order = new Order(compilePhase, testPhase, packagePhase, name, projectName, build);
            order.setTimeOfBuild(finalTime(reader));
            return orderRepository.save(order);
    }

    public Order addOrderModule(BufferedReader reader, String name, String projectName, int build) {
        CompilePhase compilePhase = compileService.addCompilePhase(reader, projectName, build, name);
        if(compilePhase.getStatus().equals("finished")){
            TestPhase testPhase = testRepository.save(new TestPhase("no information", "0s", 0, 0, 0, 0, 0, 0));
            PackagePhase packagePhase = packageRepository.save(new PackagePhase("no information", "0s", ""));
            return new Order(compilePhase, testPhase, packagePhase, name, projectName, build);
        }
        TestPhase testPhase = testService.addTestPhase(reader, projectName, build, name);
        if(testPhase.getStatus().equals("finished")){
            PackagePhase packagePhase = packageRepository.save(new PackagePhase("no information", "0s", ""));
            return new Order(compilePhase, testPhase, packagePhase, name, projectName, build);
        }
        PackagePhase packagePhase = packageService.addPackagePhase(reader, projectName, build, name);
        return new Order(compilePhase, testPhase, packagePhase, name, projectName, build);
    }

    private float finalTime(BufferedReader reader){
        String line = "";
        float finalTime = 0;
        int offset = 0;
        try {
            while((line = reader.readLine()) != null){
                if(line.contains("Total time:") && line.contains("INFO")){
                    String parts[] = line.split(" ");
                    if(parts.length == 6) offset++;
                    finalTime = timeOfBuild(parts[4 + offset],parts[3 + offset]);
                    break;
                }
            }
        } catch (Exception e) {
           System.out.println(e);
        }
        return finalTime;
    }

    private float timeOfBuild(String typeOfTime, String time){
        float res = 0;
        System.out.println(typeOfTime + " " + time);
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
