package com.example.bikerentalsystem.model;

public class ElectricBike extends Bike {
    public ElectricBike() {
        setType("Electric");
    }

    public ElectricBike(Long id, String model, int availabilityCount, double rating) {
        super(id, model, "Electric", availabilityCount, rating);
    }

    @Override
    public String displayInfo() {
        return "Electric Bike - Model: " + getModel() + ", Availability: " + getAvailabilityCount() + ", Rating: " + getRating();
    }
}