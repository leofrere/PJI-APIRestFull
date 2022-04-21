package com.api.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;

import com.api.PJIApplication;
import com.api.model.Log;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PJIApplication.class)
public class LogSortTest {
    
    @Test
    void sortTest(){
        Log log1 = new Log(3);
        Log log2 = new Log(8);
        Log log3 = new Log(1);
        Log log4 = new Log(4);
        Log log5 = new Log(14);
        Log log6 = new Log(6);

        LinkedList<Log> logs = new LinkedList<Log>();
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
