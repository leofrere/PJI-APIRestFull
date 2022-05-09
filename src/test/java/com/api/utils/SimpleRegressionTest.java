package com.api.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

public class SimpleRegressionTest {
    
    @Test
    public void minIntTest(){
        JSONArray tab = new JSONArray("[{\"build\":1,\"value\":7},{\"build\":2,\"value\":4},{\"build\":3,\"value\":3},{\"build\":4,\"value\":2},{\"build\":5,\"value\":1},{\"build\":6,\"value\":13}]");
        int res = MathUtils.minInt(tab, "value");
        assertEquals(1, res);
    }

    @Test
    public void minFloatTest(){
        JSONArray tab = new JSONArray("[{\"build\":1,\"value\":7.6},{\"build\":2,\"value\":4.1},{\"build\":3,\"value\":3.39},{\"build\":4,\"value\":2.86},{\"build\":5,\"value\":1.7},{\"build\":6,\"value\":13.13}]");
        float res = MathUtils.minFloat(tab, "value");
        assertEquals(1.7, res, 0.01);
    }

    @Test
    public void regressionIntTest(){
        JSONArray tab = new JSONArray("[{\"build\":1,\"value\":7},{\"build\":2,\"value\":4},{\"build\":3,\"value\":3},{\"build\":4,\"value\":2},{\"build\":5,\"value\":1},{\"build\":6,\"value\":13}]");
        Map<String,Float>[] res = MathUtils.regressionInt(tab, "value");
        assertEquals(6, res.length);
        assertEquals(2.73, res[0].get("value").floatValue(), 0.01);
        assertEquals(4.47, res[1].get("value").floatValue(), 0.01);
        assertEquals(6.20, res[2].get("value").floatValue(), 0.01);
        assertEquals(7.94, res[3].get("value").floatValue(), 0.01);
        assertEquals(9.68, res[4].get("value").floatValue(), 0.01);
        assertEquals(11.41, res[5].get("value").floatValue(), 0.01);
    }

    @Test
    public void regressionFloatTest(){
        JSONArray tab = new JSONArray("[{\"build\":1,\"value\":7.6},{\"build\":2,\"value\":4.1},{\"build\":3,\"value\":3.39},{\"build\":4,\"value\":2.86},{\"build\":5,\"value\":1.7},{\"build\":6,\"value\":13.13}]");
        Map<String,Float>[] res = MathUtils.regressionFloat(tab, "value");
        assertEquals(6, res.length);
        assertEquals(3.34, res[0].get("value").floatValue(), 0.01);
        assertEquals(4.98, res[1].get("value").floatValue(), 0.01);
        assertEquals(6.62, res[2].get("value").floatValue(), 0.01);
        assertEquals(8.27, res[3].get("value").floatValue(), 0.01);
        assertEquals(9.91, res[4].get("value").floatValue(), 0.01);
        assertEquals(11.55, res[5].get("value").floatValue(), 0.01);
    }

}
