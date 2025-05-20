package com.example.bikerentalsystem.service;

import com.example.bikerentalsystem.model.Bike;
import com.example.bikerentalsystem.model.ElectricBike;
import com.example.bikerentalsystem.model.ManualBike;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BikeService {
    private final String filePath = "src/main/resources/data/bikes.txt";
    private final Sorter sorter;

    public BikeService() {
        this.sorter = new Sorter();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addBike(Bike bike) {
        List<Bike> bikes = getAllBikes(null);
        bike.setId(generateId(bikes));
        bikes.add(bike);
        saveBikes(bikes);
    }

    public List<Bike> getAllBikes(String sortBy) {
        List<Bike> bikes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    Long id = Long.parseLong(data[0]);
                    String type = data[1];
                    String model = data[2];
                    int availabilityCount = Integer.parseInt(data[3]);
                    double rating = Double.parseDouble(data[4]);
                    Bike bike;
                    if (type.equals("Electric")) {
                        bike = new ElectricBike(id, model, availabilityCount, rating);
                    } else {
                        bike = new ManualBike(id, model, availabilityCount, rating);
                    }
                    bikes.add(bike);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sort bikes if a sorting criterion is provided
        if (sortBy != null && !sortBy.isEmpty()) {
            sorter.sortBikes(bikes, sortBy);
        }

        return bikes;
    }

    public Optional<Bike> getBikeById(Long id) {
        return getAllBikes(null).stream()
                .filter(bike -> bike.getId().equals(id))
                .findFirst();
    }

    public List<Bike> searchBikes(String query, String sortBy) {
        String lowerQuery = query.toLowerCase();
        List<Bike> bikes = getAllBikes(sortBy);
        return bikes.stream()
                .filter(bike -> bike.getModel().toLowerCase().contains(lowerQuery) || bike.getType().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    public void updateBike(Bike updatedBike) {
        List<Bike> bikes = getAllBikes(null);
        bikes.removeIf(bike -> bike.getId().equals(updatedBike.getId()));
        bikes.add(updatedBike);
        saveBikes(bikes);
    }

    public void deleteBike(Long id) {
        List<Bike> bikes = getAllBikes(null);
        bikes.removeIf(bike -> bike.getId().equals(id));
        saveBikes(bikes);
    }

    private void saveBikes(List<Bike> bikes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Bike bike : bikes) {
                writer.write(bike.getId() + "," + bike.getType() + "," + bike.getModel() + "," +
                        bike.getAvailabilityCount() + "," + bike.getRating());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Long generateId(List<Bike> bikes) {
        return bikes.stream().map(Bike::getId).max(Long::compare).orElse(0L) + 1;
    }
}