package com.api.services;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;

import com.api.model.CompilePhase;
import com.api.model.Order;
import com.api.model.TestPhase;
import com.api.model.PackagePhase;
import com.api.repository.OrderRepository;

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
        CompilePhase compilePhase = compileService.addCompilePhase(reader, projectName, build, name);
        TestPhase testPhase = testService.addTestPhase(reader, projectName, build, name);
        PackagePhase packagePhase = packageService.addPackagePhase(reader, projectName, build, name);
        return orderRepository.save(new Order(compilePhase, testPhase, packagePhase, name, projectName, build));
    }

}
