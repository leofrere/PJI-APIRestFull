package com.api.analyse;

import java.util.LinkedList;
import java.util.List;

import com.api.analyse.abstracts.Analyse;
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
        Phase phase1 = getPhase(phaseName, log1.getOrders().get(moduleNumber));
        Phase phase2 = getPhase(phaseName, log2.getOrders().get(moduleNumber));
        
        if(phase1.getTime() == null || phase2.getTime() == null) {
            return 0;
        }

        float time1 = phase1.getTimeFloat();
        float time2 = phase2.getTimeFloat();
        
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
        Phase phase1 = getPhase(phaseName, log1.getOrders().get(moduleNumber));
        Phase phase2 = getPhase(phaseName, log2.getOrders().get(moduleNumber));
        
        if(phase1.getTime() == null || phase2.getTime() == null) {
            return 0;
        }

        float time1 = phase1.getTimeFloat();
        float time2 = phase2.getTimeFloat();
        
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
        for(int i = n1; i <= n2; i++){
            Phase phase = getPhase(phaseName, logs.get(i).getOrders().get(moduleNumber));
            String time = phase.getTime();
            if(time != null) {
                sumTime += phase.getTimeFloat();
                cpt++;
            }
        }
        return sumTime / cpt;
    }

    /**
     * @param logs
     * @param phaseName nom de la phase ciblé (compile, test, package)
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @return temps médian pour une phase ciblé
     */
    public static float medianTimeBetween(List<Log> logs, int n1, int n2, String phaseName, int moduleNumber){

        List<Log> logsWithTime = new LinkedList<Log>();
        for(int i = n1; i <= n2; i++){
            Phase phase = getPhase(phaseName, logs.get(i).getOrders().get(moduleNumber));
            String time = phase.getTime();
            if(time != null) {
                logsWithTime.add(logs.get(i));
            }
        }

        logsWithTime.sort((Log log1, Log log2) -> {
            Phase phase1 = getPhase(phaseName, log1.getOrders().get(moduleNumber));
            Phase phase2 = getPhase(phaseName, log2.getOrders().get(moduleNumber));
            if (phase1.getTimeFloat() > phase2.getTimeFloat()) {
                return 1;
            } else if (phase1.getTimeFloat() < phase2.getTimeFloat()) {
                return -1;
            } else {
                return 0;
            }
        });
        
        if(logsWithTime.size() % 2 == 0) {
            Log log = logsWithTime.get(logsWithTime.size() / 2);
            Phase phase = getPhase(phaseName, log.getOrders().get(moduleNumber));
            return phase.getTimeFloat();
        } else {
            Log log1 = logsWithTime.get(logsWithTime.size() / 2);
            Log log2 = logsWithTime.get(logsWithTime.size() / 2 + 1);
            Phase phase1 = getPhase(phaseName, log1.getOrders().get(moduleNumber));
            Phase phase2 = getPhase(phaseName, log2.getOrders().get(moduleNumber));
            return (phase1.getTimeFloat() + phase2.getTimeFloat()) / 2;
        }
        

    }

}
