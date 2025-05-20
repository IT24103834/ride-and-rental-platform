package com.example.bikerentalsystem.service;
import com.example.bikerentalsystem.model.User;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final String FILE_PATH = "src/main/resources/data/users.txt";
    public void createUser(User user) throws IOException {
        List<User> users = getAllUsers();
        int newId = users.stream().mapToInt(User::getId).max().orElse(0) + 1;
        user.setId(newId);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(formatUser(user));
            writer.newLine();
        }
    }
    public User authenticate(String email, String password) throws IOException {
        return getAllUsers().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email) &&
                        user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
    public boolean emailExists(String email) throws IOException {
        return getAllUsers().stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }
    public List<User> getAllUsers() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();

        return Files.readAllLines(Paths.get(FILE_PATH)).stream()
                .filter(line -> !line.isBlank())
                .map(this::parseUser)
                .collect(Collectors.toList());
    }
    public boolean updateUser(User updatedUser) throws IOException {
        List<User> users = getAllUsers();
        boolean updated = false;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == updatedUser.getId()) {
                users.set(i, updatedUser);
                updated = true;
                break;
            }
        }
        if (updated) {
            overwriteFile(users);
        }
        return updated;
    }
    public boolean deleteUser(int userId) throws IOException {
        List<User> users = getAllUsers();
        boolean removed = users.removeIf(user -> user.getId() == userId);

        if (removed) {
            overwriteFile(users);
        }

        return removed;
    }
    public boolean deleteProfile(int userId) throws IOException {
        List<User> users = getAllUsers();
        boolean removed = users.removeIf(u -> u.getId() == userId);

        if (removed) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
                for (User user : users) {
                    writer.write(formatUser(user));
                    writer.newLine();
                }
            }
        }
        return removed;
    }
    private String formatUser(User user) {
        return String.join(",",
                String.valueOf(user.getId()),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getTown(),
                user.getPassword()
        );
    }
    private User parseUser(String line) {
        String[] parts = line.split(",");
        User user = new User();
        user.setId(Integer.parseInt(parts[0].trim()));
        user.setFirstName(parts[1].trim());
        user.setLastName(parts[2].trim());
        user.setEmail(parts[3].trim());
        user.setTown(parts[4].trim());
        user.setPassword(parts[5].trim());
        return user;
    }
    private void overwriteFile(List<User> users) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (User user : users) {
                writer.write(formatUser(user));
                writer.newLine();
            }
        }
    }

}
