package com.example.bikerentalsystem.model;

import java.time.LocalDate;

public class Ride {
    private int id;
    private String name;
    private String contact;
    private String origin;
    private String destination;
    private LocalDate travelDate;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public LocalDate getTravelDate() { return travelDate; }
    public void setTravelDate(LocalDate travelDate) { this.travelDate = travelDate; }
}
