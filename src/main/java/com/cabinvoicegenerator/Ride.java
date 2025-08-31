package com.cabinvoicegenerator;

public class Ride {

    public enum RideType {
        NORMAL,
        PREMIUM
    }
    private double distance;
    private int time;
    private RideType rideType;

    public Ride(double distance, int time, RideType rideType) {
        this.distance = distance;
        this.time = time;
        this.rideType = rideType;
    }

    public double getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public RideType getRideType() {
        return rideType;
    }
}
