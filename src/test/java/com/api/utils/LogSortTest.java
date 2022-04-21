package com.api.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;

import com.api.PJIApplication;
import com.api.model.Order;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PJIApplication.class)
public class LogSortTest {
    
    @Test
    void sortTest(){
        Order log1 = new Order(3);
        Order log2 = new Order(8);
        Order log3 = new Order(1);
        Order log4 = new Order(4);
        Order log5 = new Order(14);
        Order log6 = new Order(6);

        LinkedList<Order> logs = new LinkedList<Order>();
        logs.add(log1);
        logs.add(log2); 
        logs.add(log3);
        logs.add(log4);
        logs.add(log5);
        logs.add(log6);

        LogSort.sort(logs);

        for(int i = 1; i < logs.size(); i++){
            assertTrue(logs.get(i).getBuild() >= logs.get(i-1).getBuild());
        }
    }

}
