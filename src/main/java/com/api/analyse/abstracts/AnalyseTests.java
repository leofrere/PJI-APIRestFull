package com.api.analyse.abstracts;

import java.util.List;

import com.api.model.interfaces.Test;

public abstract class AnalyseTests extends Analyse {
    
    public static int getNumberOfTestOfType(String typeTest, Test test){
        switch(typeTest){
            case "run":
                return test.getTestsRun();
            case "failed":
                return test.getTestsFailed();
            case "skipped":
                return test.getTestsSkipped();
            default:
                return test.getTestsError();
        }
    }

    /**
     * @param test1
     * @param test2
     * @param typeTest type de test (run, failed, skipped, error)
     * @return la différence de nombre de tests entre les deux logs
     */
    public static int increaseNumberOfTestBetweenTwoTests(Test test1, Test test2, String typeTest) {
        return getNumberOfTestOfType(typeTest, test2) - getNumberOfTestOfType(typeTest, test1);
    }

    /**
     * @param test1
     * @param test2
     * @param typeTest type de test (run, failed, skipped, error)
     * @return la différence de nombre de tests entre les deux logs en pourcentage
     */
    public static float increaseNumberOfTestInPercentBetweenTwoTests(Test test1, Test test2, String typeTest) {
        return (getNumberOfTestOfType(typeTest, test2) - getNumberOfTestOfType(typeTest, test1)) / (float)getNumberOfTestOfType(typeTest, test1);
    }

    /**
     * @param tests
     * @param n1
     * @param n2
     * @param typeTest type de test (run, failed, skipped, error)
     * @return moyenne de la différence du nombre de tests entre le test n1 et le test n2
     */
    public static float increaseMeanNumberTestBetween(List<Test> tests, int n1, int n2, String typeTest) {
        int cpt = 0;
        int sum = 0;
        for(int i = n1+1; i <= n2; i++){
            sum += increaseNumberOfTestBetweenTwoTests(tests.get(i-1), tests.get(i), typeTest);
            cpt++;
        }
        return sum / (float) cpt;
    }

    /**
     * @param tests
     * @param n1
     * @param n2
     * @param typeTest type de test (run, failed, skipped, error)
     * @return moyenne de la différence de nombre de tests entre le test n1 et le test n2 en pourcentage
     */
    public static float increaseMeanNumberOfTestInPercentBetween(List<Test> tests, int n1, int n2, String typeTest) {
        int cpt = 0;
        float sum = 0;
        for(int i = n1+1; i <= n2; i++){
            sum += increaseNumberOfTestInPercentBetweenTwoTests(tests.get(i-1), tests.get(i), typeTest);
            cpt++;
        }
        return sum / (float) cpt;
    }

    /**
     * @param tests
     * @param n1
     * @param n2
     * @param typeTest type de test (run, failed, skipped, error)
     * @return moyenne du nombre de tests entre le test n1 et le test n2
     */
    public static float meanTest(List<Test> tests, int n1, int n2, String typeTest){
        int cpt = 0;
        int sum = 0;
        for(int i = n1+1; i <= n2; i++){
            sum += getNumberOfTestOfType(typeTest, tests.get(i));
            cpt++;
        }
        return sum / (float) cpt;
    }

}
