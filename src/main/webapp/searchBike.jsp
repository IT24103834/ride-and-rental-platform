<%@ page import="java.util.*, com.platform.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Search Bikes</title>
  <link rel="stylesheet" href="css/style.css">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
<div class="form-container">
  <h1 class="form-title">
    <i class="fas fa-search"></i>
    Search Bikes
  </h1>
  <form action="bikes" method="get" class="bike-form">
    <input type="hidden" name="action" value="search">
    <div class="form-group">
      <label for="searchTerm">Search by Model or Type:</label>
      <div class="search-input-group">
        <input type="text" id="searchTerm" name="searchTerm"
               placeholder="Enter model or type" required>
      </div>
    </div>
    <button type="submit" class="btn-primary">
      <i class="fas fa-search"></i>
      Search
    </button>
  </form>
  <div class="back-link">
    <a href="bikes">
      <i class="fas fa-arrow-left"></i>
      Back to Bike List
    </a>
  </div>
</div>

<!-- Search Results Section -->
<%
  if (request.getAttribute("searchResults") != null) {
    List<Bike> searchResults = (List<Bike>) request.getAttribute("searchResults");
    if (!searchResults.isEmpty()) {
%>
<div class="container search-results">
  <div class="header">
    <h2>
      <i class="fas fa-search"></i>
      Search Results
    </h2>
    <a href="addBike.jsp" class="add-bike-btn">
      <i class="fas fa-plus"></i>
      Add New Bike
    </a>
  </div>
  <table class="bike-table">
    <tr>
      <th>Model</th>
      <th>Type</th>
      <th>Availability</th>
      <th>Actions</th>
    </tr>
    <% for (Bike bike : searchResults) { %>
    <tr>
      <td><%= bike.getModel() %></td>
      <td>
                        <span class="bike-type <%= bike.getType().toLowerCase() %>">
                            <i class="fas <%= bike.getType().equalsIgnoreCase("Electric") ? "fa-bolt" : "fa-bicycle" %>"></i>
                            <%= bike.getType() %>
                        </span>
      </td>
      <td><%= bike.getAvailability() %></td>
      <td>
        <form action="bikes" method="post" style="display: inline;">
          <input type="hidden" name="action" value="delete">
          <input type="hidden" name="model" value="<%= bike.getModel() %>">
          <button type="submit" class="delete-btn">
            <i class="fas fa-trash"></i>
            Delete
          </button>
        </form>
      </td>
    </tr>
    <% } %>
  </table>
</div>
<%
} else {
%>
<div class="container">
  <div class="no-results">
    <i class="fas fa-search"></i>
    <p>No bikes found matching your search.</p>
  </div>
</div>
<%
    }
  }
%>
</body>
</html>
