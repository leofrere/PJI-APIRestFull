package com.api.analyse;

import java.util.List;

import com.api.model.*;
import com.api.model.interfaces.Phase;

public class AnalyseTime extends Analyse {
    
    /**
     * @param log1 
     * @param log2
     * @param phaseName nom de la phase ciblé (compile, test, package)
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @return 0 si un des deux log a un time null, sinon la différence de temps entre les deux logs
     */
    public static float increaseTimeBetweenTwoLogs(Log log1, Log log2, String phaseName, int moduleNumber) {
        Phase phase1 = log1.getOrders().get(moduleNumber).getCompilePhase();
        Phase phase2 = log2.getOrders().get(moduleNumber).getCompilePhase();
        
        if(phase1.getTime() == null || phase2.getTime() == null) {
            return 0;
        }

        float time1 = Float.parseFloat(phase1.getTime().replace(",", ".").replace("s", ""));
        float time2 = Float.parseFloat(phase2.getTime().replace(",", ".").replace("s", ""));
        
        return time2 - time1;
    }

    /**
     * @param logs
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param phaseName nom de la phase ciblé (compile, test, package)
     * @return la moyenne des diffénreces entre deux builds consécutif du log n1 au log n2
     */
    public static float increaseMeanTimeBetween(List<Log> logs, int n1, int n2, String phaseName, int moduleNumber){
        float sumTime = 0;
        int cpt = 0;
        for(int i = n1+1; i <= n2; i++){
            float time = increaseTimeBetweenTwoLogs(logs.get(i-1), logs.get(i), phaseName, moduleNumber);
            if(time != 0) {
                sumTime += time;
                cpt++;
            }
        }
        return sumTime / cpt;
    }

    /**
     * @param logs
     * @param phaseName nom de la phase ciblé (compile, test, package)
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @return 0 si un des deux log a un time null, sinon la différence de temps entre les deux logs en pourcentage
     */
    public static float increaseTimeBetweenTwoLogsInPercent(Log log1, Log log2, String phaseName, int moduleNumber) {
        Phase phase1 = log1.getOrders().get(moduleNumber).getCompilePhase();
        Phase phase2 = log2.getOrders().get(moduleNumber).getCompilePhase();
        
        if(phase1.getTime() == null || phase2.getTime() == null) {
            return 0;
        }

        float time1 = Float.parseFloat(phase1.getTime().replace(",", ".").replace("s", ""));
        float time2 = Float.parseFloat(phase2.getTime().replace(",", ".").replace("s", ""));
        
        return (time2 - time1) / time1;
    }

    /**
     * @param logs
     * @param phaseName nom de la phase ciblé (compile, test, package)
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @return la moyenne des diffénrences entre deux builds consécutif du log n1 au log n2 en pourcentage
     */
    public static float increaseMeanTimeBetweenInPercent(List<Log> logs, int n1, int n2, String phaseName, int moduleNumber){
        float sumPercent = 0;
        int cpt = 0;
        for(int i = n1+1; i <= n2; i++){
            float percent = increaseTimeBetweenTwoLogsInPercent(logs.get(i-1), logs.get(i), phaseName, moduleNumber);
            if(percent != 0) {
                sumPercent += percent;
                cpt++;
            }
        }
        return sumPercent / cpt;
    }

    /**
     * @param logs
     * @param phaseName nom de la phase ciblé (compile, test, package)
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @return moyenne du temps pour une phase ciblé
     */
    public static float meanTimeBetween(List<Log> logs, int n1, int n2, String phaseName, int moduleNumber){
        float sumTime = 0;
        int cpt = 0;
        for(int i = n1+1; i <= n2; i++){
            Phase phase = logs.get(i).getOrders().get(moduleNumber).getCompilePhase();
            String time = phase.getTime();
            if(time != null) {
                sumTime += Float.parseFloat(time.replace(",", ".").replace("s", ""));
                cpt++;
            }
        }
        return sumTime / cpt;
    }

}
