package com.cabinvoicegenerator;
public class InvoiceGenerator {
    private static final double NORMAL_COST_PER_KM = 10.0;
    private static final double NORMAL_COST_PER_MIN = 1.0;
    private static final double NORMAL_MIN_FARE = 5.0;

    private static final double PREMIUM_COST_PER_KM = 15.0;
    private static final double PREMIUM_COST_PER_MIN = 2.0;
    private static final double PREMIUM_MIN_FARE = 20.0;

    public double calculateFare(double distance, int time, Ride.RideType type) {
        double totalFare = 0;
        if (type == Ride.RideType.NORMAL) {
            totalFare = distance * NORMAL_COST_PER_KM + time * NORMAL_COST_PER_MIN;
            return Math.max(totalFare, NORMAL_MIN_FARE);
        } else {
            totalFare = distance * PREMIUM_COST_PER_KM + time * PREMIUM_COST_PER_MIN;
            return Math.max(totalFare, PREMIUM_MIN_FARE);
        }
    }

    public double calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride.getDistance(), ride.getTime(), ride.getRideType());
        }
        return totalFare;
    }

    public InvoiceSummary calculateSummary(Ride[] rides) {
        double totalFare = calculateFare(rides);
        return new InvoiceSummary(rides.length, totalFare);
    }
}
