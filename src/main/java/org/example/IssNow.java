package org.example;

public class IssNow {
    private int timestamp ;
    private String message;
    private Position position;

    public IssNow( int time, String message, double lat, double lng) {
        this.message = message;

    }

    @Override
    public String toString() {
        return "IssNow{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", position=" + position +
                '}';
    }
}
