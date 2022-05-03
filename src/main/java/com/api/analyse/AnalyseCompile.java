package com.api.analyse;

import java.util.LinkedList;
import java.util.List;

import com.api.analyse.abstracts.Analyse;
import com.api.model.Log;
import com.api.model.Order;
import com.api.model.abstracts.Compile;

public class AnalyseCompile extends Analyse {

    public static Compile getCompile(String phaseName, Order order) {
        switch(phaseName) {
            case "compile":
                return order.getCompilePhase();
            default:
                return order.getTestPhase();
        }
    }

    /**
     * @param log1
     * @param log2
     * @param phaseName nom de la phase ciblé (compile, test)
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @return la différence de nombre de classes compilé entre les deux logs
     */
    public static int increaseNumberOfCompileClassBetweenTwoLogs(Log log1, Log log2, String phaseName,int moduleNumber) {
        Compile compile1 = getCompile(phaseName, log1.getOrders().get(moduleNumber));
        Compile compile2 = getCompile(phaseName, log2.getOrders().get(moduleNumber));
        return compile2.getCompiledClasses() - compile1.getCompiledClasses();
    }

    /**
     * @param logs
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param phaseName nom de la phase ciblé (compile, test)
     * @return la différence de nombre de classes compilé entre les deux logs en pourcentage
     */
    public static float increaseNumberOfCompileClassInPercentBetweenTwoLogs(Log log1, Log log2, String phaseName, int moduleNumber) {
        Compile compile1 = getCompile(phaseName, log1.getOrders().get(moduleNumber));
        Compile compile2 = getCompile(phaseName, log2.getOrders().get(moduleNumber));
        return (compile2.getCompiledClasses() - compile1.getCompiledClasses()) / (float)compile1.getCompiledClasses();
    }

    /**
     * @param logs
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param phaseName nom de la phase ciblé (compile, test)
     * @return moyenne du nombre de classe compilé entre le log n1 et log n2
     */
    public static float increaseMeanNumberOfCompileClassBetween(List<Log> logs, int n1, int n2, String phaseName, int moduleNumber) {
        int cpt = 0;
        int sum = 0;
        for(int i = n1+1; i <= n2; i++){
            sum += increaseNumberOfCompileClassBetweenTwoLogs(logs.get(i-1), logs.get(i), phaseName, moduleNumber);
            cpt++;
        }
        return sum / (float) cpt;
    }

    /**
     * @param logs
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param phaseName nom de la phase ciblé (compile, test)
     * @return moyenne du nombre de classe compilé entre le log n1 et log n2 en pourcentage
     */
    public static float increaseMeanNumberOfCompileClassInPercentBetween(List<Log> logs, int n1, int n2, String phaseName, int moduleNumber) {
        int cpt = 0;
        float sum = 0;
        for(int i = n1+1; i <= n2; i++){
            sum += increaseNumberOfCompileClassInPercentBetweenTwoLogs(logs.get(i-1), logs.get(i), phaseName, moduleNumber);
            cpt++;
        }
        return sum / cpt;
    }

    /**
     * @param logs
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param phaseName nom de la phase ciblé (compile, test)
     * @return moyenne du nombre de classe compilé entre le log n1 et log n2
     */
    public static float meanClassCompiled(List<Log> logs, int n1, int n2, String phaseName, int moduleNumber) {
        int cpt = 0;
        int sum = 0;
        for(int i = n1; i <= n2; i++){
            sum += getCompile(phaseName, logs.get(i).getOrders().get(moduleNumber)).getCompiledClasses();
            cpt++;
        }
        return sum / (float) cpt;
    }

