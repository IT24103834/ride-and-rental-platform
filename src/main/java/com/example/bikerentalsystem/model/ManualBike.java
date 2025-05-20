package com.example.bikerentalsystem.model;

public class ManualBike extends Bike {
    public ManualBike() {
        setType("Manual");
    }

    public ManualBike(Long id, String model, int availabilityCount, double rating) {
        super(id, model, "Manual", availabilityCount, rating);
    }

    @Override
    public String displayInfo() {
        return "Manual Bike - Model: " + getModel() + ", Availability: " + getAvailabilityCount() + ", Rating: " + getRating();
    }
}