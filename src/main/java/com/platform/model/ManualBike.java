package com.platform.model;

import com.platform.model.Bike;

public class ManualBike extends Bike {
    public ManualBike(String id, String model, int availability) {
        super(id, model, "Manual", availability);
    }

    @Override
    public String displayInfo() {
        return "[Manual Bike] " + super.displayInfo();
    }
}