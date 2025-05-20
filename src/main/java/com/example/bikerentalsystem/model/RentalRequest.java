package com.example.bikerentalsystem.model;

public class RentalRequest {
    private Long id;
    private String userName;
    private String bikeType; // "Electric" or "Manual"
    private String status; // "Pending", "Assigned", "Cancelled"

    public RentalRequest() {}

    public RentalRequest(Long id, String userName, String bikeType, String status) {
        this.id = id;
        this.userName = userName;
        this.bikeType = bikeType;
        this.status = status;
    }

    // Getters and Setters (Encapsulation)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getBikeType() { return bikeType; }
    public void setBikeType(String bikeType) { this.bikeType = bikeType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}