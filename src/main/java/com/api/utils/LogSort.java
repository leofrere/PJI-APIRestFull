package com.api.utils;

import java.util.List;

import com.api.model.Log;

public class LogSort {
    
    public static void sort(List<Log> logs) {
        logs.sort((Log log1, Log log2) -> {
            if (log1.getBuild() > log2.getBuild()) {
                return 1;
            } else if (log1.getBuild() < log2.getBuild()) {
                return -1;
            } else {
                return 0;
            }
        });
    }

}
