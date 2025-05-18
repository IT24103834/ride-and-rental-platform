package com.platform.model;

public class Bike {
    private String id;
    private String model;
    private String type;
    private int availability;

    public Bike(String id, String model, String type, int availability) {
        this.id = id;
        this.model = model;
        this.type = type;
        this.availability = availability;
    }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getAvailability() { return availability; }
    public void setAvailability(int availability) { this.availability = availability; }


    public String displayInfo() {
        return model + " (" + type + ") - Available: " + availability;
    }


}
