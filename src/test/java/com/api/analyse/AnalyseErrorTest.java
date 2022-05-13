package com.api.analyse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.api.model.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnalyseErrorTest {
    
    private List<Log> logs;
 
    @BeforeAll
    public void init() {
        logs = new LinkedList<Log>();
        CompilePhase c1 = new CompilePhase();
        CompilePhase c2 = new CompilePhase();
        CompilePhase c3 = new CompilePhase();
        CompilePhase c4 = new CompilePhase();
        CompilePhase c5 = new CompilePhase();

        TestPhase t1 = new TestPhase();
        TestPhase t2 = new TestPhase();
        TestPhase t3 = new TestPhase();
        TestPhase t4 = new TestPhase();
        TestPhase t5 = new TestPhase();

        PackagePhase p1 = new PackagePhase();
        PackagePhase p2 = new PackagePhase();
        PackagePhase p3 = new PackagePhase();
        PackagePhase p4 = new PackagePhase();
        PackagePhase p5 = new PackagePhase();

        c1.setErrorsTrace("11:26:35,567 [ERROR] /Users/leofrere/.jenkins/workspace/mavenGL/src/main/java/bank/CompteEpargne.java:[11,17] cannot find symbol");
        c3.setErrorsTrace("11:26:35,567 [ERROR] /Users/leofrere/.jenkins/workspace/mavenGL/src/main/java/bank/CompteEpargne.java:[11,17] cannot find symbol");
        t4.setErrorsTrace("16:30:31,776 [ERROR] /Users/leofrere/.jenkins/workspace/mavenGL/src/test/java/bank/BankTest.java:[152,49] variable e is already defined in method");
        p5.setErrorsTrace("16:30:31,776 [ERROR] /Users/leofrere/.jenkins/workspace/mavenGL/src/test/java/bank/BankTest.java:[152,49] an other error");
        
        List<Order> l1 = new LinkedList<Order>();
        List<Order> l2 = new LinkedList<Order>();
        List<Order> l3 = new LinkedList<Order>();
        List<Order> l4 = new LinkedList<Order>();
        List<Order> l5 = new LinkedList<Order>();

        l1.add(new Order(c1, t1, p1, "errorTest", "project", 1));
        l2.add(new Order(c2, t2, p2, "errorTest", "project", 2));
        l3.add(new Order(c3, t3, p3, "errorTest", "project", 3));
        l4.add(new Order(c4, t4, p4, "errorTest", "project", 4));
        l5.add(new Order(c5, t5, p5, "errorTest", "project", 5));

        logs.add(new Log("AnalyseErrorTest", 1, l1, 0, null));
        logs.add(new Log("AnalyseErrorTest", 2, l2, 0, null));
        logs.add(new Log("AnalyseErrorTest", 3, l3, 0, null));
        logs.add(new Log("AnalyseErrorTest", 4, l4, 0, null));
        logs.add(new Log("AnalyseErrorTest", 5, l5, 0, null));
    }

    @Test
    public void proportionOfBuildErrorTest() {
        float proportionOfBuildError = AnalyseError.proportionOfBuildError(logs, 0, 4, 0);
        assertEquals(0.8, proportionOfBuildError, 0.001);
    }

    @Test
    public void numberOfErrorPerTypeTest(){
        Map<String, Integer> numberOfErrorPerType = AnalyseError.numberOfErrorPerType(logs, 0, 4, 0);
        assertEquals(2, numberOfErrorPerType.get("cannot find symbol"));
        assertEquals(1, numberOfErrorPerType.get("variable e is already defined in method"));
        assertEquals(1, numberOfErrorPerType.get("an other error"));

    }
}
