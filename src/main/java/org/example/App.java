package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class App {

    public static void main(String[] args) throws IOException {

        String url = "http://api.open-notify.org/iss-now.json";

        String content = fetchContent(url);

        Gson gson = new Gson();

//                new GsonBuilder().registerTypeAdapter(Employee.class, new JsonDeserializerEmployee())
//                .serializeNulls().create();

        IssNow issNow = gson.fromJson(content, new TypeToken<IssNow>() {
        }.getType());

        System.out.println(issNow);
    }

    private static String fetchContent(String uri) throws IOException {

        final int OK = 200;
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        int responseCode = connection.getResponseCode();
        if (responseCode == OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        }

        return null;
    }
}