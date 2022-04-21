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
public class PackageTest {
    
    @Test
    void creationOfPackageTest() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getClass().getResource("logfile.log").getFile()));
            new CompilePhase(reader);
            new TestPhase(reader);
            PackagePhase packagePhase = new PackagePhase(reader);
            assertEquals("ok", packagePhase.getStatus());
            assertEquals("/Users/leofrere/.jenkins/workspace/mavenGL/target/BankMaven-0.0.1-SNAPSHOT.jar", packagePhase.getJarPath());
            assertEquals("", packagePhase.getErrorsTrace());
            assertEquals("0,04s", packagePhase.getTime());
        } catch (FileNotFoundException e) {
            assertTrue(false);
        }
    }

}
