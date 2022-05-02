package com.api.analyse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import com.api.model.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnalyseCompileTest {

    private List<Log> logs;

    @BeforeAll
    public void init() {
        logs = new LinkedList<Log>();
        CompilePhase c1 = new CompilePhase();
        CompilePhase c2 = new CompilePhase();
        CompilePhase c3 = new CompilePhase();
        CompilePhase c4 = new CompilePhase();
        CompilePhase c5 = new CompilePhase();

        c1.setNumberOfClasses(5);
        c2.setNumberOfClasses(7);
        c3.setNumberOfClasses(8);
        c4.setNumberOfClasses(12);
        c5.setNumberOfClasses(19);

        TestPhase t1 = new TestPhase();
        TestPhase t2 = new TestPhase();
        TestPhase t3 = new TestPhase();
        TestPhase t4 = new TestPhase();
        TestPhase t5 = new TestPhase();

        t1.setNumberOfTestClasses(2);
        t2.setNumberOfTestClasses(4);
        t3.setNumberOfTestClasses(7);
        t4.setNumberOfTestClasses(9);
        t5.setNumberOfTestClasses(15);

        PackagePhase p1 = new PackagePhase();
        PackagePhase p2 = new PackagePhase();
        PackagePhase p3 = new PackagePhase();
        PackagePhase p4 = new PackagePhase();
        PackagePhase p5 = new PackagePhase();
        
        List<Order> l1 = new LinkedList<Order>();
        List<Order> l2 = new LinkedList<Order>();
        List<Order> l3 = new LinkedList<Order>();
        List<Order> l4 = new LinkedList<Order>();
        List<Order> l5 = new LinkedList<Order>();

        l1.add(new Order(c1, t1, p1, "compileTest", "project", 1));
        l2.add(new Order(c2, t2, p2, "compileTest", "project", 2));
        l3.add(new Order(c3, t3, p3, "compileTest", "project", 3));
        l4.add(new Order(c4, t4, p4, "compileTest", "project", 4));
        l5.add(new Order(c5, t5, p5, "compileTest", "project", 5));

        logs.add(new Log("AnalyseCompileTest", 1, l1));
        logs.add(new Log("AnalyseCompileTest", 2, l2));
        logs.add(new Log("AnalyseCompileTest", 3, l3));
        logs.add(new Log("AnalyseCompileTest", 4, l4));
        logs.add(new Log("AnalyseCompileTest", 5, l5));
    }

    @Test
    public void increaseNumberOfCompileClassBetweenTwoLogsOfCompilePhaseTest(){
        int res = AnalyseCompile.increaseNumberOfCompileClassBetweenTwoLogs(logs.get(0), logs.get(1), "compile", 0);
        assertEquals(2, res);
    }

    @Test
    public void increaseNumberOfCompileClassBetweenTwoLogsOfTestPhaseTest(){
        int res = AnalyseCompile.increaseNumberOfCompileClassBetweenTwoLogs(logs.get(1), logs.get(2), "test", 0);
        assertEquals(3, res);
    }

    @Test
    public void increaseNumberOfCompileClassBetweenTwoLogsInPercentOfCompilePhaseTest(){
        float res = AnalyseCompile.increaseNumberOfCompileClassInPercentBetweenTwoLogs(logs.get(0), logs.get(1), "compile", 0);
        assertEquals(0.40, res, 0.01);
    }

    @Test
    public void increaseNumberOfCompileClassBetweenTwoLogsInPercentOfTestPhaseTest(){
        float res = AnalyseCompile.increaseNumberOfCompileClassInPercentBetweenTwoLogs(logs.get(1), logs.get(2), "test", 0);
        assertEquals(0.75, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfCompileClassBetweenOfCompilePhaseTest(){
        float res = AnalyseCompile.increaseMeanNumberOfCompileClassBetween(logs, 0, 4, "compile", 0);
        assertEquals(3.5, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfCompileClassBetweenOfTestPhaseTest(){
        float res = AnalyseCompile.increaseMeanNumberOfCompileClassBetween(logs, 0, 4, "test", 0);
        assertEquals(3.25, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfCompileClassBetweenOfCompilePhaseInPercentTest(){
        float res = AnalyseCompile.increaseMeanNumberOfCompileClassInPercentBetween(logs, 0, 4, "compile", 0);
        assertEquals(0.40, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfCompileClassBetweenOfTestPhaseInPercentTest(){
        float res = AnalyseCompile.increaseMeanNumberOfCompileClassInPercentBetween(logs, 0, 4, "test", 0);
        assertEquals(0.67, res, 0.01);
    }

    @Test
    public void meanClassCompiledOfCompilePhaseTest(){
        float res = AnalyseCompile.meanClassCompiled(logs, 0, 4, "compile", 0);
        assertEquals(10.19, res, 0.01);
    }

    @Test
    public void meanClassCompiledOfTestPhaseTest(){
        float res = AnalyseCompile.meanClassCompiled(logs, 0, 4, "test", 0);
        assertEquals(7.40, res, 0.01);
    }

    @Test
    public void medianClassCompiledCompilePhaseTest(){
        float res = AnalyseCompile.medianClassCompiled(logs, 0, 4, "compile", 0);
        assertEquals(8, res, 0.01);
    }

    @Test
    public void medianClassCompiledTestPhaseTest(){
        float res = AnalyseCompile.medianClassCompiled(logs, 0, 4, "test", 0);
        assertEquals(7, res, 0.01);
    }

    @Test
    public void firstQuartileBetweenCompilePhaseTest(){
        float res = AnalyseCompile.firstQuartileBetween(logs, 0, 4, "compile", 0);
        assertEquals(7.5, res, 0.01);
    }

    @Test
    public void firstQuartileBetweenTestPhaseTest(){
        float res = AnalyseCompile.firstQuartileBetween(logs, 0, 4, "test", 0);
        assertEquals(5.5, res, 0.01);
    }

    @Test
    public void thirdQuartileBetweenCompilePhaseTest(){
        float res = AnalyseCompile.thirdQuartileBetween(logs, 0, 4, "compile", 0);
        assertEquals(10, res, 0.01);
    }

    @Test
    public void thirdQuartileBetweenTestPhaseTest(){
        float res = AnalyseCompile.thirdQuartileBetween(logs, 0, 4, "test", 0);
        assertEquals(8, res, 0.01);
    }



}
