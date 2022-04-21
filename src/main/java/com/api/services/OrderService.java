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

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CompilePhaseService compileService;

    @Autowired
    private TestPhaseService testService;

    @Autowired
    private PackagePhaseService packageService;

    public Order getOrderById(long id) {
        return orderRepository.findById(id).get();
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public List<Order> getOrderByProject(String project) {
        List<Order> list = orderRepository.findAll();
        LinkedList<Order> OrderOfProject = new LinkedList<Order>();

        for (Order order : list) {
            if (order.getProject().equals(project)) {
                OrderOfProject.add(order);
            }
        }

        return OrderOfProject;
    }

    public void addOrder(BufferedReader reader, int buildNumber) {
        CompilePhase compilePhase = compileService.addCompilePhase(reader);
        TestPhase testPhase = testService.addTestPhase(reader);
        PackagePhase packagePhase = packageService.addPackagePhase(reader);
        orderRepository.save(new Order("mavenGL", buildNumber, compilePhase, testPhase, packagePhase));
    }

    public boolean buildAlreadyExist(String project, int buildNumber) {
        List<Order> list = orderRepository.findAll();
        for (Order order : list) {
            if (order.getProject().equals(project) && order.getBuild() == buildNumber) {
                return true;
            }
        }
        return false;
    }

    public void deleteOrder(long id) {
        Order order = orderRepository.findById(id).get();
        long compileId = order.getCompilePhase().getId();
        long testId = order.getTestPhase().getId();
        long packageId = order.getPackagePhase().getId();
        orderRepository.deleteById(id);
        compileService.deleteCompilePhase(compileId);
        testService.deleteTestPhase(testId);
        packageService.deletePackagePhase(packageId);
    }

}
