<%@ page import="java.util.*, com.platform.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Bike List</title>
  <link rel="stylesheet" href="css/style.css">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
  <style>
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
    .container {
      padding: 20px;
      background: rgba(255, 255, 255, 0.85);
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
      margin: 20px auto;
      max-width: 1000px;
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
    }
    .add-bike-btn:hover {
      background-color: #218838;
    }
    .home-btn {
      display: inline-block;
      width: auto;
      min-width: 120px;
      text-align: center;
      background: #2ecc71;
      color: #fff;
      padding: 0.4rem 1.2rem;
      border-radius: 6px;
      font-size: 0.92rem;
      font-weight: 600;
      text-decoration: none;
      margin: 1rem auto 0 auto;
      transition: background 0.2s;
    }
    .home-btn:hover {
      background: #27ae60;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="header">
    <h2>Available Bikes</h2>
    <a href="addBike.jsp" class="add-bike-btn">Add New Bike</a>
  </div>

  <%
    List<Bike> bikes = (List<Bike>) request.getAttribute("bikes");
    if (bikes == null || bikes.isEmpty()) {
  %>
  <p>No bikes available.</p>
  <%
  } else {
  %>
  <table class="bike-table">
    <tr>
      <th>Model</th>
      <th>Type</th>
      <th>Availability</th>
      <th>Actions</th>
    </tr>
    <%
      for (Bike bike : bikes) {
    %>
    <tr>
      <td><%= bike.getModel() %></td>
      <td><%= bike.getType() %></td>
      <td><%= bike.getAvailability() %></td>
      <td>
        <form action="bikes" method="post" style="display: inline;">
          <input type="hidden" name="action" value="delete">
          <input type="hidden" name="model" value="<%= bike.getModel() %>">
          <button type="submit" class="delete-btn">Delete</button>
        </form>
      </td>
    </tr>
    <%
      }
    %>
  </table>
  <%
    }
  %>
</div>
<a href="index.jsp" class="home-btn"><i class="fas fa-home"></i> Go to Home Page</a>
</body>
</html>
