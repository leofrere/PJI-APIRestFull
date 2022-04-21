package com.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.api.PJIApplication;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PJIApplication.class)
public class TestPhaseTest {
    
    @Test
    void creationOfTestTest() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getClass().getResource("logfile.log").getFile()));
            new CompilePhase(reader);
            TestPhase test = new TestPhase(reader);
            assertEquals("ok", test.getStatus());
            assertEquals(3, test.getNumberOfTestClasses());
            assertEquals("", test.getErrorsTrace());
            assertEquals("2,45s", test.getTime());
            assertEquals(26, test.getTestsRun());
            assertEquals(0, test.getTestsFailed());
            assertEquals(0, test.getTestsError());
            assertEquals(0, test.getTestsSkipped());
        } catch (FileNotFoundException e) {
            assertTrue(false);
        }
    }

    @Test
    void creationOfTestPhaseWithErrorInLogTest() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getClass().getResource("logfileWithTestError.log").getFile()));
            new CompilePhase(reader);
            TestPhase test = new TestPhase(reader);
            assertEquals("ko", test.getStatus());
            assertEquals(3, test.getNumberOfTestClasses());
            assertEquals("09:10:12,231 [ERROR] /Users/leofrere/.jenkins/workspace/mavenGL/src/test/java/bank/CompteTest.java:[151,31] cannot find symbol", test.getErrorsTrace());
            assertEquals(null, test.getTime());
            assertEquals(0, test.getTestsRun());
            assertEquals(0, test.getTestsFailed());
            assertEquals(0, test.getTestsError());
            assertEquals(0, test.getTestsSkipped());
        } catch (FileNotFoundException e) {
            assertTrue(false);
        }
    }

}
