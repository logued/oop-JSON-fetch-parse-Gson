package org.example;

public class IssPosition {
    public double latitude;
    public double longitude;

    public IssPosition(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "IssPosition{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
