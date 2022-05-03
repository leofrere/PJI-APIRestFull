package com.api.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GraphQLRequest {

    public static String sendRequest(String request) throws Exception{

        String res = "";
        URL url = new URL("http://localhost:8081/graphql");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        url.toString();

        OutputStream os = conn.getOutputStream();
        os.write(request.getBytes());
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
