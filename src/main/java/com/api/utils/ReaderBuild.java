package com.api.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.api.model.JenkinsBuild;

public class ReaderBuild {
    
    public static BufferedReader readBuild(JenkinsBuild jenkinsBuild, int buildNumber) throws Exception {
        try {
            URL url = new URL(jenkinsBuild.getUrl() + "/job/" + jenkinsBuild.getPath() + "/" + String.valueOf(buildNumber) + "/consoleText");
            String authStr = jenkinsBuild.getUsername() + ":" + jenkinsBuild.getPassword();
            String encoding = Base64.getEncoder().encodeToString(authStr.getBytes("utf-8"));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            if(!jenkinsBuild.getUsername().equals("") && !jenkinsBuild.getPassword().equals("")){
                connection.setRequestProperty("Authorization", "Basic " + encoding);
            }
            InputStream content = connection.getInputStream();
            return new BufferedReader(new InputStreamReader(content));
        } catch (Exception e) {
            System.out.println("URL: " + jenkinsBuild.getUrl() + "/job/" + jenkinsBuild.getProjectName() + "/" + String.valueOf(buildNumber) + "/consoleText not exist");
            e.printStackTrace();
        }
        return null;
    }

    public static int lastBuild(JenkinsBuild jenkinsBuild) throws Exception {
        try {
            URL url = new URL(jenkinsBuild.getUrl() + "/job/" + jenkinsBuild.getPath() + "/lastBuild/");
            String authStr = jenkinsBuild.getUsername() + ":" + jenkinsBuild.getPassword();
            String encoding = Base64.getEncoder().encodeToString(authStr.getBytes("utf-8"));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            if(!jenkinsBuild.getUsername().equals("") && !jenkinsBuild.getPassword().equals("")){
                connection.setRequestProperty("Authorization", "Basic " + encoding);
            }
            InputStream content = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line = null;
            Pattern p1 = Pattern.compile("<title>(.*?)</title>");
            while ((line = reader.readLine()) != null) {
                Matcher m1 = p1.matcher(line);
                if (m1.find()){
                    Pattern p2 = Pattern.compile("#[0-9]*");
                    Matcher m2 = p2.matcher(m1.group(0));
                    if (m2.find()) {
                        return Integer.parseInt(m2.group(0).replace("#", ""));
                    }
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
