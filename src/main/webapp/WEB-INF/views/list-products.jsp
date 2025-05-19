<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory System</title>
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
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 2rem;
        }
        h2 {
            font-weight: 400;
            color: #000;
        }
        .btn {
            padding: 0.6rem 1.2rem;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: 500;
            transition: all 0.3s;
        }
        .btn-add {
            background-color: #000;
            color: #fff;
            text-decoration: none;
        }
        .btn-add:hover {
            background-color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            box-shadow: 0 2px 15px rgba(0,0,0,0.05);
            background-color: #fff;
        }
        th, td {
            padding: 1rem;
            text-align: left;
            border-bottom: 1px solid #eee;
        }
        th {
            background-color: #000;
            color: #fff;
            font-weight: 500;
        }
        tr:hover {
            background-color: #f9f9f9;
        }
        .action-btn {
            padding: 0.4rem 0.8rem;
            margin-right: 0.5rem;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            font-size: 0.9rem;
        }
        .btn-edit {
            background-color: #000;
            color: #fff;
        }
        .btn-delete {
            background-color: #fff;
            color: #000;
            border: 1px solid #000;
        }
        .btn-edit:hover {
            background-color: #333;
        }
        .btn-delete:hover {
            background-color: #f5f5f5;
        }
        .logout-btn {
            background-color: transparent;
            color: #ccc;
            border: 1px solid #ccc;
            padding: 0.4rem 0.8rem;
            border-radius: 3px;
            text-decoration: none;
            transition: all 0.3s;
        }
        .logout-btn:hover {
            color: #fff;
            border-color: #fff;
        }
    </style>
</head>
<body>
    <nav class="navbar">
        <div class="container navbar-container">
            <a class="navbar-brand" href="#">Inventory System</a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="list">Dashboard</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="list">Inventory</a>
                </li>
                <li class="nav-item">
                    <a class="logout-btn" href="logout">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container content">
        <div class="header">
            <h2>Inventory Items</h2>
            <c:if test="${role == 'admin'}"><a href="products?action=new" class="btn btn-add">Add New Item</a></c:if>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <c:if test="${role == 'admin'}">
                        <th>Actions</th>
                    </c:if>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>$${product.price}</td>
                        <c:if test="${role == 'admin'}">
                            <td>
                                <a href="products?action=edit&id=${product.id}" class="action-btn btn-edit">Edit</a>
                                <a href="products?action=delete&id=${product.id}" class="action-btn btn-delete" onclick="return confirm('Are you sure you want to delete this item?')">Delete</a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>