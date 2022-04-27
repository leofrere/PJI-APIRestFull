package com.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.StringReader;

import com.api.LogFile;

import org.junit.jupiter.api.Test;

public class PackageTest {
    
    @Test
    void creationOfPackageTest() {
        BufferedReader reader = new BufferedReader(new StringReader(LogFile.getLogFile()));
        new CompilePhase(reader);
        new TestPhase(reader);
        PackagePhase packagePhase = new PackagePhase(reader);
        assertEquals("ok", packagePhase.getStatus());
        assertEquals("/Users/leofrere/.jenkins/workspace/mavenGL/target/BankMaven-0.0.1-SNAPSHOT.jar", packagePhase.getJarPath());
        assertEquals("", packagePhase.getErrorsTrace());
        assertEquals("0,04s", packagePhase.getTime());
    }

}
