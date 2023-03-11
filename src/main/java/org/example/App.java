package org.example;                // March 2023

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;//

// Demonstrates how to fetch JSON from a remote API, and
// parse the returned JSON into Java Objects, that have
// a corresponding structure.

// This sample uses the following API:
// API Request:   http://api.open-notify.org/iss-now.json
//
//  Response from API request: (as at March 2023)
// { "timestamp": 1678523947,                   // UNIX Time (seconds since 1st Jan 1970)
//   "iss_position": { "latitude": "-47.8432",
//                     "longitude": "93.5798"},
//   "message": "success"
// }

class App {

    public static void main(String[] args) throws IOException {

        String url = "http://api.open-notify.org/iss-now.json";

        String jsonString = fetchJsonFromAPI(url);

        if(jsonString==null){
            System.out.println("Connection failed, exiting.");
            return;
        }

        // Instantiate (create) a Gson Parser
        Gson gsonParser = new Gson();

        // We use the default Gson parser here, which will map the JSON data
        // into the IssPositionAtTime object as long as
        // the names of the key fields in the JSON string are exactly the same
        // as the names of the instance fields in the IssPositionAtTime class.

        IssPositionAtTime issPositionAtTime = gsonParser.fromJson(jsonString, IssPositionAtTime.class );

        //issPositionAtTime = new IssPositionAtTime(1678523947,"test message",56.789,6.789);
        System.out.println(issPositionAtTime);
    }

    private static String fetchJsonFromAPI(String uri) throws IOException {

        final int CONNECTION_OK = 200;  // code returned if connection is successful

        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();

        if (responseCode == CONNECTION_OK)
        {
            // we have connected successfully, so now
            // create an input buffer to read from the API stream
            BufferedReader inBuffer = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String inputLine;

            // create a String buffer to build up the JSON String
            // that will be returned from the stream
            StringBuffer strBuffer = new StringBuffer();

            // read in all lines from stream until the stream
            // has been emptied.
            while ((inputLine = inBuffer.readLine()) != null) {
                strBuffer.append(inputLine);
            }
            inBuffer.close();

            return strBuffer.toString();  // return the JSON String
        }

        return null; // if connection failed
    }
}