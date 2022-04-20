package com.api.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import com.api.model.JenkinsBuild;

public class ReaderBuild {
    
    public static BufferedReader readBuild(JenkinsBuild jenkinsBuild, int buildNumber) throws Exception {
        try {
            URL url = new URL(jenkinsBuild.getUrl() + "/job/" + jenkinsBuild.getProjectName() + "/" + String.valueOf(buildNumber) + "/consoleText");
            String authStr = jenkinsBuild.getUsername() + ":" + jenkinsBuild.getPassword();
            String encoding = Base64.getEncoder().encodeToString(authStr.getBytes("utf-8"));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            InputStream content = connection.getInputStream();
            return new BufferedReader(new InputStreamReader(content));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
