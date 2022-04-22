package com.api.services;

import java.io.BufferedReader;
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

    public Order addOrder(BufferedReader reader, String name) {
        CompilePhase compilePhase = compileService.addCompilePhase(reader);
        TestPhase testPhase = testService.addTestPhase(reader);
        PackagePhase packagePhase = packageService.addPackagePhase(reader);
        return orderRepository.save(new Order(compilePhase, testPhase, packagePhase, name));
    }

}
