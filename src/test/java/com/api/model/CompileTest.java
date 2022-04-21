package com.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.api.PJIApplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PJIApplication.class)
public class CompileTest {
    
    @Test
    void creationOfCompileTest() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getClass().getResource("logfile.log").getFile()));
            CompilePhase compile = new CompilePhase(reader);
            assertEquals("ok", compile.getStatus());
            assertEquals(14, compile.getNumberOfClasses());
            assertEquals("", compile.getErrorsTrace());
            assertEquals("2,46s", compile.getTime());
        } catch (FileNotFoundException e) {
            assertTrue(false);
        }
    }

    @Test
    void creationOfCompileWithErrorInLogTest() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getClass().getResource("logfileWithCompileError.log").getFile()));
            CompilePhase compile = new CompilePhase(reader);
            assertEquals("ko", compile.getStatus());
            assertEquals(14, compile.getNumberOfClasses());
            assertEquals("11:26:35,105 [ERROR] /Users/leofrere/.jenkins/workspace/mavenGL/src/main/java/bank/CompteEpargne.java:[11,17] cannot find symbol", compile.getErrorsTrace());
            assertEquals(null, compile.getTime());
        } catch (FileNotFoundException e) {
            assertTrue(false);
        }
    }

}
