<%@ page import="java.util.*, com.platform.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Search Bikes</title>
  <link rel="stylesheet" href="css/style.css">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
  <style>
    body {
      min-height: 100vh;
      background: #f6f8fb;
      display: flex;
      align-items: center;
      justify-content: center;
      font-family: 'Poppins', sans-serif;
    }
    .form-container {
      background: #fff;
      border-radius: 32px;
      box-shadow: 0 8px 32px rgba(44,62,80,0.08);
      padding: 3.5rem 3rem 2.5rem 3rem;
      max-width: 700px;
      width: 100%;
      margin: 2.5rem auto;
    }
    .form-title {
      text-align: center;
      font-size: 2.6rem;
      font-weight: 800;
      color: #2c3e50;
      margin-bottom: 2.2rem;
      letter-spacing: 0.5px;
    }
    .form-title i {
      font-size: 2.8rem;
      color: #2c3e50;
      margin-right: 0.7rem;
    }
    .form-group label {
      color: #2c3e50;
      font-weight: 600;
      margin-bottom: 0.7rem;
      display: block;
      font-size: 1.25rem;
    }
    .search-input-group input[type="text"] {
      width: 100%;
      padding: 1.2rem 1.2rem;
      border: 2px solid #e5e7eb;
      border-radius: 14px;
      font-size: 1.25rem;
      background: #fafbfc;
      margin-bottom: 1.5rem;
      transition: border-color 0.3s;
    }
    .search-input-group input[type="text"]:focus {
      border-color: #3498db;
      outline: none;
    }
    .btn-primary {
      width: 100%;
      background: #5da6e9;
      color: #fff;
      padding: 1.2rem 0;
      border: none;
      border-radius: 18px;
      font-size: 1.35rem;
      font-weight: 600;
      cursor: pointer;
      margin-top: 0.5rem;
      transition: background 0.2s;
      display: flex;
      align-items: center;
      justify-content: center;
      gap: 0.7rem;
    }
    .btn-primary:hover {
      background: #217dbb;
    }
    .back-link {
      display: flex;
      justify-content: center;
      margin-top: 2.2rem;
      font-size: 1.25rem;
      font-weight: 600;
    }
    .back-link a {
      color: #8a8a8a;
      text-decoration: none;
      font-weight: 600;
      font-size: 1.25rem;
      transition: color 0.2s;
      display: flex;
      align-items: center;
      gap: 0.5rem;
    }
    .back-link a:hover {
      color: #217dbb;
      text-decoration: underline;
    }
    .container.search-results {
      margin-top: 2rem;
      background: #fff;
      border-radius: 16px;
      box-shadow: 0 4px 16px rgba(0,0,0,0.07);
      padding: 1.5rem;
    }
    .header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 20px;
    }
    .add-bike-btn {
      background-color: #28a745;
      color: white;
      text-decoration: none;
      padding: 10px 20px;
      border-radius: 5px;
      font-weight: 500;
    }
    .add-bike-btn:hover {
      background-color: #218838;
    }
    .bike-table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
      background: rgba(255, 255, 255, 0.9);
    }
    .bike-table th, .bike-table td {
      padding: 12px;
      text-align: left;
      border: 1px solid #ddd;
    }
    .bike-table th {
      background-color: #007bff;
      color: white;
    }
    .bike-table tr:hover {
      background-color: #f5f5f5;
    }
    .delete-btn {
      background-color: #dc3545;
      color: white;
      border: none;
      padding: 5px 10px;
      border-radius: 4px;
      cursor: pointer;
    }
    .delete-btn:hover {
      background-color: #c82333;
    }
    .no-results {
      text-align: center;
      color: #888;
      margin-top: 2rem;
    }
    .no-results i {
      font-size: 2rem;
      margin-bottom: 0.5rem;
      color: #3498db;
    }
  </style>
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
