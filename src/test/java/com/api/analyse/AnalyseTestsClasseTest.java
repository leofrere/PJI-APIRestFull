package com.api.analyse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import com.api.model.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnalyseTestsClasseTest {
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

        List<TestClasse> lt1 = new LinkedList<TestClasse>();
        List<TestClasse> lt2 = new LinkedList<TestClasse>();
        List<TestClasse> lt3 = new LinkedList<TestClasse>();
        List<TestClasse> lt4 = new LinkedList<TestClasse>();
        List<TestClasse> lt5 = new LinkedList<TestClasse>();

        lt1.add(new TestClasse("package.Class", null, 13, 1, 1, 1));
        lt2.add(new TestClasse("package.Class", null, 17, 2, 2, 0));
        lt3.add(new TestClasse("package.Class", null, 11, 3, 1, 3));
        lt4.add(new TestClasse("package.Class", null, 13, 4, 4, 2));
        lt5.add(new TestClasse("package.Class", null, 27, 0, 0, 0));

        lt3.add(new TestClasse("package2.Class", null, 11, 3, 1, 3));
        lt4.add(new TestClasse("package2.Class", null, 13, 4, 4, 2));
        lt5.add(new TestClasse("package2.Class", null, 27, 0, 0, 0));

        t1.setTestsByClasse(lt1);
        t2.setTestsByClasse(lt2);
        t3.setTestsByClasse(lt3);
        t4.setTestsByClasse(lt4);
        t5.setTestsByClasse(lt5);

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

        logs.add(new Log("AnalyseCompileTest", 1, l1, 0, null));
        logs.add(new Log("AnalyseCompileTest", 2, l2, 0, null));
        logs.add(new Log("AnalyseCompileTest", 3, l3, 0, null));
        logs.add(new Log("AnalyseCompileTest", 4, l4, 0, null));
        logs.add(new Log("AnalyseCompileTest", 5, l5, 0, null));
    }

    @Test
    public void increaseNumberOfRunTestBetweenTwoLogsTest(){
        int res = AnalyseTestsClasse.increaseNumberOfTestBetweenTwoLogs(logs.get(0), logs.get(1), "run","package.Class",  0);
        assertEquals(4, res);
    }

    @Test
    public void increaseNumberOfFailedTestBetweenTwoLogsTest(){
        int res = AnalyseTestsClasse.increaseNumberOfTestBetweenTwoLogs(logs.get(0), logs.get(1), "failed", "package2.Class", 0);
        assertEquals(0, res);
    }

    @Test
    public void increaseNumberOfSkippedTestBetweenTwoLogsTest(){
        int res = AnalyseTestsClasse.increaseNumberOfTestBetweenTwoLogs(logs.get(0), logs.get(1), "skipped", "package.Class", 0);
        assertEquals(1, res);
    }

    @Test
    public void increaseNumberOfErrorTestBetweenTwoLogsTest(){
        int res = AnalyseTestsClasse.increaseNumberOfTestBetweenTwoLogs(logs.get(0), logs.get(1), "error", "package.Class", 0);
        assertEquals(-1, res);
    }

    @Test
    public void increaseNumberOfRunTestInPercentBetweenTwoLogsTest(){
        float res = AnalyseTestsClasse.increaseNumberOfTestInPercentBetweenTwoLogs(logs.get(0), logs.get(1), "run", "package.Class", 0);
        assertEquals(0.3, res, 0.01);
    }

    @Test
    public void increaseNumberOfFailledTestInPercentBetweenTwoLogsTest(){
        float res = AnalyseTestsClasse.increaseNumberOfTestInPercentBetweenTwoLogs(logs.get(0), logs.get(1), "failed", "package.Class", 0);
        assertEquals(1, res, 0.01);
    }

    @Test
    public void increaseNumberOfSkippedTestInPercentBetweenTwoLogsTest(){
        float res = AnalyseTestsClasse.increaseNumberOfTestInPercentBetweenTwoLogs(logs.get(0), logs.get(1), "skipped", "package.Class", 0);
        assertEquals(1, res, 0.01);
    }

    @Test
    public void increaseNumberOfErrorTestInPercentBetweenTwoLogsTest(){
        float res = AnalyseTestsClasse.increaseNumberOfTestInPercentBetweenTwoLogs(logs.get(0), logs.get(1), "error", "package.Class", 0);
        assertEquals(-1, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfRunTestBetweenTest(){
        float res = AnalyseTestsClasse.increaseMeanNumberOfTestBetween(logs, 0, 4, "run", "package.Class", 0);
        assertEquals(3.5, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfFailedTestBetweenTest(){
        float res = AnalyseTestsClasse.increaseMeanNumberOfTestBetween(logs, 0, 4, "failed", "package.Class", 0);
        assertEquals(-0.25, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfSkippedTestBetweenTest(){
        float res = AnalyseTestsClasse.increaseMeanNumberOfTestBetween(logs, 0, 4, "skipped", "package.Class", 0);
        assertEquals(-0.25, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfErrorTestBetweenTest(){
        float res = AnalyseTestsClasse.increaseMeanNumberOfTestBetween(logs, 0, 4, "error", "package.Class", 0);
        assertEquals(-0.25, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfRunTestInPercentBetweenTest(){
        float res = AnalyseTestsClasse.increaseMeanNumberOfTestInPercentBetween(logs, 0, 4, "run", "package.Class", 0);
        assertEquals(0.3, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfFailedTestInPercentBetweenTest(){
        float res = AnalyseTestsClasse.increaseMeanNumberOfTestInPercentBetween(logs, 0, 4, "failed", "package.Class", 0);
        assertEquals(0.2, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfSkippedTestInPercentBetweenTest(){
        float res = AnalyseTestsClasse.increaseMeanNumberOfTestInPercentBetween(logs, 0, 4, "skipped", "package.Class", 0);
        assertEquals(0.62, res, 0.01);
    }

    @Test
    public void increaseMeanNumberOfErrorTestInPercentBetweenTest(){
        float res = AnalyseTestsClasse.increaseMeanNumberOfTestInPercentBetween(logs, 0, 4, "error", "package.Class", 0);
        assertEquals(-0.58, res, 0.01);
    }

    @Test
    public void meanRunTestTest(){
        float res = AnalyseTestsClasse.meanTest(logs, 0, 4, "run", "package.Class", 0);
        assertEquals(16.2, res, 0.01);
    }

    @Test
    public void meanFailedTestTest(){
        float res = AnalyseTestsClasse.meanTest(logs, 0, 4, "failed", "package.Class", 0);
        assertEquals(2, res, 0.01);
    }

    @Test
    public void meanSkippedTestTest(){
        float res = AnalyseTestsClasse.meanTest(logs, 0, 4, "skipped", "package.Class", 0);
        assertEquals(1.6, res, 0.01);
    }

    @Test
    public void meanErrorTestTest(){
        float res = AnalyseTestsClasse.meanTest(logs, 0, 4, "error", "package2.Class", 0);
        assertEquals(1.66, res, 0.01);
    }

    @Test
    public void medianRunTestTest(){
        float res = AnalyseTestsClasse.medianTest(logs, 0, 4, "run", "package.Class", 0);
        assertEquals(13, res, 0.01);
    }

    @Test
    public void medianFailedTestTest(){
        float res = AnalyseTestsClasse.medianTest(logs, 0, 4, "failed", "package.Class", 0);
        assertEquals(2, res, 0.01);
    }

    @Test
    public void medianSkippedTestTest(){
        float res = AnalyseTestsClasse.medianTest(logs, 0, 4, "skipped", "package.Class", 0);
        assertEquals(1, res, 0.01);
    }

    @Test
    public void medianErrorTestTest(){
        float res = AnalyseTestsClasse.medianTest(logs, 0, 4, "error", "package2.Class", 0);
        assertEquals(2, res, 0.01);
    }

    @Test
    public void firstQuartileRunTestTest(){
        float res = AnalyseTestsClasse.firstQuartileTest(logs, 0, 4, "run", "package.Class", 0);
        assertEquals(13, res, 0.01);
    }

    @Test
    public void firstQuartileFailedTestTest(){
        float res = AnalyseTestsClasse.firstQuartileTest(logs, 0, 4, "failed", "package.Class", 0);
        assertEquals(1.5, res, 0.01);
    }

    @Test
    public void firstQuartileSkippedTestTest(){
        float res = AnalyseTestsClasse.firstQuartileTest(logs, 0, 4, "skipped", "package.Class", 0);
        assertEquals(1, res, 0.01);
    }

    @Test
    public void firstQuartileErrorTestTest(){
        float res = AnalyseTestsClasse.firstQuartileTest(logs, 0, 4, "error", "package2.Class", 0);
        assertEquals(1, res, 0.01);
    }

    @Test
    public void thirdQuartileRunTestTest(){
        float res = AnalyseTestsClasse.thirdQuartileTest(logs, 0, 4, "run", "package.Class", 0);
        assertEquals(22, res, 0.01);
    }

    @Test
    public void thirdQuartileFailedTestTest(){
        float res = AnalyseTestsClasse.thirdQuartileTest(logs, 0, 4, "failed", "package.Class", 0);
        assertEquals(3.5, res, 0.01);
    }

    @Test
    public void thirdQuartileSkippedTestTest(){
        float res = AnalyseTestsClasse.thirdQuartileTest(logs, 0, 4, "skipped", "package.Class", 0);
        assertEquals(3, res, 0.01);
    }

    @Test
    public void thirdQuartileErrorTestTest(){
        float res = AnalyseTestsClasse.thirdQuartileTest(logs, 0, 4, "error", "package2.Class", 0);
        assertEquals(1, res, 0.01);
    }
}
