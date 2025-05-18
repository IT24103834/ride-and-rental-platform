package com.platform.model;

import com.platform.model.Bike;

public class AutomaticBike extends Bike {
    public AutomaticBike(String id, String model, int availability) {
        super(id, model, "Electric", availability);
    }

    @Override
    public String displayInfo() {
        return "[E-Bike] " + super.displayInfo();
    }
}
