package org.example;                // March 2024

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Demonstrates how to fetch JSON from a remote API (Endpoint), and
 * parse the returned JSON string into a Java Object that has
 * a corresponding structure.
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

    public static void main(String[] args) throws IOException, InterruptedException {

        final String url = "http://api.open-notify.org/iss-now.json";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String jsonString = response.body();

        if (jsonString == null) {
            System.out.println("Json String was empty.");
            return;
        }

        // Instantiate (create) a Gson Parser
        Gson gsonParser = new Gson();

        // Call the fromJson() method in the parser to create a new ISSPositionTime object
        // and populate it with the JSON string data.
        // The class field names must match the key names in the json string.

        IssPositionAtTime issPositionAtTime = gsonParser.fromJson(jsonString, IssPositionAtTime.class);

        System.out.println(issPositionAtTime);
    }

}