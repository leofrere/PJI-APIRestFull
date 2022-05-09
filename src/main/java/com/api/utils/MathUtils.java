package com.api.utils;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;

public class MathUtils {
    
    public static int minInt(JSONArray tab, String variable) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < tab.length(); i++) {
            int value = tab.getJSONObject(i).getInt(variable);
            if (value < min) {
                min = value;
            }
        }
        return min;            
    }

    public static float minFloat(JSONArray tab, String variable) {
        float min = Float.MAX_VALUE;

        for (int i = 0; i < tab.length(); i++) {
            float value = tab.getJSONObject(i).getFloat(variable);
            if (value < min) {
                min = value;
            }
        }
        return min;            
    }

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
    public static Map<String,Float>[] regressionFloat(JSONArray tab, String variable) {
        Map<String,Float>[] map = new HashMap[tab.length()];
        float b0 = minFloat(tab, variable);
        int build;;
        float value,sum = 0;
        for (int i = 0; i < tab.length(); i++) {
            build = tab.getJSONObject(i).getInt("build");
            value = tab.getJSONObject(i).getFloat(variable);
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

    @SuppressWarnings("unchecked")
    public static Map<String,Float>[] coefMul(JSONArray tab, String variable) {
        Map<String,Float>[] map = new HashMap[tab.length()];
        int build;;
        float coef;

        for (int i = 1; i < tab.length(); i++) {
            map[i] = new HashMap<String,Float>();
            build = tab.getJSONObject(i).getInt("build");
            coef = tab.getJSONObject(i-1).getFloat(variable) / tab.getJSONObject(i).getFloat(variable);
            map[i].put("build", (float) build);
            map[i].put(variable, coef);
        }
        return map;  
    }

    @SuppressWarnings("unchecked")
    public static Map<String,Float>[] indice(JSONArray tab, String variable) {
        Map<String,Float>[] map = new HashMap[tab.length()];
        int build;;
        float ref = tab.getJSONObject(0).getFloat(variable);
        float indice;

        for (int i = 1; i < tab.length(); i++) {
            map[i] = new HashMap<String,Float>();
            build = tab.getJSONObject(i).getInt("build");
            indice = ref / tab.getJSONObject(i).getFloat(variable);
            map[i].put("build", (float) build);
            map[i].put(variable, indice);
        }
        return map;    
    }



}