    /**
     * @param logs
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param phaseName nom de la phase ciblé (compile, test)
     * @return mediane du nombre de classe compilé entre le log n1 et log n2
     */
    public static float medianClassCompiled(List<Log> logs, int n1, int n2, String phaseName, int moduleNumber) {
        List<Log> logsWithCompiledClass = sortByClassCompiled(logs, n1, n2, phaseName, moduleNumber);
        if(logsWithCompiledClass.size() % 2 != 0) {
            Log log = logsWithCompiledClass.get(logsWithCompiledClass.size() / 2);
            Compile compile = getCompile(phaseName, log.getOrders().get(moduleNumber));
            return compile.getCompiledClasses();
        } else {
            Log log1 = logsWithCompiledClass.get(logsWithCompiledClass.size() / 2);
            Log log2 = logsWithCompiledClass.get(logsWithCompiledClass.size() / 2 - 1);
            Compile compile1 = getCompile(phaseName, log1.getOrders().get(moduleNumber));
            Compile compile2 = getCompile(phaseName, log2.getOrders().get(moduleNumber));
            return (compile1.getCompiledClasses() + compile2.getCompiledClasses()) / (float) 2;
        }
    }

    /**
     * @param logs
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param phaseName nom de la phase ciblé (compile, test)
     * @return premier quartile du nombre de classe compilé entre le log n1 et log n2
     */
    public static float firstQuartileBetween(List<Log> logs, int n1, int n2, String phaseName, int moduleNumber) {
        List<Log> logsWithCompiledClass = sortByClassCompiled(logs, n1, n2, phaseName, moduleNumber);
        if(logsWithCompiledClass.size() % 4 == 0) {
            Log log = logsWithCompiledClass.get(logsWithCompiledClass.size() * 1 / 4);
            Compile compile = getCompile(phaseName, log.getOrders().get(moduleNumber));
            return compile.getCompiledClasses();
        } else {
            Log log1 = logsWithCompiledClass.get(logsWithCompiledClass.size() * 1 / 4);
            Log log2 = logsWithCompiledClass.get(logsWithCompiledClass.size() * 1 / 4 + 1);
            Compile compile1 = getCompile(phaseName, log1.getOrders().get(moduleNumber));
            Compile compile2 = getCompile(phaseName, log2.getOrders().get(moduleNumber));
            return (compile1.getCompiledClasses() + compile2.getCompiledClasses()) / (float) 2;
        }
    }
    
    /**
     * @param logs
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param phaseName nom de la phase ciblé (compile, test)
     * @return troisième quartile du nombre de classe compilé entre le log n1 et log n2
     */
    public static float thirdQuartileBetween(List<Log> logs, int n1, int n2, String phaseName, int moduleNumber) {
        List<Log> logsWithCompiledClass = sortByClassCompiled(logs, n1, n2, phaseName, moduleNumber);
        if(logsWithCompiledClass.size() % 4 == 0) {
            Log log = logsWithCompiledClass.get(logsWithCompiledClass.size() * 3 / 4);
            Compile compile = getCompile(phaseName, log.getOrders().get(moduleNumber));
            return compile.getCompiledClasses();
        } else {
            Log log1 = logsWithCompiledClass.get(logsWithCompiledClass.size() * 3 / 4);
            Log log2 = logsWithCompiledClass.get(logsWithCompiledClass.size() * 3 / 4 - 1);
            Compile compile1 = getCompile(phaseName, log1.getOrders().get(moduleNumber));
            Compile compile2 = getCompile(phaseName, log2.getOrders().get(moduleNumber));
            return (compile1.getCompiledClasses() + compile2.getCompiledClasses()) / (float) 2;
        }
    }

    private static List<Log> sortByClassCompiled(List<Log> logs, int n1, int n2, String phaseName, int moduleNumber) {
        List<Log> logsWithCompiledClass = new LinkedList<Log>();
        for(int i = n1; i <= n2; i++) {
            logsWithCompiledClass.add(logs.get(i));        
        }

        logsWithCompiledClass.sort((Log log1, Log log2) -> {
            Compile compile1 = getCompile(phaseName, log1.getOrders().get(moduleNumber));
            Compile compile2 = getCompile(phaseName, log2.getOrders().get(moduleNumber));
            if (compile1.getCompiledClasses() > compile2.getCompiledClasses()) {
                return 1;
            } else if (compile1.getCompiledClasses() < compile2.getCompiledClasses()) {
                return -1;
            } else {
                return 0;
            }
        });
        return logsWithCompiledClass;
    }

}
