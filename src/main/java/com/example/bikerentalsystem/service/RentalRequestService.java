package com.example.bikerentalsystem.service;

import com.example.bikerentalsystem.model.Bike;
import com.example.bikerentalsystem.model.RentalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Service
public class RentalRequestService {
    private final String filePath = "src/main/resources/data/rental_requests.txt";
    private final Queue<RentalRequest> requestQueue;
    @Autowired
    private BikeService bikeService;

    public RentalRequestService() {
        this.requestQueue = new ArrayDeque<>();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        loadQueueFromFile();
    }

    // Create: Add a rental request to the queue
    public void addRequest(RentalRequest request) {
        List<RentalRequest> allRequests = getAllRequests();
        request.setId(generateId(allRequests));
        request.setStatus("Pending");
        requestQueue.add(request);
        allRequests.add(request); // Add to in-memory list
        saveQueueToFile(allRequests);
        processQueue();
    }

    // Read: Get all requests
    public List<RentalRequest> getAllRequests() {
        List<RentalRequest> allRequests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    Long id = Long.parseLong(data[0]);
                    String userName = data[1];
                    String bikeType = data[2];
                    String status = data[3];
                    RentalRequest request = new RentalRequest(id, userName, bikeType, status);
                    allRequests.add(request);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Remove duplicates by ID
        List<RentalRequest> uniqueRequests = new ArrayList<>();
        for (RentalRequest request : allRequests) {
            if (!uniqueRequests.stream().anyMatch(r -> r.getId().equals(request.getId()))) {
                uniqueRequests.add(request);
            }
        }
        return uniqueRequests;
    }

    // Update: Update an existing request
    public void updateRequest(RentalRequest updatedRequest) {
        List<RentalRequest> allRequests = getAllRequests();
        boolean found = false;
        RentalRequest oldRequest = null;
        for (int i = 0; i < allRequests.size(); i++) {
            if (allRequests.get(i).getId().equals(updatedRequest.getId())) {
                oldRequest = allRequests.get(i);
                allRequests.set(i, updatedRequest);
                found = true;
                break;
            }
        }
        if (found) {
            // Handle status change effects
            if ("Cancelled".equals(updatedRequest.getStatus()) && "Assigned".equals(oldRequest.getStatus())) {
                // Revert bike availability
                List<Bike> bikes = bikeService.getAllBikes(null);
                Bike bikeToUpdate = bikes.stream()
                        .filter(bike -> bike.getType().equals(updatedRequest.getBikeType()))
                        .findFirst()
                        .orElse(null);
                if (bikeToUpdate != null) {
                    bikeToUpdate.setAvailabilityCount(bikeToUpdate.getAvailabilityCount() + 1);
                    bikeService.updateBike(bikeToUpdate);
                }
            } else if ("Assigned".equals(updatedRequest.getStatus()) && !"Assigned".equals(oldRequest.getStatus())) {
                // Assign bike if status changes to Assigned
                if (!assignBike(updatedRequest)) {
                    updatedRequest.setStatus(oldRequest.getStatus()); // Revert if no bike available
                }
            }

            // Rebuild the queue with only Pending requests
            requestQueue.clear();
            allRequests.stream()
                    .filter(r -> "Pending".equals(r.getStatus()))
                    .forEach(requestQueue::add);
            saveQueueToFile(allRequests);
        }
    }

    // Delete: Remove a request
    public void deleteRequest(Long id) {
        List<RentalRequest> allRequests = getAllRequests();
        RentalRequest requestToDelete = allRequests.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (requestToDelete != null) {
            if ("Assigned".equals(requestToDelete.getStatus())) {
                // Revert bike availability
                List<Bike> bikes = bikeService.getAllBikes(null);
                Bike bikeToUpdate = bikes.stream()
                        .filter(bike -> bike.getType().equals(requestToDelete.getBikeType()))
                        .findFirst()
                        .orElse(null);
                if (bikeToUpdate != null) {
                    bikeToUpdate.setAvailabilityCount(bikeToUpdate.getAvailabilityCount() + 1);
                    bikeService.updateBike(bikeToUpdate);
                }
            }
            // Remove from allRequests and requestQueue
            allRequests.removeIf(request -> request.getId().equals(id));
            requestQueue.removeIf(request -> request.getId().equals(id));
            // Save the updated list to file
            saveQueueToFile(allRequests);
        }
    }

    // Process the queue and assign bikes if available
    public void processQueue() {
        List<RentalRequest> allRequests = getAllRequests();
        for (RentalRequest request : allRequests) {
            if ("Pending".equals(request.getStatus())) {
                boolean assigned = assignBike(request);
                if (assigned) {
                    request.setStatus("Assigned");
                    updateRequest(request);
                }
            }
        }
    }

    // Abstracted bike assignment logic
    private boolean assignBike(RentalRequest request) {
        List<Bike> availableBikes = bikeService.getAllBikes(null).stream()
                .filter(bike -> bike.getType().equals(request.getBikeType()) && bike.getAvailabilityCount() > 0)
                .toList();

        if (!availableBikes.isEmpty()) {
            Bike bike = availableBikes.get(0);
            bike.setAvailabilityCount(bike.getAvailabilityCount() - 1);
            bikeService.updateBike(bike);
            return true;
        }
        return false;
    }

    // Load queue from file on startup
    private void loadQueueFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    Long id = Long.parseLong(data[0]);
                    String userName = data[1];
                    String bikeType = data[2];
                    String status = data[3];
                    RentalRequest request = new RentalRequest(id, userName, bikeType, status);
                    if ("Pending".equals(status)) {
                        requestQueue.add(request);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save queue state to file
    private void saveQueueToFile(List<RentalRequest> requests) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) { // false to overwrite
            for (RentalRequest request : requests) {
                writer.write(request.getId() + "," + request.getUserName() + "," +
                        request.getBikeType() + "," + request.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to rental_requests.txt: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Generate a unique ID for new requests
    private Long generateId(List<RentalRequest> requests) {
        return requests.stream().map(RentalRequest::getId).max(Long::compare).orElse(0L) + 1;
    }
}