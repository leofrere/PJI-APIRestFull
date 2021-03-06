package com.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.StringReader;

import com.api.LogFile;

public class TestPhaseTest {
    
    @Test
    void creationOfTestTest() {
        BufferedReader reader = new BufferedReader(new StringReader(LogFile.getLogFile()));
        new CompilePhase(reader, true);
        TestPhase test = new TestPhase(reader, true);
        assertEquals("ok", test.getStatus());
        assertEquals(3, test.getCompiledClasses());
        assertEquals("", test.getErrorsTrace());
        assertEquals("2,45s", test.getTime());
        assertEquals(26, test.getTestsRun());
        assertEquals(0, test.getTestsFailed());
        assertEquals(0, test.getTestsError());
        assertEquals(0, test.getTestsSkipped());
    }

    @Test
    void creationOfTestPhaseWithErrorInLogTest() {
        BufferedReader reader = new BufferedReader(new StringReader(LogFile.getLogfileWithTestError()));
        new CompilePhase(reader, true);
        TestPhase test = new TestPhase(reader, true);
        assertEquals("finished", test.getStatus());
        assertEquals(3, test.getCompiledClasses());
        assertEquals("", test.getErrorsTrace());
        assertEquals("0,81s", test.getTime());
        assertEquals(0, test.getTestsRun());
        assertEquals(0, test.getTestsFailed());
        assertEquals(0, test.getTestsError());
        assertEquals(0, test.getTestsSkipped());
    }

    @Test
    void creationOfTestWithAllTest() {
        BufferedReader reader = new BufferedReader(new StringReader(LogFile.getLogFileWithDifferentResultForTest()));
        new CompilePhase(reader, true);
        TestPhase test = new TestPhase(reader, true);
        assertEquals("ok", test.getStatus());
        assertEquals(3, test.getCompiledClasses());
        assertEquals("", test.getErrorsTrace());
        assertEquals("2,45s", test.getTime());
        assertEquals(15, test.getTestsRun());
        assertEquals(2, test.getTestsFailed());
        assertEquals(3, test.getTestsError());
        assertEquals(6, test.getTestsSkipped());
    }

    @Test
    void creationOfTestWithFailedTest() {
        BufferedReader reader = new BufferedReader(new StringReader(LogFile.getLogFileWithAllTestFailed()));
        new CompilePhase(reader, true);
        TestPhase test = new TestPhase(reader, true);
        assertEquals("ok", test.getStatus());
        assertEquals(3, test.getCompiledClasses());
        assertEquals("", test.getErrorsTrace());
        assertEquals("2,45s", test.getTime());
        assertEquals(0, test.getTestsRun());
        assertEquals(26, test.getTestsFailed());
        assertEquals(0, test.getTestsError());
        assertEquals(0, test.getTestsSkipped());
    }

    @Test
    void creationOfTestWithErrorTest() {
        BufferedReader reader = new BufferedReader(new StringReader(LogFile.getLogFileWithAllTestError()));
        new CompilePhase(reader, true);
        TestPhase test = new TestPhase(reader, true);
        assertEquals("ok", test.getStatus());
        assertEquals(3, test.getCompiledClasses());
        assertEquals("", test.getErrorsTrace());
        assertEquals("2,45s", test.getTime());
        assertEquals(0, test.getTestsRun());
        assertEquals(0, test.getTestsFailed());
        assertEquals(26, test.getTestsError());
        assertEquals(0, test.getTestsSkipped());
    }

    @Test
    void creationOfTestWithSkippedTest() {
        BufferedReader reader = new BufferedReader(new StringReader(LogFile.getLogFileWithAllTestSkipped()));
        new CompilePhase(reader, true);
        TestPhase test = new TestPhase(reader, true);
        assertEquals("ok", test.getStatus());
        assertEquals(3, test.getCompiledClasses());
        assertEquals("", test.getErrorsTrace());
        assertEquals("2,45s", test.getTime());
        assertEquals(0, test.getTestsRun());
        assertEquals(0, test.getTestsFailed());
        assertEquals(0, test.getTestsError());
        assertEquals(26, test.getTestsSkipped());
    }

}
