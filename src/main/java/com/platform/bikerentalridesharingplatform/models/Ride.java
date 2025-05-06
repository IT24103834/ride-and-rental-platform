package com.platform.bikerentalridesharingplatform.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Ride {
    private String rideId;
    private String origin;
    private String destination;
    private String datetime;
    private int totalSeats;
    private String createdBy;
    private String status;
    private List<String> joinedUserIds;

    // Default constructor
    public Ride() {
        this.joinedUserIds = new ArrayList<>();
    }

    // Full constructor
    public Ride(String rideId, String origin, String destination, String datetime,
                int totalSeats, String createdBy, String status, List<String> joinedUserIds) {
        this.rideId = rideId;
        this.origin = origin;
        this.destination = destination;
        this.datetime = datetime;
        this.totalSeats = totalSeats;
        this.createdBy = createdBy;
        this.status = status;
        this.joinedUserIds = (joinedUserIds != null) ? joinedUserIds : new ArrayList<>();
    }

    // Getters and setters
    public String getRideId() { return rideId; }
    public void setRideId(String rideId) { this.rideId = rideId; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDatetime() { return datetime; }
    public void setDatetime(String datetime) { this.datetime = datetime; }

    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<String> getJoinedUserIds() { return joinedUserIds; }
    public void setJoinedUserIds(List<String> joinedUserIds) {
        this.joinedUserIds = (joinedUserIds != null) ? joinedUserIds : new ArrayList<>();
    }

    /**
     * Serializes this Ride into a pipe-delimited string.
     * Format: rideId|origin|destination|datetime|totalSeats|createdBy|status|user1,user2,...
     */
    @Override
    public String toString() {
        String users = String.join(",", joinedUserIds); // Join list into comma-separated
        return String.join("|",
                rideId,
                origin,
                destination,
                datetime,
                String.valueOf(totalSeats),
                createdBy,
                status,
                users
        );
    }

    /**
     * Parses a line from rides.txt into a Ride object.
     */
    public static Ride fromString(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        // Split into at most 8 parts (the last part may contain commas)
        String[] parts = line.split("\\|", -1);
        if (parts.length < 8) {
            throw new IllegalArgumentException("Invalid ride record: " + line);
        }
        String rideId     = parts[0];
        String origin     = parts[1];
        String destination= parts[2];
        String datetime   = parts[3];
        int totalSeats    = Integer.parseInt(parts[4]);
        String createdBy  = parts[5];
        String status     = parts[6];
        String usersPart  = parts[7];

        List<String> usersList = new ArrayList<>();
        if (!usersPart.isEmpty()) {
            usersList = new ArrayList<>(Arrays.asList(usersPart.split(","))); // Split by comma into list
        }
        return new Ride(rideId, origin, destination, datetime, totalSeats, createdBy, status, usersList);
    }
}

