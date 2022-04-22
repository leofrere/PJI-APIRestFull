package com.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;

import com.api.LogFile;
import com.api.PJIApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PJIApplication.class)
public class LogServiceTest {

    @Autowired
    private LogService logService;

    @Test
    void creationOfListOrdersName() {
        BufferedReader reader = new BufferedReader(new StringReader(LogFile.getLogOfMultiProjectFile()));
        LinkedList<String> ordersName = new LinkedList<String>();
        logService.setOrdersName(reader, ordersName);
        assertEquals(2, ordersName.size());
        assertEquals("module1", ordersName.get(0));
        assertEquals("module2", ordersName.get(1));
    }
}
