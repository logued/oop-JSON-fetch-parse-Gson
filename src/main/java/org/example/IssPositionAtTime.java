package org.example;

// Class that represents the Position of the ISS at a specific Time (Timestamped)

import java.time.Instant;
import java.time.ZoneId;

public class IssPositionAtTime {
    private long timestamp ;        // Unix Time (seconds since 1st January 1970
    private String message;
    private Position iss_position;  // must use field name to match key name in JSON

    // The structure of our classes must match the structure of the JSON, so here we need to have
    // a class called "iss_position" that will have two fields that can take the latitude and longitude values.
    // ISSUE: Not very flexible to have the API dictate the structure of your classes.
    // SOLUTION: Use Gson Builder, so we don't have to have exact match between structure and names. (see next sample)

    // The ISS API returns the timestamp in Unix Time
    // so, it is the number of seconds since 1st January 1970.
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
