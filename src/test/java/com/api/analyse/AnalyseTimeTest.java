package com.api.analyse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import com.api.model.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnalyseTimeTest {
    
    private List<Log> logs;

    @BeforeAll
    public void init() {
        logs = new LinkedList<Log>();
        CompilePhase c1 = new CompilePhase();
        CompilePhase c2 = new CompilePhase();
        CompilePhase c3 = new CompilePhase();
        CompilePhase c4 = new CompilePhase();
        CompilePhase c5 = new CompilePhase();

        c1.setTime("2,54s");
        c2.setTime("1,79s");
        c3.setTime("3,13s");
        c5.setTime("2,68s");

        TestPhase t1 = new TestPhase();
        TestPhase t2 = new TestPhase();
        TestPhase t3 = new TestPhase();
        TestPhase t4 = new TestPhase();
        TestPhase t5 = new TestPhase();

        t1.setTime("5,78s");
        t2.setTime("6,24s");
        t3.setTime("6,10s");
        t5.setTime("8,31s");

        PackagePhase p1 = new PackagePhase();
        PackagePhase p2 = new PackagePhase();
        PackagePhase p3 = new PackagePhase();
        PackagePhase p4 = new PackagePhase();
        PackagePhase p5 = new PackagePhase();

        p1.setTime("1,54s");
        p2.setTime("1,79s");
        p3.setTime("3,13s");
        p5.setTime("2,68s");
        
        List<Order> l1 = new LinkedList<Order>();
        List<Order> l2 = new LinkedList<Order>();
        List<Order> l3 = new LinkedList<Order>();
        List<Order> l4 = new LinkedList<Order>();
        List<Order> l5 = new LinkedList<Order>();

        l1.add(new Order(c1, t1, p1, "timeTest"));
        l2.add(new Order(c2, t2, p2, "timeTest"));
        l3.add(new Order(c3, t3, p3, "timeTest"));
        l4.add(new Order(c4, t4, p4, "timeTest"));
        l5.add(new Order(c5, t5, p5, "timeTest"));

        logs.add(new Log("AnalyseTimeTest", 1, l1));
        logs.add(new Log("AnalyseTimeTest", 2, l2));
        logs.add(new Log("AnalyseTimeTest", 3, l3));
        logs.add(new Log("AnalyseTimeTest", 4, l4));
        logs.add(new Log("AnalyseTimeTest", 5, l5));
    }

    @Test
    public void increaseTimeBetweenTwoLogsOfCompilePhaseTest(){
        float res = AnalyseTime.increaseTimeBetweenTwoLogs(logs.get(0), logs.get(1), "compile", 0);
        assertEquals(-0.75, res, 0.01);
    }

    @Test
    public void increaseTimeBetweenTwoLogsOfTestPhaseTest(){
        float res = AnalyseTime.increaseTimeBetweenTwoLogs(logs.get(0), logs.get(1), "test", 0);
        assertEquals(0.45, res, 0.01);
    }

    @Test
    public void increaseTimeBetweenTwoLogsOfPackagePhaseTest(){
        float res = AnalyseTime.increaseTimeBetweenTwoLogs(logs.get(0), logs.get(1), "package", 0);
        assertEquals(0.25, res, 0.01);
    }

    @Test
    public void increaseTimeBetweenTwoLogsOfCompilePhaseWithTimeNullTest(){
        float res = AnalyseTime.increaseTimeBetweenTwoLogs(logs.get(2), logs.get(3), "compile", 0);
        assertEquals(0, res, 0.01);
    }

    @Test
    public void increaseTimeBetweenTwoLogsOfTestPhaseWithTimeNullTest(){
        float res = AnalyseTime.increaseTimeBetweenTwoLogs(logs.get(1), logs.get(3), "test", 0);
        assertEquals(0, res, 0.01);
    }

    @Test
    public void increaseTimeBetweenTwoLogsOfPackagePhaseWithTimeNullTest(){
        float res = AnalyseTime.increaseTimeBetweenTwoLogs(logs.get(0), logs.get(3), "package", 0);
        assertEquals(0, res, 0.01);
    }

    @Test
    public void increaseTimeBetweenTwoLogsInPercentOfCompilePhaseTest(){
        float res = AnalyseTime.increaseTimeBetweenTwoLogsInPercent(logs.get(0), logs.get(1), "compile", 0);
        assertEquals(-0.29, res, 0.01);
    }

    @Test
    public void increaseTimeBetweenTwoLogsInPercentOfTestPhaseTest(){
        float res = AnalyseTime.increaseTimeBetweenTwoLogsInPercent(logs.get(0), logs.get(1), "test", 0);
        assertEquals(0.07, res, 0.01);
    }

    @Test
    public void increaseTimeBetweenTwoLogsInPercentOfPackagePhaseTest(){
        float res = AnalyseTime.increaseTimeBetweenTwoLogsInPercent(logs.get(0), logs.get(1), "package", 0);
        assertEquals(0.16, res, 0.01);
    }

    @Test
    public void increaseTimeBetweenTwoLogsInPercentOfCompilePhaseWithTimeNullTest(){
        float res = AnalyseTime.increaseTimeBetweenTwoLogsInPercent(logs.get(2), logs.get(3), "compile", 0);
        assertEquals(0, res, 0.01);
    }

    @Test
    public void increaseTimeBetweenTwoLogsInPercentOfTestPhaseWithTimeNullTest(){
        float res = AnalyseTime.increaseTimeBetweenTwoLogsInPercent(logs.get(1), logs.get(3), "test", 0);
        assertEquals(0, res, 0.01);
    }

    @Test
    public void increaseTimeBetweenTwoLogsInPercentOfPackagePhaseWithTimeNullTest(){
        float res = AnalyseTime.increaseTimeBetweenTwoLogsInPercent(logs.get(0), logs.get(3), "package", 0);
        assertEquals(0, res, 0.01);
    }

    @Test
    public void increaseMeanTimeBetweenOfCompilePhaseTest(){
        float res = AnalyseTime.increaseMeanTimeBetween(logs, 0, 4, "compile", 0);
        assertEquals(0.29, res, 0.01);
    }

    @Test
    public void increaseMeanTimeBetweenOfTestPhaseTest(){
        float res = AnalyseTime.increaseMeanTimeBetween(logs, 0, 4, "test", 0);
        assertEquals(0.15, res, 0.01);
    }

    @Test
    public void increaseMeanTimeBetweenOfPackagePhaseTest(){
        float res = AnalyseTime.increaseMeanTimeBetween(logs,0, 4, "package", 0);
        assertEquals(0.79, res, 0.01);
    }

    @Test
    public void increaseMeanTimeBetweenOfCompilePhaseInPercentTest(){
        float res = AnalyseTime.increaseMeanTimeBetweenInPercent(logs, 0, 4, "compile", 0);
        assertEquals(0.22, res, 0.01);
    }

    @Test
    public void increaseMeanTimeBetweenOfTestPhaseInPercentTest(){
        float res = AnalyseTime.increaseMeanTimeBetweenInPercent(logs, 0, 4, "test", 0);
        assertEquals(0.02, res, 0.01);
    }

    @Test
    public void increaseMeanTimeBetweenOfPackagePhaseInPercentTest(){
        float res = AnalyseTime.increaseMeanTimeBetweenInPercent(logs,0, 4, "package", 0);
        assertEquals(0.45, res, 0.01);
    }

    @Test
    public void meanTimeBetweenOfCompilePhaseTest(){
        float res = AnalyseTime.meanTimeBetween(logs, 0, 4, "compile", 0);
        assertEquals(2.53, res, 0.01);
    }

    @Test
    public void meanTimeBetweenOfTestPhaseTest(){
        float res = AnalyseTime.meanTimeBetween(logs, 0, 4, "test", 0);
        assertEquals(6.60, res, 0.01);
    }

    @Test
    public void meanTimeBetweenOfPackagePhaseTest(){
        float res = AnalyseTime.meanTimeBetween(logs,0, 4, "package", 0);
        assertEquals(2.28, res, 0.01);
    }


}
