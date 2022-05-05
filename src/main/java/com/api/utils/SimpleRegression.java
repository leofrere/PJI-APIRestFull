package com.api.utils;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;

public class SimpleRegression {
    
    public static int minInt(JSONArray tab, String variable) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < tab.length(); i++) {
            int value = tab.getJSONObject(i).getInt(variable);
            if (value < min) {
                min = tab.getJSONObject(i).getInt("build");
            }
        }
        return min;            
    }

    public static float minFloat(JSONArray tab, String variable) {
        float min = Float.MAX_VALUE;

        for (int i = 0; i < tab.length(); i++) {
            int value = tab.getJSONObject(i).getInt(variable);
            if (value < min) {
                min = tab.getJSONObject(i).getInt("build");
            }
        }
        return min;            
    }

    public static Map<String,Float>[] regressionInt(JSONArray tab, String variable) {
        Map<String,Float>[] map = new HashMap[tab.length()];
        int b0 = minInt(tab, variable);
        int build, value;
        float sum = 0;

        for (int i = 0; i < tab.length(); i++) {
            build = tab.getJSONObject(i).getInt("build");
            value = tab.getJSONObject(i).getInt(variable);
            sum += (value - b0) / (float) build;
        }

        float b1 =  sum / (float) tab.length();

        for (int i = 0; i < tab.length(); i++) {
            map[i] = new HashMap<String,Float>();
            build = tab.getJSONObject(i).getInt("build");
            map[i].put("build", (float) build);
            value = tab.getJSONObject(i).getInt(variable);
            map[i].put("value", b0 + b1 * build);
        }
        return map;
        
    }

    public static Map<String,Float>[] regressionFloat(JSONArray tab, String variable) {
        Map<String,Float>[] map = new HashMap[tab.length()];
        float b0 = minFloat(tab, variable);
        int build;;
        float value,sum = 0;

        for (int i = 0; i < tab.length(); i++) {
            build = tab.getJSONObject(i).getInt("build");
            value = tab.getJSONObject(i).getInt(variable);
            sum += (value - b0) / (float) build;
        }

        float b1 =  sum / (float) tab.length();

        for (int i = 0; i < tab.length(); i++) {
            map[i] = new HashMap<String,Float>();
            build = tab.getJSONObject(i).getInt("build");
            map[i].put("build", (float) build);
            value = tab.getJSONObject(i).getInt(variable);
            map[i].put(variable, b0 + b1 * build);
        }
        return map;
        
    }



}
