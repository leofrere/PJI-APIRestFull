package com.api.analyse;

import java.util.List;

import com.api.model.*;

public class Analyse {

    public static float meanTimeForCompilePhase(List<Log> logs, int moduleNumber, String phaseName){
        float meanTime = 0;
        int cpt = 0;
        Phase phase;

        for(Log log : logs){
            Order order = log.getOrders().get(moduleNumber);

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

            if(phase.getStatus().equals("ok")) {
                meanTime +=  Float.parseFloat(phase.getTime().replace(",", ".").replace("s", ""));
                cpt++;
            }
           
        }

        return meanTime/cpt;
    }

    public static float meanTimeForCompilePhase(List<Log> logs, String phaseName){
        return meanTimeForCompilePhase(logs, 0, phaseName);
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

}
