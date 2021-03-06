package com.api.analyse;

import java.util.LinkedList;
import java.util.List;

import com.api.analyse.abstracts.AnalyseTests;
import com.api.model.Log;
import com.api.model.interfaces.Test;

public class AnalyseTestsPhase extends AnalyseTests {
    
    /**
     * @param log1
     * @param log2
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param typeTest type de test (run, failed, skipped, error)
     * @return la différence de nombre de tests entre les deux logs
     */
    public static int increaseNumberOfTestBetweenTwoLogs(Log log1, Log log2, String typeTest, int moduleNumber) {
        Test test1 = log1.getOrders().get(moduleNumber).getTestPhase();
        Test test2 = log2.getOrders().get(moduleNumber).getTestPhase();
        return increaseNumberOfTestBetweenTwoTests(test1, test2, typeTest);
    }

    /**
     * @param log1
     * @param log2
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param typeTest type de test (run, failed, skipped, error)
     * @return la différence de nombre de tests entre les deux logs en pourcentage
     */
    public static float increaseNumberOfTestInPercentBetweenTwoLogs(Log log1, Log log2, String typeTest, int moduleNumber) {
        Test test1 = log1.getOrders().get(moduleNumber).getTestPhase();
        Test test2 = log2.getOrders().get(moduleNumber).getTestPhase();
        return increaseNumberOfTestInPercentBetweenTwoTests(test1, test2, typeTest);
    }

    /**
     * @param logs
     * @param n1
     * @param n2
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param typeTest type de test (run, failed, skipped, error)
     * @return moyenne de la différence du nombre de tests entre le test n1 et le test n2
     */
    public static float increaseMeanNumberOfTestBetween(List<Log> logs, int n1, int n2, String typeTest, int moduleNumber) {
        List<Test> tests = new LinkedList<Test>();
        getListOfTest(logs, n1, n2, moduleNumber, tests);
        return increaseMeanNumberTestBetween(tests, typeTest);
    }

    /**
     * @param logs
     * @param n1
     * @param n2
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param typeTest type de test (run, failed, skipped, error)
     * @return moyenne de la différence du nombre de tests entre le test n1 et le test n2 en pourcentage
     */
    public static float increaseMeanNumberOfTestInPercentBetween(List<Log> logs, int n1, int n2, String typeTest, int moduleNumber){
        List<Test> tests = new LinkedList<Test>();
        getListOfTest(logs, n1, n2, moduleNumber, tests);
        return increaseMeanNumberOfTestInPercentBetween(tests, typeTest);
    }

    /**
     * @param logs
     * @param n1
     * @param n2
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param typeTest type de test (run, failed, skipped, error)
     * @return moyenne du nombre de tests entre le test n1 et le test n2
     */
    public static float meanTest(List<Log> logs, int n1, int n2, String typeTest, int moduleNumber){
        List<Test> tests = new LinkedList<Test>();
        getListOfTest(logs, n1, n2, moduleNumber, tests);
        return meanTest(tests, typeTest);
    }

    /**
     * @param logs
     * @param n1
     * @param n2
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param typeTest type de test (run, failed, skipped, error)
     * @return mediane du nombre de tests entre le test n1 et le test n2
     */
    public static float medianTest(List<Log> logs, int n1, int n2, String typeTest,int moduleNumber){
        List<Test> tests = new LinkedList<Test>();
        getListOfTest(logs, n1, n2, moduleNumber, tests);
        return medianTest(tests, typeTest);
    }

    /**
     * @param logs
     * @param n1
     * @param n2
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param typeTest type de test (run, failed, skipped, error)
     * @return premier quartile du nombre de tests entre le test n1 et le test n2
     */
    public static float firstQuartileTest(List<Log> logs, int n1, int n2, String typeTest, int moduleNumber){
        List<Test> tests = new LinkedList<Test>();
        getListOfTest(logs, n1, n2, moduleNumber, tests);
        return firstQuartileBetween(tests, typeTest);
    }

    /**
     * @param logs
     * @param n1
     * @param n2
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param typeTest type de test (run, failed, skipped, error)
     * @return troisième quartile du nombre de tests entre le test n1 et le test n2
     */
    public static float thirdQuartileTest(List<Log> logs, int n1, int n2, String typeTest, int moduleNumber){
        List<Test> tests = new LinkedList<Test>();
        getListOfTest(logs, n1, n2, moduleNumber, tests);
        return thirdQuartileBetween(tests, typeTest);
    }

    private static void getListOfTest(List<Log> logs, int n1, int n2, int moduleNumber, List<Test> tests) {
        for(int i = n1; i <= n2; i++){
            Test test = logs.get(i).getOrders().get(moduleNumber).getTestPhase();
            if(test != null){
                tests.add(test);
            }
        }
    }

}
