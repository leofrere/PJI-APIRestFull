package com.api.controller;

import java.util.HashMap;
import java.util.Map;

import com.api.utils.GraphQLRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evolution")
public class VariableController {

    @GetMapping("/phase/{projectName}/{phaseName}/{variable}")
    public Map<Integer, String> evolutionOfPhaseVariable(@PathVariable String projectName, @PathVariable String phaseName, @PathVariable String variable, @RequestParam(defaultValue = "0") int module) throws Exception{
        Map<Integer, String> map = new HashMap<Integer, String>();
        JSONObject data = new JSONObject(GraphQLRequest.evolutionOfPhaseVariable(projectName, phaseName+"Phase", variable));
        JSONArray tab = getLogs(data);
        for(int i = 0; i < tab.length(); i++){
            int build = tab.getJSONObject(i).getInt("build");
            JSONObject phase = getPhase(phaseName+"Phase", tab, i, module);
            String value = phase.get(variable).toString();
            map.put(build, value);
        }
        return map;
    }

    @GetMapping("/test/{projectName}/{classeName}/{variable}")
    public Map<Integer, String> evolutionOfTestClasseVariable(@PathVariable String projectName, @PathVariable String classeName, @PathVariable String variable, @RequestParam(defaultValue = "0") int module) throws Exception{
        Map<Integer, String> map = new HashMap<Integer, String>();
        JSONObject data = new JSONObject(GraphQLRequest.evolutionOfTestClasseVariable(projectName, variable));
        JSONArray tab = getLogs(data);
        for(int i = 0; i < tab.length(); i++){
            int build = tab.getJSONObject(i).getInt("build");
            JSONObject phase = getPhase("testPhase", tab, i, module);
            JSONArray tests = phase.getJSONArray("testsByClasse");
            for(int j = 0; j < tests.length(); j++){
                JSONObject test = tests.getJSONObject(j);
                if(test.get("classe").toString().equals(classeName.replace("-", "."))){
                    String value = test.get(variable).toString();
                    map.put(build, value);
                    break;
                }
            }
        }
        
        return map;
    }

    private JSONArray getLogs(JSONObject data) {
        JSONObject logs = new JSONObject(data.get("data").toString());
        JSONArray tab = logs.getJSONArray("logs");
        return tab;
    }

    private JSONObject getPhase(String phaseName, JSONArray tab, int i, int module) {
        JSONArray orders = tab.getJSONObject(i).getJSONArray("orders");
        JSONObject phase = orders.getJSONObject(module).getJSONObject(phaseName);
        return phase;
    }
    
}
