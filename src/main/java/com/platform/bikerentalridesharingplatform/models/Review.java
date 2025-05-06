package com.platform.bikerentalridesharingplatform.models;

public class Review {
    private String reviewId;
    private String targetType;
    private String targetId;
    private String userId;
    private int rating;
    private String timestamp;
    private String comment;

    // Default constructor
    public Review() {}

    // Full constructor
    public Review(String reviewId, String targetType, String targetId,
                  String userId, int rating, String timestamp, String comment) {
        this.reviewId   = reviewId;
        this.targetType = targetType;
        this.targetId   = targetId;
        this.userId     = userId;
        this.rating     = rating;
        this.timestamp  = timestamp;
        this.comment    = comment;
    }

    // Getters and setters
    public String getReviewId() { return reviewId; }
    public void setReviewId(String reviewId) { this.reviewId = reviewId; }

    public String getTargetType() { return targetType; }
    public void setTargetType(String targetType) { this.targetType = targetType; }

    public String getTargetId() { return targetId; }
    public void setTargetId(String targetId) { this.targetId = targetId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    /**
     * Serializes this Review into a pipe-delimited string.
     * Format: reviewId|targetType|targetId|userId|rating|timestamp|comment
     */
    @Override
    public String toString() {
        return String.join("|",
                reviewId,
                targetType,
                targetId,
                userId,
                String.valueOf(rating),
                timestamp,
                comment
        );
    }

    /**
     * Parses a line from reviews.txt into a Review object.
     */
    public static Review fromString(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        String[] parts = line.split("\\|", -1);
        if (parts.length < 7) {
            throw new IllegalArgumentException("Invalid review record: " + line);
        }
        String reviewId   = parts[0];
        String targetType = parts[1];
        String targetId   = parts[2];
        String userId     = parts[3];
        int rating        = Integer.parseInt(parts[4]);
        String timestamp  = parts[5];
        String comment    = parts[6];
        return new Review(reviewId, targetType, targetId, userId, rating, timestamp, comment);
    }
}

