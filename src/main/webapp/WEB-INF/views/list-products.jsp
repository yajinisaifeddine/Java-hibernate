<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">Product Management</a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="list">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="list">Products</a>
                    </li>
                    <li class="nav-item">
                    
                  <c:choose>
				    <c:when test="${isAuthenticated == true}">
				        <li class="nav-item">
				            <a class="nav-link" href="logout">Logout</a>
				        </li>
				    </c:when>
				    <c:otherwise>
				        <li class="nav-item">
				            <a class="nav-link" href="login.jsp">Login</a>
				        </li>
				    </c:otherwise>
				</c:choose>

                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="d-flex justify-content-between mb-4">
            <h2>Product List</h2>
            <c:if test="${role == 'admin'}"><a href="products?action=new" class="btn btn-success">Add New Product</a></c:if>
        </div>
        
        <table class="table table-striped table-hover">
    <thead class="table-dark">
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
                        <a href="products?action=edit&id=${product.id}" class="btn btn-primary btn-sm">Edit</a>
                        <form action="products" method="post" style="display: inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${product.id}">
                            <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?')">Delete</button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </tbody>
</table>

            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>