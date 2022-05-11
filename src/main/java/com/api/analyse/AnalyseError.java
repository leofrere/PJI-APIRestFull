package com.api.analyse;

import java.util.List;
import java.util.Map;

import com.api.analyse.abstracts.Analyse;
import com.api.model.Log;

public class AnalyseError extends Analyse {
    
    /**
     * @param logs
     * @param n1
     * @param n2
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @return la proportion de build avec une erreur
     */
    public static float proportionOfBuildError(List<Log> logs, int n1, int n2, int moduleNumber) {
        int nbError = 0;
        for(int i = n1; i <= n2; i++){
            if(logs.get(i).getOrders().get(moduleNumber).getCompilePhase().getErrorsTrace().length() > 0){
                nbError++;
                continue;
            }

            if(logs.get(i).getOrders().get(moduleNumber).getTestPhase().getErrorsTrace().length() > 0){
                nbError++;
                continue;
            }

            if(logs.get(i).getOrders().get(moduleNumber).getPackagePhase().getErrorsTrace().length() > 0){
                nbError++;
            }

        }
        return nbError / (float) (n2 - n1 + 1);
    }

    /**
     * @param logs
     * @param n1
     * @param n2
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @return un dictionnaire contenant pour chaque type d'erreur le nombre d'apparition entre les logs n1 et n2
     */
    public static Map<String, Integer> numberOfErrorPerType(List<Log> logs, int n1, int n2, int moduleNumber) {
        Map<String, Integer> map = new java.util.HashMap<String, Integer>();
        for(int i = n1; i <= n2; i++){
            if(logs.get(i).getOrders().get(moduleNumber).getCompilePhase().getErrorsTrace().length() > 0){
                String error = logs.get(i).getOrders().get(moduleNumber).getCompilePhase().getErrorsTrace().split("] ")[2];
                if(map.containsKey(error)){
                    map.put(error, map.get(error) + 1);
                }else{
                    map.put(error, 1);
                }
                continue;
            }

            if(logs.get(i).getOrders().get(moduleNumber).getTestPhase().getErrorsTrace().length() > 0){
                String error = "";
                String[] strings = logs.get(i).getOrders().get(moduleNumber).getTestPhase().getErrorsTrace().split("] ");
                if(strings.length > 2){
                    error = strings[2];
                }else{
                    error = strings[1];
                }
                if(map.containsKey(error)){
                    map.put(error, map.get(error) + 1);
                }else{
                    map.put(error, 1);
                }
                continue;
            }

            if(logs.get(i).getOrders().get(moduleNumber).getPackagePhase().getErrorsTrace().length() > 0){
                String error = logs.get(i).getOrders().get(moduleNumber).getPackagePhase().getErrorsTrace().split("] ")[2];
                if(map.containsKey(error)){
                    map.put(error, map.get(error) + 1);
                }else{
                    map.put(error, 1);
                }
            }

        }
        return map;
    }

}
