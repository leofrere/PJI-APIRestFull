package com.api.utils;

import java.util.List;

import com.api.model.Order;

public class LogSort {
    
    public static void sort(List<Order> logs) {
        logs.sort((Order log1, Order log2) -> {
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
