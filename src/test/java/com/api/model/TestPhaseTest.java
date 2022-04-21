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
            CompilePhase compile = new CompilePhase(reader);
            TestPhase test = new TestPhase(reader);
            assertEquals("ok", test.getStatus());
            assertEquals(3, test.getNumberOfTestClasses());
            assertEquals("", test.getErrorsTrace());
            assertEquals("2,46s", compile.getTime());
            assertEquals(26, test.getTestsRun());
            assertEquals(0, test.getTestsFailed());
            assertEquals(0, test.getTestsError());
            assertEquals(0, test.getTestsSkipped());
        } catch (FileNotFoundException e) {
            assertTrue(false);
        }
    }

}
