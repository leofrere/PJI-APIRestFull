package com.api.analyse;

import java.util.List;

import com.api.model.*;

public abstract class Analyse {

    protected static Phase getPhase(String phaseName, Order order) {
        Phase phase;
        switch(phaseName){
            case "compile":
                phase = order.getCompilePhase();
                break;
            case "test":
                phase = order.getTestPhase();
                break;
            default:
                phase = order.getCompilePhase();
                break;
        }
        return phase;
    }


    public static float increaseOfNumberOfTest(Log log1, Log log2, int moduleNumber){
        TestPhase testPhase1 = log1.getOrders().get(moduleNumber).getTestPhase();
        TestPhase testPhase2 = log2.getOrders().get(moduleNumber).getTestPhase();

        int numberOfTest1 = testPhase1.getTestsRun() + testPhase1.getTestsFailed() + testPhase1.getTestsSkipped() + testPhase1.getTestsError();
        int numberOfTest2 = testPhase2.getTestsRun() + testPhase2.getTestsFailed() + testPhase2.getTestsSkipped() + testPhase2.getTestsError();

        return (float) (numberOfTest2 - numberOfTest1) / (float) numberOfTest1;
    }

    public static float increaseOfNumberOfTest(Log log1, Log log2){
        return increaseOfNumberOfTest(log1, log2, 0);
    }

    public static int numberOfErrorOfPhase(List<Log> logs, int moduleNumber, String phaseName){
        int numberOfError = 0;
        Phase phase;

        for(Log log : logs){
            Order order = log.getOrders().get(moduleNumber);

            phase = getPhase(phaseName, order);

            if(phase.getErrorsTrace().length() > 0 && phase.getStatus().equals("ko")) {
                numberOfError++;
            }
        }

        return numberOfError;
    }

    public static String phaseWithTheMostError(List<Log> logs, int moduleNumber){
        String phaseName = "compile";
        int numberOfError = numberOfErrorOfPhase(logs, moduleNumber, phaseName);

        for(String phase : new String[]{"package", "test"}){
            int numberOfErrorTmp = numberOfErrorOfPhase(logs, moduleNumber, phase);
            if(numberOfErrorTmp > numberOfError){
                phaseName = phase;
                numberOfError = numberOfErrorTmp;
            }
        }

        return phaseName;
    }

    public static String phaseWithTheMostError(List<Log> logs){
        return phaseWithTheMostError(logs, 0);
    }

}
