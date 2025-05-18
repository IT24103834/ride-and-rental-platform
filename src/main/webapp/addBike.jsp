<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Bike</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .hero-section {
            position: relative;
            height: 120px;
            overflow: hidden;
            border-radius: 15px;
            margin-bottom: 15px;
        }

        .hero-image {
            width: 100%;
            height: 100%;
            object-fit: cover;
            filter: brightness(0.7);
        }

        .hero-content {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            text-align: center;
            color: white;
            z-index: 1;
            width: 100%;
            padding: 0 20px;
        }

        .hero-title {
            font-size: 1.5rem;
            font-weight: 600;
            margin-bottom: 0.3rem;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.3);
        }

        .hero-subtitle {
            font-size: 1rem;
            opacity: 0.9;
            text-shadow: 1px 1px 2px rgba(0,0,0,0.3);
            margin-bottom: 0.3rem;
        }

        .form-card {
            background: white;
            border-radius: 12px;
            padding: 1.2rem 1rem;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            position: relative;
            z-index: 1;
            max-width: 900px;
            width: 100%;
            margin: 2rem auto;
        }

        .form-header {
            text-align: center;
            margin-bottom: 30px;
        }

        .form-header i {
            font-size: 2rem;
            color: #3498db;
            margin-bottom: 15px;
        }

        .input-group {
            position: relative;
            margin-bottom: 0.7rem;
        }

        .input-group i {
            position: absolute;
            left: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: #666;
            z-index: 2;
            pointer-events: none;
        }

        .input-group input,
        .input-group select {
            width: 100%;
            padding: 12px 45px;
            border: 1px solid #ddd;
            border-radius: 8px;
            font-size: 1rem;
            background-color: white;
            color: #333;
        }

        /* Specific styles for select element */
        .input-group select {
            appearance: none;
            -webkit-appearance: none;
            -moz-appearance: none;
            background-image: url("data:image/svg+xml;charset=UTF-8,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='none' stroke='%23666666' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'%3e%3cpolyline points='6 9 12 15 18 9'%3e%3c/polyline%3e%3c/svg%3e");
            background-repeat: no-repeat;
            background-position: right 15px center;
            background-size: 15px;
            padding-right: 45px;
        }

        .input-group select::-ms-expand {
            display: none;
        }

        .input-group select:focus {
            outline: none;
            border-color: #3498db;
        }

        .input-group input:focus {
            outline: none;
            border-color: #3498db;
        }

        .submit-btn {
            width: 100%;
            padding: 0.5rem 0;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 7px;
            font-size: 0.98rem;
            cursor: pointer;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-top: 20px;
            transition: background-color 0.3s ease;
        }

        .submit-btn:hover {
            background-color: #2980b9;
        }

        .submit-btn i {
            margin-right: 8px;
        }

        .back-link {
            text-align: center;
            margin-top: 20px;
            padding: 10px;
        }

        .back-link a {
            color: #666;
            text-decoration: none;
            display: inline-flex;
            align-items: center;
            gap: 5px;
            transition: color 0.3s ease;
        }

        .back-link a:hover {
            color: #3498db;
        }

        /* Container max-width to prevent stretching on large screens */
        .container {
            max-width: 800px;
            margin: 1.5rem auto;
            padding: 1.2rem 1rem 1.2rem 1rem;
            border-radius: 18px;
        }

        .home-btn {
            display: block;
            width: 100%;
            text-align: center;
            background: #2ecc71;
            color: #fff;
            padding: 0.5rem 0;
            border-radius: 7px;
            font-size: 0.98rem;
            font-weight: 600;
            text-decoration: none;
            margin-top: 1rem;
            transition: background 0.2s;
        }
        .home-btn:hover {
            background: #27ae60;
        }

        body {
            min-height: 100vh;
            background: linear-gradient(
                    rgba(44, 62, 80, 0.85),
                    rgba(52, 73, 94, 0.85)
            ), url('images/Bike.jpg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Poppins', sans-serif;
        }
    </style>
</head>
<body>
<div class="form-card">
    <div class="form-header">
        <i class="fas fa-bicycle"></i>
        <h2>Bike Details</h2>
    </div>

    <form action="bikes" method="post" class="bike-form">
        <div class="input-group">
            <i class="fas fa-tag"></i>
            <input type="text" id="model" name="model" placeholder="Enter bike model" required>
        </div>

        <div class="input-group">
            <i class="fas fa-bicycle"></i>
            <select id="type" name="type" required>
                <option value="">Select bike type</option>
                <option value="Manual">Manual Bike</option>
                <option value="Automatic">Automatic Bike</option>
            </select>
        </div>

        <div class="input-group">
            <i class="fas fa-hashtag"></i>
            <input type="number" id="availability" name="availability" min="1"
                   placeholder="Number of bikes available" required>
        </div>

        <button type="submit" class="submit-btn">
            <i class="fas fa-plus-circle"></i>
            Add Bike
        </button>
    </form>

    <a href="index.jsp" class="home-btn">Back to Home</a>

    <div class="back-link">
        <a href="bikes">
            <i class="fas fa-arrow-left"></i>
            Back to Bike List
        </a>
    </div>
</div>
</body>
</html>
