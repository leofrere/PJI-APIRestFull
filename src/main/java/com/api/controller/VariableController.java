package com.api.controller;

import java.util.HashMap;
import java.util.Map;

import com.api.utils.GraphQLRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evolution")
public class VariableController {

    @GetMapping("/{projectName}/{phase}/{variable}")
    public Map<Integer, String> evolutionOfPhaseVariable(@PathVariable String projectName, @PathVariable String phase, @PathVariable String variable) throws Exception{
        Map<Integer, String> map = new HashMap<Integer, String>();

        JSONObject data = new JSONObject(GraphQLRequest.evolutionOfPhaseVariable(projectName, phase, variable));
        JSONObject logs = new JSONObject(data.get("data").toString());
        JSONArray tab = logs.getJSONArray("logs");
        for(int i = 0; i < tab.length(); i++){
            int build = tab.getJSONObject(i).getInt("build");
            JSONArray orders = tab.getJSONObject(i).getJSONArray("orders");
            JSONObject compilePhase = orders.getJSONObject(0).getJSONObject(phase);
            String value = compilePhase.get(variable).toString();
            map.put(build, value);
        }
        
        return map;
    }
    
}
