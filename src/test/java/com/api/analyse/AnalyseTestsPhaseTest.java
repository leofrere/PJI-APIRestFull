package com.api.analyse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import com.api.model.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnalyseTestsPhaseTest {
    
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

        t1.setTestsRun(13);
        t2.setTestsRun(17);
        t3.setTestsRun(11);
        t4.setTestsRun(13);
        t5.setTestsRun(27);

        t1.setTestsFailed(1);
        t2.setTestsFailed(2);
        t3.setTestsFailed(3);
        t4.setTestsFailed(4);
        t5.setTestsFailed(0);

        t1.setTestsSkipped(1);
        t2.setTestsSkipped(2);
        t3.setTestsSkipped(1);
        t4.setTestsSkipped(4);
        t5.setTestsSkipped(0);

        t1.setTestsError(1);
        t2.setTestsError(0);
        t3.setTestsError(3);
        t4.setTestsError(2);
        t5.setTestsError(0);

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

        logs.add(new Log("AnalyseCompileTest", 1, l1, 0));
        logs.add(new Log("AnalyseCompileTest", 2, l2, 0));
        logs.add(new Log("AnalyseCompileTest", 3, l3, 0));
        logs.add(new Log("AnalyseCompileTest", 4, l4, 0));
        logs.add(new Log("AnalyseCompileTest", 5, l5, 0));
    }

    @Test
    public void increaseNumberOfRunTestBetweenTwoLogsTest(){
        int res = AnalyseTestsPhase.increaseNumberOfTestBetweenTwoLogs(logs.get(0), logs.get(1), "run", 0);
        assertEquals(4, res);
    }

    @Test
    public void increaseNumberOfFailedTestBetweenTwoLogsTest(){
        int res = AnalyseTestsPhase.increaseNumberOfTestBetweenTwoLogs(logs.get(0), logs.get(1), "failed", 0);
        assertEquals(1, res);
    }

    @Test
    public void increaseNumberOfSkippedTestBetweenTwoLogsTest(){
        int res = AnalyseTestsPhase.increaseNumberOfTestBetweenTwoLogs(logs.get(0), logs.get(1), "skipped", 0);
        assertEquals(1, res);
    }

    @Test
    public void increaseNumberOfErrorTestBetweenTwoLogsTest(){
        int res = AnalyseTestsPhase.increaseNumberOfTestBetweenTwoLogs(logs.get(0), logs.get(1), "error", 0);
        assertEquals(-1, res);
    }

    @Test
    public void increaseNumberOfRunTestInPercentBetweenTwoLogsTest(){
        float res = AnalyseTestsPhase.increaseNumberOfTestInPercentBetweenTwoLogs(logs.get(0), logs.get(1), "run", 0);
        assertEquals(0.3, res, 0.01);
    }

    @Test
    public void increaseNumberOfFailledTestInPercentBetweenTwoLogsTest(){
        float res = AnalyseTestsPhase.increaseNumberOfTestInPercentBetweenTwoLogs(logs.get(0), logs.get(1), "failed", 0);
        assertEquals(1, res, 0.01);
    }

    @Test
    public void increaseNumberOfSkippedTestInPercentBetweenTwoLogsTest(){
        float res = AnalyseTestsPhase.increaseNumberOfTestInPercentBetweenTwoLogs(logs.get(0), logs.get(1), "skipped", 0);
        assertEquals(1, res, 0.01);
    }

    @Test
    public void increaseNumberOfErrorTestInPercentBetweenTwoLogsTest(){
        float res = AnalyseTestsPhase.increaseNumberOfTestInPercentBetweenTwoLogs(logs.get(0), logs.get(1), "error", 0);
        assertEquals(-1, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfRunTestBetweenTest(){
        float res = AnalyseTestsPhase.increaseMeanNumberOfTestBetween(logs, 0, 4, "run", 0);
        assertEquals(3.5, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfFailedTestBetweenTest(){
        float res = AnalyseTestsPhase.increaseMeanNumberOfTestBetween(logs, 0, 4, "failed", 0);
        assertEquals(-0.25, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfSkippedTestBetweenTest(){
        float res = AnalyseTestsPhase.increaseMeanNumberOfTestBetween(logs, 0, 4, "skipped", 0);
        assertEquals(-0.25, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfErrorTestBetweenTest(){
        float res = AnalyseTestsPhase.increaseMeanNumberOfTestBetween(logs, 0, 4, "error", 0);
        assertEquals(-0.25, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfRunTestInPercentBetweenTest(){
        float res = AnalyseTestsPhase.increaseMeanNumberOfTestInPercentBetween(logs, 0, 4, "run", 0);
        assertEquals(0.3, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfFailedTestInPercentBetweenTest(){
        float res = AnalyseTestsPhase.increaseMeanNumberOfTestInPercentBetween(logs, 0, 4, "failed", 0);
        assertEquals(0.2, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfSkippedTestInPercentBetweenTest(){
        float res = AnalyseTestsPhase.increaseMeanNumberOfTestInPercentBetween(logs, 0, 4, "skipped", 0);
        assertEquals(0.62, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfErrorTestInPercentBetweenTest(){
        float res = AnalyseTestsPhase.increaseMeanNumberOfTestInPercentBetween(logs, 0, 4, "error", 0);
        assertEquals(-0.58, res, 0.01);
    }

    @Test
    public void meanRunTestTest(){
        float res = AnalyseTestsPhase.meanTest(logs, 0, 4, "run", 0);
        assertEquals(16.2, res, 0.01);
    }

    @Test
    public void meanFailedTestTest(){
        float res = AnalyseTestsPhase.meanTest(logs, 0, 4, "failed", 0);
        assertEquals(2, res, 0.01);
    }

    @Test
    public void meanSkippedTestTest(){
        float res = AnalyseTestsPhase.meanTest(logs, 0, 4, "skipped", 0);
        assertEquals(1.6, res, 0.01);
    }

    @Test
    public void meanErrorTestTest(){
        float res = AnalyseTestsPhase.meanTest(logs, 0, 4, "error", 0);
        assertEquals(1.2, res, 0.01);
    }

    @Test
    public void medianRunTestTest(){
        float res = AnalyseTestsPhase.medianTest(logs, 0, 4, "run", 0);
        assertEquals(13, res, 0.01);
    }

    @Test
    public void medianFailedTestTest(){
        float res = AnalyseTestsPhase.medianTest(logs, 0, 4, "failed", 0);
        assertEquals(2, res, 0.01);
    }

    @Test
    public void medianSkippedTestTest(){
        float res = AnalyseTestsPhase.medianTest(logs, 0, 4, "skipped", 0);
        assertEquals(1, res, 0.01);
    }

    @Test
    public void medianErrorTestTest(){
        float res = AnalyseTestsPhase.medianTest(logs, 0, 4, "error", 0);
        assertEquals(1, res, 0.01);
    }

    @Test
    public void firstQuartileRunTestTest(){
        float res = AnalyseTestsPhase.firstQuartileTest(logs, 0, 4, "run", 0);
        assertEquals(13, res, 0.01);
    }

    @Test
    public void firstQuartileFailedTestTest(){
        float res = AnalyseTestsPhase.firstQuartileTest(logs, 0, 4, "failed", 0);
        assertEquals(1.5, res, 0.01);
    }

    @Test
    public void firstQuartileSkippedTestTest(){
        float res = AnalyseTestsPhase.firstQuartileTest(logs, 0, 4, "skipped", 0);
        assertEquals(1, res, 0.01);
    }

    @Test
    public void firstQuartileErrorTestTest(){
        float res = AnalyseTestsPhase.firstQuartileTest(logs, 0, 4, "error", 0);
        assertEquals(0.5, res, 0.01);
    }

    @Test
    public void thirdQuartileRunTestTest(){
        float res = AnalyseTestsPhase.thirdQuartileTest(logs, 0, 4, "run", 0);
        assertEquals(22, res, 0.01);
    }

    @Test
    public void thirdQuartileFailedTestTest(){
        float res = AnalyseTestsPhase.thirdQuartileTest(logs, 0, 4, "failed", 0);
        assertEquals(3.5, res, 0.01);
    }

    @Test
    public void thirdQuartileSkippedTestTest(){
        float res = AnalyseTestsPhase.thirdQuartileTest(logs, 0, 4, "skipped", 0);
        assertEquals(3, res, 0.01);
    }

    @Test
    public void thirdQuartileErrorTestTest(){
        float res = AnalyseTestsPhase.thirdQuartileTest(logs, 0, 4, "error", 0);
        assertEquals(2.5, res, 0.01);
    }

}
