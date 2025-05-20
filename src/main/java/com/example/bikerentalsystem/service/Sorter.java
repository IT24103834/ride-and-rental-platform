package com.example.bikerentalsystem.service;

import com.example.bikerentalsystem.model.Bike;

import java.util.List;

public class Sorter {
    // Sort bikes by the specified criterion (availability or rating)
    public void sortBikes(List<Bike> bikes, String criterion) {
        if (bikes == null || bikes.isEmpty()) {
            return;
        }
        quickSort(bikes, 0, bikes.size() - 1, criterion);
    }

    // Quick Sort implementation
    private void quickSort(List<Bike> bikes, int low, int high, String criterion) {
        if (low < high) {
            int pi = partition(bikes, low, high, criterion);
            quickSort(bikes, low, pi - 1, criterion);
            quickSort(bikes, pi + 1, high, criterion);
        }
    }

    // Partition method for Quick Sort
    private int partition(List<Bike> bikes, int low, int high, String criterion) {
        Bike pivot = bikes.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            boolean shouldSwap;
            if ("availability".equalsIgnoreCase(criterion)) {
                shouldSwap = bikes.get(j).getAvailabilityCount() >= pivot.getAvailabilityCount();
            } else {
                shouldSwap = bikes.get(j).getRating() >= pivot.getRating();
            }

            if (shouldSwap) {
                i++;
                swap(bikes, i, j);
            }
        }
        swap(bikes, i + 1, high);
        return i + 1;
    }

    // Swap method for Quick Sort
    private void swap(List<Bike> bikes, int i, int j) {
        Bike temp = bikes.get(i);
        bikes.set(i, bikes.get(j));
        bikes.set(j, temp);
    }
}