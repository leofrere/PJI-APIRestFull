package com.api.analyse;

import java.util.List;

import com.api.model.Log;
import com.api.model.Order;
import com.api.model.interfaces.Compile;

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
     * @param log1
     * @param log2
     * @param phaseName nom de la phase ciblé (compile, test)
     * @return la différence de nombre de classes compilé entre les deux logs
     */
    public static int increaseNumberOfCompileClassBetweenTwoLogs(Log log1, Log log2, String phaseName) {
        return increaseNumberOfCompileClassBetweenTwoLogs(log1, log2, phaseName, 0);
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
     * @return la différence de nombre de classes compilé entre les deux logs en pourcentage
     */
    public static float increaseNumberOfCompileClassInPercentBetweenTwoLogs(Log log1, Log log2, String phaseName) {
        return increaseNumberOfCompileClassInPercentBetweenTwoLogs(log1, log2, phaseName, 0);
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
     * @param phaseName nom de la phase ciblé (compile, test)
     * @return moyenne du nombre de classe compilé entre le log n1 et log n2
     */
    public static float increaseMeanNumberOfCompileClassBetween(List<Log> logs, int n1, int n2, String phaseName) {
        return increaseMeanNumberOfCompileClassBetween(logs, n1, n2, phaseName, 0);
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
     * @param phaseName nom de la phase ciblé (compile, test)
     * @return moyenne du nombre de classe compilé entre le log n1 et log n2 en pourcentage
     */
    public static float increaseMeanNumberOfCompileClassInPercentBetween(List<Log> logs, int n1, int n2, String phaseName) {
        return increaseMeanNumberOfCompileClassInPercentBetween(logs, n1, n2, phaseName, 0);
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
        for(int i = n1+1; i <= n2; i++){
            sum += logs.get(i).getOrders().get(moduleNumber).getCompilePhase().getCompiledClasses();
            cpt++;
        }
        return sum / (float) cpt;
    }

    /**
     * @param logs
     * @param phaseName nom de la phase ciblé (compile, test)
     * @return moyenne du nombre de classe compilé entre le log n1 et log n2
     */
    public static float meanClassCompiled(List<Log> logs, int n1, int n2, String phaseName) {
        return meanClassCompiled(logs, n1, n2, phaseName, 0);
    }
    


}
