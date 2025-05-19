<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Item</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Roboto', sans-serif;
        }
        body {
            background-color: #f5f5f5;
            color: #333;
            line-height: 1.6;
        }
        .navbar {
            background-color: #000;
            padding: 1rem 0;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .container {
            width: 85%;
            margin: 0 auto;
        }
        .navbar-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .navbar-brand {
            color: #fff;
            font-size: 1.5rem;
            font-weight: 500;
            text-decoration: none;
            letter-spacing: 1px;
        }
        .navbar-nav {
            display: flex;
            list-style: none;
        }
        .nav-item {
            margin-left: 1.5rem;
        }
        .nav-link {
            color: #ccc;
            text-decoration: none;
            font-weight: 400;
            transition: color 0.3s;
        }
        .nav-link:hover {
            color: #fff;
        }
        .content {
            padding: 3rem 0;
        }
        .card {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 15px rgba(0,0,0,0.05);
            overflow: hidden;
            max-width: 700px;
            margin: 0 auto;
        }
        .card-header {
            background-color: #000;
            color: #fff;
            padding: 1.5rem;
        }
        .card-title {
            font-weight: 400;
            margin: 0;
            font-size: 1.5rem;
        }
        .card-body {
            padding: 2rem;
        }
        .form-group {
            margin-bottom: 1.5rem;
        }
        label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: 500;
            color: #555;
        }
        .form-control {
            width: 100%;
            padding: 0.8rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
            transition: border-color 0.3s;
        }
        .form-control:focus {
            border-color: #000;
            outline: none;
        }
        .btn {
            padding: 0.8rem 1.5rem;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: 500;
            transition: all 0.3s;
            font-size: 1rem;
        }
        .btn-primary {
            background-color: #000;
            color: #fff;
        }
        .btn-secondary {
            background-color: #fff;
            color: #000;
            border: 1px solid #000;
            margin-right: 1rem;
        }
        .btn-primary:hover {
            background-color: #333;
        }
        .btn-secondary:hover {
            background-color: #f5f5f5;
        }
        .form-actions {
            display: flex;
            justify-content: flex-start;
            margin-top: 1rem;
        }
    </style>
</head>
<body>
    <nav class="navbar">
        <div class="container navbar-container">
            <a class="navbar-brand" href="#">Inventory System</a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="">Dashboard</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="list">Inventory</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container content">
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Add New Item</h3>
            </div>
            <div class="card-body">
                <form action="products" method="post">
                    <input type="hidden" name="action" value="create">
                    
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" id="name" name="name" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
                    </div>
                    
                    <div class="form-group">
                        <label for="price">Price</label>
                        <input type="number" step="0.01" class="form-control" id="price" name="price" required>
                    </div>
                    
                    <div class="form-actions">
                        <a href="list" class="btn btn-secondary">Cancel</a>
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>