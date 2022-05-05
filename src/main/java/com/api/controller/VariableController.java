package com.api.controller;

import java.util.HashMap;
import java.util.Map;

import com.api.utils.GraphQLRequest;
import com.api.utils.SimpleRegression;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/evolution")
public class VariableController {

    @CrossOrigin
    @GetMapping("/regression/{projectName}/{module}/{phase}/time")
    public Map<String,Float>[] regressionTime(@PathVariable String projectName, @PathVariable String module, @PathVariable String phase) throws JSONException, Exception {
        JSONObject data = new JSONObject(GraphQLRequest.sendRequest("{\"query\":\"{"+ phase +"ByProject(project:\\\""+ projectName +"\\\",module:\\\""+ module +"\\\"){build,timeFloat}}\"}"));
        JSONObject logs = new JSONObject(data.get("data").toString());
        JSONArray tab = logs.getJSONArray(phase+"PhasesByProject");
        return SimpleRegression.regressionFloat(tab, "timeFloat");
    }

    @CrossOrigin
    @GetMapping("/regression/{projectName}/{module}/{phase}/tests/{type}")
    public Map<String,Float>[] regressionTimeByTest(@PathVariable String projectName, @PathVariable String module, @PathVariable String phase, @PathVariable String type) throws JSONException, Exception {
        JSONObject data = new JSONObject(GraphQLRequest.sendRequest("{\"query\":\"{"+phase+"TimeByTest(project: \\\""+ projectName +"\\\",module:\\\""+module+"\\\", test: \\\""+type+"\\\")}\"}"));
        JSONObject logs = new JSONObject(data.get("data").toString());
        JSONArray tab = logs.getJSONArray(phase+"TimeByTest");
        return SimpleRegression.regressionFloat(tab, "time");
    }

    @CrossOrigin
    @GetMapping("/regression/{projectName}/{module}/{phase}/compile")
    public Map<String,Float>[] regressionTimeByCompiledClass(@PathVariable String projectName, @PathVariable String module, @PathVariable String phase) throws JSONException, Exception {
        JSONObject data = new JSONObject(GraphQLRequest.sendRequest("{\"query\":\"{"+phase+"PhasesTimeByCompiledClass(project: \\\""+projectName+"\\\",module:\\\""+module+"\\\")}\"}"));
        JSONObject logs = new JSONObject(data.get("data").toString());
        JSONArray tab = logs.getJSONArray(phase+"PhasesTimeByCompiledClass");
        return SimpleRegression.regressionFloat(tab, "time");
    }

    @CrossOrigin
    @GetMapping("/regression/{projectName}/{module}/{phase}/{variable}")
    public Map<String,Float>[] regressionInt(@PathVariable String projectName, @PathVariable String module, @PathVariable String phase, @PathVariable String variable) throws JSONException, Exception {
        JSONObject data = new JSONObject(GraphQLRequest.sendRequest("{\"query\":\"{"+ phase +"ByProject(project:\\\""+ projectName +"\\\",module:\\\""+ module +"\\\"){build,"+ variable +"}}\"}"));
        JSONObject logs = new JSONObject(data.get("data").toString());
        JSONArray tab = logs.getJSONArray(phase+"PhasesByProject");
        return SimpleRegression.regressionInt(tab, variable);
    }


    @CrossOrigin
    @GetMapping("/phase/{projectName}/{phaseName}/{variable}")
    public Map<Integer, String> evolutionOfPhaseVariable(@PathVariable String projectName, @PathVariable String phaseName, @PathVariable String variable, @RequestParam(defaultValue = "0") int module) throws Exception{
        Map<Integer, String> map = new HashMap<Integer, String>();
        JSONObject data = new JSONObject(GraphQLRequest.sendRequest("{\"query\":\"{logsByProject(project:\\\""+ projectName +"\\\"){build,orders{"+ phaseName +"Phase{"+ variable +"}}}}\\n\"}"));
        JSONArray tab = getLogs(data);
        for(int i = 0; i < tab.length(); i++){
            int build = tab.getJSONObject(i).getInt("build");
            JSONObject phase = getPhase(phaseName+"Phase", tab, i, module);
            String value = phase.get(variable).toString();
            map.put(build, value);
        }
        return map;
    }

    @CrossOrigin
    @GetMapping("/test/{projectName}/{classeName}/{variable}")
    public Map<Integer, String> evolutionOfTestClasseVariable(@PathVariable String projectName, @PathVariable String classeName, @PathVariable String variable, @RequestParam(defaultValue = "0") int module) throws Exception{
        Map<Integer, String> map = new HashMap<Integer, String>();
        JSONObject data = new JSONObject(GraphQLRequest.sendRequest("{\"query\":\"{logsByProject(project:\\\""+ projectName +"\\\"){build,orders{testPhase{testsByClasse{classe,"+ variable +"}}}}}\\n\"}"));
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
        JSONArray tab = logs.getJSONArray("logsByProject");
        return tab;
    }

    private JSONObject getPhase(String phaseName, JSONArray tab, int i, int module) {
        JSONArray orders = tab.getJSONObject(i).getJSONArray("orders");
        JSONObject phase = orders.getJSONObject(module).getJSONObject(phaseName);
        return phase;
    }
    
}
