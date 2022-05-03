package com.api.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GraphQLRequest {
    
    public static String evolutionOfPhaseVariable(String project, String phase, String variable) throws Exception{

        String res = "";
        URL url = new URL("http://localhost:8081/graphql");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");

        String input="{\"query\":\"{logsByProject(project:\\\""+ project +"\\\"){build,orders{"+ phase +"{"+ variable +"}}}}\\n\"}";
        url.toString();

        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String line;
        while ((line = br.readLine()) != null) {
            res += line;
        }
        conn.disconnect();
        return res;
    }

    public static String evolutionOfTestClasseVariable(String project, String variable) throws Exception{

        String res = "";
        URL url = new URL("http://localhost:8081/graphql");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");

        String input="{\"query\":\"{logsByProject(project:\\\""+ project +"\\\"){build,orders{testPhase{testsByClasse{classe,"+ variable +"}}}}}\\n\"}";
        url.toString();

        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String line;
        while ((line = br.readLine()) != null) {
            res += line;
        }
        conn.disconnect();
        return res;
    }

}
