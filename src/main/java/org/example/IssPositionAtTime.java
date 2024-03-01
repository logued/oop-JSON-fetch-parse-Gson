package org.example;                // Updated: March 2024

// Class that represents the Position of the ISS at a specific Time (Timestamped)

import java.time.Instant;
import java.time.ZoneId;

/**
 * This class is used to store the data retrieved from the following API
 * http://api.open-notify.org/iss-now.json
 * This is the JSON string returned from calling the API (try it in your browser)
      { "iss_position":  { "longitude": "-116.9469", "latitude": "-49.3874"   },
        "timestamp": 1709330687,
        "message": "success"
      }
 * If we declare the names of the fields in this class to be the same the names of the keys that
 * appear in the JSON string.  (i.e. iss_position, timestamp, and message), then this
 * allows the Gson parser to match the keys directly the with fields - with no mappings needed.
 */

public class IssPositionAtTime {
    private long timestamp ;        // Unix Time (number of seconds since 1st January 1970
    private String message;
    private Position iss_position;  // must use field name to match key name in JSON string

    // The nesting structure of our class must match the structure of the JSON, so in this class
    // we need to have another class called "iss_position" that has two fields that can take
    // the latitude and longitude values. (Composition)
    // ISSUE: Not very flexible to have the API dictate the structure of your classes.
    // SOLUTION: Use Gson Builder to allow us to map the name of a key to a field with a different
    // name in the class so that we don't have to have an exact match between structure and names.
    // (Shown in next sample).

    // Note: The ISS API returns the timestamp in Unix Epoch Time
    // which is the number of seconds since 1st January 1970.
    //
    public IssPositionAtTime(long unixTimeInSeconds, String message, double latitude, double longitude) {
        this.timestamp =  unixTimeInSeconds;
        this.message = message;
        this.iss_position = new Position(latitude,longitude);
    }

    @Override
    public String toString() {
        return "IssPositionAtTime{" +
                "timestamp=" + timestamp + "[" +
                            Instant.ofEpochSecond(timestamp)
                                    .atZone(ZoneId.of("Europe/Dublin"))
                                    .toString() + "]" +
                ", message='" + message + '\'' +
                ", location=" + iss_position.toString()  +      //"[" + latitude + ", " + longitude + "]" +
                '}';
    }
}
