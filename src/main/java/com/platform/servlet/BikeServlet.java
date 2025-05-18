package com.platform.servlet;

import com.platform.model.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/bikes")
public class BikeServlet extends HttpServlet {
    private static final String FILE_PATH = "/bikes.txt";

    private List<Bike> loadBikes(HttpServletRequest request) throws IOException {
        List<Bike> bikes = new ArrayList<>();
        String fullPath = getServletContext().getRealPath(FILE_PATH);
        File file = new File(fullPath);

        // Create the file if it doesn't exist
        if (!file.exists()) {
            file.createNewFile();
            return bikes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fullPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String id = parts[0];
                    String model = parts[1];
                    String type = parts[2];
                    int count = Integer.parseInt(parts[3]);

                    Bike bike;
                    if ("Electric".equalsIgnoreCase(type)) {
                        bike = new AutomaticBike(id, model, count);
                    } else {
                        bike = new ManualBike(id, model, count);
                    }
                    bikes.add(bike);
                }
            }
        }
        return bikes;
    }

    private void saveBikes(List<Bike> bikes, HttpServletRequest request) throws IOException {
        String fullPath = getServletContext().getRealPath(FILE_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath))) {
            for (Bike bike : bikes) {
                writer.write(String.format("%s,%s,%s,%d%n",
                        bike.getId(),
                        bike.getModel(),
                        bike.getType(),
                        bike.getAvailability()
                ));
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("search".equals(action)) {
            // Handle search
            String searchTerm = req.getParameter("searchTerm");
            List<Bike> allBikes = loadBikes(req);
            List<Bike> searchResults = allBikes.stream()
                    .filter(bike ->
                            bike.getModel().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    bike.getType().toLowerCase().contains(searchTerm.toLowerCase()))
                    .collect(Collectors.toList());

            req.setAttribute("searchResults", searchResults);
            req.getRequestDispatcher("searchBike.jsp").forward(req, resp);
        } else {
            // Show all bikes
            List<Bike> bikes = loadBikes(req);
            req.setAttribute("bikes", bikes);
            req.getRequestDispatcher("listBike.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            // Handle delete action
            String modelToDelete = req.getParameter("model");
            List<Bike> bikes = loadBikes(req);
            bikes.removeIf(bike -> bike.getModel().equals(modelToDelete));
            saveBikes(bikes, req);
        } else {
            // Handle add action
            String model = req.getParameter("model");
            String type = req.getParameter("type");
            int availability = Integer.parseInt(req.getParameter("availability"));

            // Generate a unique ID
            String id = UUID.randomUUID().toString().substring(0, 8);

            List<Bike> bikes = loadBikes(req);
            Bike newBike;
            if ("Electric".equalsIgnoreCase(type)) {
                newBike = new AutomaticBike(id, model, availability);
            } else {
                newBike = new ManualBike(id, model, availability);
            }
            bikes.add(newBike);
            saveBikes(bikes, req);
        }

        // Redirect to prevent form resubmission
        resp.sendRedirect("bikes");
    }
}