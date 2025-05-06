package com.platform.bikerentalridesharingplatform.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class RideUtils {
    // Converts a list of user IDs into a comma-separated string
    public static String joinUserIds(List<String> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return "";
        }
        return String.join(",", userIds); // Uses String.join (Java 8+):contentReference[oaicite:10]{index=10}
    }

    // Parses a comma-separated string of user IDs into a List<String>
    public static List<String> splitUserIds(String userIdsStr) {
        List<String> result = new ArrayList<>();
        if (userIdsStr == null || userIdsStr.isEmpty()) {
            return result;
        }
        String[] parts = userIdsStr.split(","); // Split by comma:contentReference[oaicite:11]{index=11}
        result.addAll(Arrays.asList(parts));
        return result;
    }
}

