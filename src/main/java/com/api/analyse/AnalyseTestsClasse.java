package com.api.analyse;

import java.util.LinkedList;
import java.util.List;

import com.api.analyse.abstracts.AnalyseTests;
import com.api.model.Log;
import com.api.model.TestClasse;
import com.api.model.TestPhase;
import com.api.model.interfaces.Test;

public class AnalyseTestsClasse extends AnalyseTests{
    
    public static Test getTest(Log log, int moduleNumber, String testClasse){
        TestPhase testPhase = log.getOrders().get(moduleNumber).getTestPhase();
        for (TestClasse test : testPhase.getTestsByClasse()) {
            System.out.println(test.getClasse());
            System.out.println(testClasse.replace("-", "."));
            System.out.println(test.getClasse().contains(testClasse.replace("-", ".")));
            if(test.getClasse().contains(testClasse.replace("-", "."))){
                return test;
            }
        }
        return null;
    }

    /**
     * @param log1
     * @param log2
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param typeTest type de test (run, failed, skipped, error)
     * @return la différence de nombre de tests entre les deux logs
     */
    public static int increaseNumberOfTestBetweenTwoLogs(Log log1, Log log2, String typeTest, String testClasse, int moduleNumber) {
        Test test1 = getTest(log1, moduleNumber, testClasse);
        Test test2 = getTest(log2, moduleNumber, testClasse);
        if(test1 == null || test2 == null){
            return 0;
        }
        return increaseNumberOfTestBetweenTwoTests(test1, test2, typeTest);
    }

    /**
     * @param log1
     * @param log2
     * @param moduleNumber numéro du module dans le cadre d'un projet multi module Maven
     * @param typeTest type de test (run, failed, skipped, error)
     * @return la différence de nombre de tests entre les deux logs en pourcentage
     */
    public static float increaseNumberOfTestInPercentBetweenTwoLogs(Log log1, Log log2, String typeTest, String testClasse, int moduleNumber) {
        Test test1 = getTest(log1, moduleNumber, testClasse);
        Test test2 = getTest(log2, moduleNumber, testClasse);
        if(test1 == null || test2 == null){
            return 0;
        }
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
    public static float increaseMeanNumberOfTestBetween(List<Log> logs, int n1, int n2, String typeTest, String testClasse, int moduleNumber) {
        List<Test> tests = new LinkedList<Test>();
        getListOfTest(logs, n1, n2, testClasse, moduleNumber, tests);
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
    public static float increaseMeanNumberOfTestInPercentBetween(List<Log> logs, int n1, int n2, String typeTest, String testClasse, int moduleNumber){
        List<Test> tests = new LinkedList<Test>();
        getListOfTest(logs, n1, n2, testClasse, moduleNumber, tests);
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
    public static float meanTest(List<Log> logs, int n1, int n2, String typeTest, String testClasse, int moduleNumber){
        List<Test> tests = new LinkedList<Test>();
        getListOfTest(logs, n1, n2, testClasse, moduleNumber, tests);
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
    public static float medianTest(List<Log> logs, int n1, int n2, String typeTest, String testClasse, int moduleNumber){
        List<Test> tests = new LinkedList<Test>();
        getListOfTest(logs, n1, n2, testClasse, moduleNumber, tests);
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
    public static float firstQuartileTest(List<Log> logs, int n1, int n2, String typeTest, String testClasse, int moduleNumber){
        List<Test> tests = new LinkedList<Test>();
        getListOfTest(logs, n1, n2, testClasse, moduleNumber, tests);
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
    public static float thirdQuartileTest(List<Log> logs, int n1, int n2, String typeTest, String testClasse, int moduleNumber){
        List<Test> tests = new LinkedList<Test>();
        getListOfTest(logs, n1, n2, testClasse, moduleNumber, tests);
        return thirdQuartileBetween(tests, typeTest);
    }

    private static void getListOfTest(List<Log> logs, int n1, int n2, String testClasse, int moduleNumber,
            List<Test> tests) {
        for(int i = n1; i <= n2; i++){
            Test test = getTest(logs.get(i), moduleNumber, testClasse);
            if(test != null){
                tests.add(test);
            }
        }
    }

}
