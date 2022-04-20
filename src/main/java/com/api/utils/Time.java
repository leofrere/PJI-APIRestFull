package com.api.utils;

public class Time {
    
    public static String differenceBetween(String time1, String time2){
        int th1 = Integer.parseInt(time1.split(":")[0]);
                    int tm1 = Integer.parseInt(time1.split(":")[1]);
                    float ts1 = Float.parseFloat(time1.split(":")[2].replace(",", "."));

                    int th2 = Integer.parseInt(time2.split(":")[0]);
                    int tm2 = Integer.parseInt(time2.split(":")[1]);
                    float ts2 = Float.parseFloat(time2.split(":")[2].replace(",", "."));

                    int h;
                    int m;
                    float s;

                    if(tm2 < tm1){
                        h = th2 - th1 - 1;
                        tm2 += 60;
                    } else {
                        h = th2 - th1;
                    }

                    if(ts2 < ts1){
                        m = tm2 - tm1 - 1;
                        ts2 += 60;
                    } else {
                        m = tm2 - tm1;
                    }

                    s = ts2 - ts1;
                    s += m * 60 + h * 3600;
                    return String.format("%fs", s);
    }

}
