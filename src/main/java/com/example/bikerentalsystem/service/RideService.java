package com.example.bikerentalsystem.service;
import org.springframework.stereotype.Service;
import com.example.bikerentalsystem.model.Ride;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RideService {
    private final String FILE_PATH = "src/main/resources/data/rides.txt";

    public void saveRide(Ride ride) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(String.format("%s,%s,%s,%s,%s\n",
                    ride.getName(),
                    ride.getContact(),
                    ride.getOrigin(),
                    ride.getDestination(),
                    ride.getTravelDate()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save ride", e);
        }
    }
    public List<Ride> getAllRides() {
        List<Ride> rides = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length == 5) {
                    Ride ride = new Ride();
                    ride.setId(index++); // store line number
                    ride.setName(parts[0]);
                    ride.setContact(parts[1]);
                    ride.setOrigin(parts[2]);
                    ride.setDestination(parts[3]);
                    ride.setTravelDate(LocalDate.parse(parts[4]));
                    rides.add(ride);
                }
            }
        } catch (IOException e) {
        }
        return rides;
    }
    public void updateRide(int id, Ride updatedRide) {
        List<String> allLines;
        try {
            allLines = new ArrayList<>(Files.readAllLines(Paths.get(FILE_PATH)));
            if (id >= 0 && id < allLines.size()) {
                String updatedLine = String.format("%s,%s,%s,%s,%s",
                        updatedRide.getName(),
                        updatedRide.getContact(),
                        updatedRide.getOrigin(),
                        updatedRide.getDestination(),
                        updatedRide.getTravelDate());
                allLines.set(id, updatedLine);
                Files.write(Paths.get(FILE_PATH), allLines);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to update ride", e);
        }
    }
    public void deleteRide(int id) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            if (id >= 0 && id < lines.size()) {
                lines.remove(id);
                Files.write(Paths.get(FILE_PATH), lines);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete ride", e);
        }
    }
    public List<Ride> searchRidesByLocation(String location) {
        return getAllRides().stream()
                .filter(ride -> ride.getOrigin().toLowerCase().contains(location.toLowerCase()) ||
                        ride.getDestination().toLowerCase().contains(location.toLowerCase()))
                .collect(Collectors.toList());
    }
}
