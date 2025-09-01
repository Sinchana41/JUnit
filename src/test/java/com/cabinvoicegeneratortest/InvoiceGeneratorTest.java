package com.cabinvoicegeneratortest;

import com.cabinvoicegenerator.*;
import org.junit.jupiter.api.*;

public class InvoiceGeneratorTest {

    // Negative Test 1: Zero distance
    @Test
    public void givenZeroDistance_ShouldThrowException() {
        InvoiceGenerator generator = new InvoiceGenerator();
        Exception ex = Assertions.assertThrows(InvalidRideException.class, () -> {
            generator.calculateFare(0.0, 5, Ride.RideType.NORMAL);
        });
        Assertions.assertEquals("Distance must be greater than 0", ex.getMessage());
    }

    // Negative Test 2: Negative time
    @Test
    public void givenNegativeTime_ShouldThrowException() {
        InvoiceGenerator generator = new InvoiceGenerator();
        Exception ex = Assertions.assertThrows(InvalidRideException.class, () -> {
            generator.calculateFare(2.0, -3, Ride.RideType.NORMAL);
        });
        Assertions.assertEquals("Time cannot be negative", ex.getMessage());
    }

    // Negative Test 3: Null Ride Type
    @Test
    public void givenNullRideType_ShouldThrowException() {
        InvoiceGenerator generator = new InvoiceGenerator();
        Exception ex = Assertions.assertThrows(InvalidRideException.class, () -> {
            generator.calculateFare(2.0, 5, null);
        });
        Assertions.assertEquals("Ride type cannot be null", ex.getMessage());
    }

    // Step 1: Calculate fare for a single normal ride
    @Test
    public void givenNormalRide_WhenCalculated_ShouldReturnFare() {
        InvoiceGenerator generator = new InvoiceGenerator();
        double fare = generator.calculateFare(2.0, 5, Ride.RideType.NORMAL);
        Assertions.assertEquals(25.0, fare);
    }

    // Step 2: Ensure minimum fare condition is applied
    @Test
    public void givenPremiumRide_WhenCalculated_ShouldReturnFare() {
        InvoiceGenerator generator = new InvoiceGenerator();
        double fare = generator.calculateFare(2.0, 5, Ride.RideType.PREMIUM);
        Assertions.assertEquals(40.0, fare);
    }

    // Step 3: Calculate fare for multiple rides
    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        InvoiceGenerator generator = new InvoiceGenerator();
        Ride[] rides = {
                new Ride(2.0, 5, Ride.RideType.NORMAL),
                new Ride(0.1, 1, Ride.RideType.NORMAL)
        };
        double fare = generator.calculateFare(rides);
        Assertions.assertEquals(30.0, fare);
    }

    // Step 4: Generate invoice summary with average fare
    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        InvoiceGenerator generator = new InvoiceGenerator();
        Ride[] rides = {
                new Ride(2.0, 5, Ride.RideType.NORMAL),
                new Ride(0.1, 1, Ride.RideType.NORMAL)
        };
        InvoiceSummary summary = generator.calculateSummary(rides);
        InvoiceSummary expected = new InvoiceSummary(2, 30.0);
        Assertions.assertEquals(expected, summary);
    }
}
