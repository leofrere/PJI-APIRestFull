package com.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.StringReader;

import com.api.LogFile;

import org.junit.jupiter.api.Test;

public class CompileTest {
    
    @Test
    void creationOfCompileTest() {
        BufferedReader reader = new BufferedReader(new StringReader(LogFile.getLogFile()));
        CompilePhase compile = new CompilePhase(reader);
        assertEquals("ok", compile.getStatus());
        assertEquals(14, compile.getCompiledClasses());
        assertEquals("", compile.getErrorsTrace());
        assertEquals("2,46s", compile.getTime());
    }

    @Test
    void creationOfCompileWithErrorInLogTest() {
        BufferedReader reader = new BufferedReader(new StringReader(LogFile.getLogfileWithCompileError()));
        CompilePhase compile = new CompilePhase(reader);
        assertEquals("finished", compile.getStatus());
        assertEquals(14, compile.getCompiledClasses());
        assertEquals("", compile.getErrorsTrace());
        assertEquals("0s", compile.getTime());
    }

}
