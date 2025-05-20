package com.example.bikerentalsystem.model;

public abstract class Bike {
    private Long id;
    private String model;
    private String type;
    private int availabilityCount;
    private double rating;

    public Bike() {}

    public Bike(Long id, String model, String type, int availabilityCount, double rating) {
        this.id = id;
        this.model = model;
        this.type = type;
        this.availabilityCount = availabilityCount;
        this.rating = rating;
    }

    // Getters and Setters (Encapsulation)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getAvailabilityCount() { return availabilityCount; }
    public void setAvailabilityCount(int availabilityCount) { this.availabilityCount = availabilityCount; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    // Polymorphic method to display bike info
    public abstract String displayInfo();
}