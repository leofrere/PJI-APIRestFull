package com.api.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

public class SimpleRegressionTest {
    
    @Test
    public void minIntTest(){
        JSONArray tab = new JSONArray("[{\"build\":1,\"value\":7},{\"build\":2,\"value\":4},{\"build\":3,\"value\":3},{\"build\":4,\"value\":2},{\"build\":5,\"value\":1},{\"build\":6,\"value\":13}]");
        int res = SimpleRegression.minInt(tab, "value");
        assertEquals(1, res);
    }

    @Test
    public void minFloatTest(){
        JSONArray tab = new JSONArray("[{\"build\":1,\"value\":7.6},{\"build\":2,\"value\":4.1},{\"build\":3,\"value\":3.39},{\"build\":4,\"value\":2.86},{\"build\":5,\"value\":1.7},{\"build\":6,\"value\":13.13}]");
        float res = SimpleRegression.minFloat(tab, "value");
        assertEquals(1.7, res, 0.01);
    }

}
