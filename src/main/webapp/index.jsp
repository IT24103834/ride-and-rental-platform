<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Bike Rental Platform</title>
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: 'Poppins', sans-serif;
    }

    body {
      min-height: 100vh;
      background: linear-gradient(
              rgba(241, 246, 249, 0.7),
              rgba(229, 238, 245, 0.7)
      ),
      url('images/image.png');
      background-size: cover;
      background-position: center;
      background-attachment: fixed;
      padding: 2rem;
      display: flex;
      flex-direction: column;
      align-items: center;
    }

    .container {
      max-width: 1200px;
      width: 100%;
      margin: 0 auto;
      backdrop-filter: blur(10px);
      background: rgba(255, 255, 255, 0.8);
      padding: 2rem;
      border-radius: 20px;
      box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
    }

    .header {
      text-align: center;
      margin-bottom: 3rem;
      color: #2c3e50;
      padding: 2rem;
      border-radius: 15px;
      background: rgba(255, 255, 255, 0.9);
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
    }

    .header-title {
      display: flex;
      align-items: center;
      justify-content: center;
      gap: 1rem;
      margin-bottom: 1.5rem;
    }

    .header-title i {
      font-size: 2.5rem;
      color: #3498db;
    }

    .header h1 {
      font-size: 2.5rem;
      font-weight: 600;
      color: #2c3e50;
    }

    .header p {
      font-size: 1.2rem;
      color: #666;
      max-width: 800px;
      margin: 0 auto;
    }

    .cards-container {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
      gap: 2rem;
      padding: 1rem;
    }

    .card {
      background: rgba(255, 255, 255, 0.95);
      border-radius: 15px;
      padding: 2rem;
      text-align: center;
      transition: all 0.3s ease;
      cursor: pointer;
      text-decoration: none;
      color: inherit;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      border: 1px solid rgba(255, 255, 255, 0.2);
    }

    .card:hover {
      transform: translateY(-5px);
      box-shadow: 0 8px 15px rgba(0, 0, 0, 0.1);
      background: rgba(255, 255, 255, 1);
    }

    .card i {
      font-size: 2rem;
      color: #3498db;
      margin-bottom: 1.5rem;
      transition: transform 0.3s ease;
    }

    .card:hover i {
      transform: scale(1.1);
    }

    .card h3 {
      font-size: 1.5rem;
      color: #2c3e50;
      margin-bottom: 1rem;
    }

    .card p {
      color: #666;
      line-height: 1.6;
    }

    @media (max-width: 768px) {
      body {
        padding: 1rem;
      }

      .container {
        padding: 1rem;
      }

      .header {
        padding: 1rem;
      }

      .header h1 {
        font-size: 2rem;
      }

      .header p {
        font-size: 1rem;
      }

      .cards-container {
        grid-template-columns: 1fr;
      }
    }
  </style>
</head>
<body>
<div class="container">
  <header class="header">
    <div class="header-title">
      <i class="fas fa-bicycle"></i>
      <h1>Welcome to Bike Rental Platform</h1>
    </div>
    <p>Your one-stop solution for bike rentals. Choose from our wide selection of manual and Automatic bikes.</p>
  </header>

  <div class="cards-container">
    <a href="bikes" class="card">
      <i class="fas fa-list"></i>
      <h3>View All Bikes</h3>
      <p>Browse our complete collection of available bikes</p>
    </a>

    <a href="addBike.jsp" class="card">
      <i class="fas fa-plus-circle"></i>
      <h3>Add New Bike</h3>
      <p>Add a new bike to the rental platform</p>
    </a>

    <a href="searchBike.jsp" class="card">
      <i class="fas fa-search"></i>
      <h3>Search Bikes</h3>
      <p>Find specific bikes by model or type</p>
    </a>
  </div>
</div>
</body>
</html>