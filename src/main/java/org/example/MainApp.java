package org.example;                // March 2024

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Demonstrates:
 * - Use a HttpClient to get response from a remote API (Endpoint)
 * - ( i.e. http://api.open-notify.org/iss-now.json )
 * - get the JSON string (the response body from the response)
 * - convert the JSON string into a Java object using the Gson Parser
 * - check the return status code from the Http response
 * <p>
 * This sample uses the following API:
 * API Request:   http://api.open-notify.org/iss-now.json
 * <p>
 * Response from API request: (as at March 2024)
          {
              "iss_position": {
                  "longitude": "-116.9469",
                  "latitude": "-49.3874"
              },
              "timestamp": 1709330687,    // UNIX Epoch Time (seconds since 1st Jan 1970)
              "message": "success"
          }
 */

class MainApp {

    final String URL = "http://api.open-notify.org/iss-now.json";   // address of API Endpoint

    public static void main(String[] args)  {
        MainApp app = new MainApp();
        app.start();
    }

    public void start() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()  // build an HTTP request
                .uri(URI.create(URL))
                .build();

        HttpResponse<String> response = null;
        // send() throws a Checked Exceptions, so we need to provide a try-catch block
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch(Exception e){
            e.printStackTrace();
        }

        // Check the response code returned from the API endpoint
        // A code of 200 indicates success
        if( response.statusCode() != 200 ) {
            System.out.println("HTTP Request failed - Status code returned = " + response.statusCode());
            return;
        }
        // get the body (the data payload) from the HTTP response
        String jsonResponseString = response.body();

        if (jsonResponseString==null) {
            System.out.println("Json String was empty.");
            return;
        }

        // Instantiate (create) a Gson Parser
        Gson gsonParser = new Gson();

        // Call the fromJson() method of the parser to create a new ISSPositionTime object
        // and populate it with the JSON String data.
        // The class field names must match the key names in the json string.
        IssPositionAtTime issPositionAtTime=null;
        try {
            issPositionAtTime = gsonParser.fromJson(jsonResponseString, IssPositionAtTime.class);
        }
        catch( JsonSyntaxException ex)
        {
            System.out.println("Jason syntax error encountered. " + ex);
        }
        System.out.println(issPositionAtTime);
    }
}