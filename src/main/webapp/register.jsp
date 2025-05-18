<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Signup</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h3 class="text-center">Signup</h3>
                    </div>
                    <div class="card-body">
                        <% if (request.getParameter("error") != null) { 
                               String error = request.getParameter("error");
                               String message = "";
                               if ("empty".equals(error)) {
                                   message = "Username and password cannot be empty!";
                               } else if ("exists".equals(error)) {
                                   message = "Username already exists!";
                               } else if ("failed".equals(error)) {
                                   message = "Registration failed. Please try again.";
                               }
                        %>
                            <div class="alert alert-danger" role="alert">
                                <%= message %>
                            </div>
                        <% } %>

                        <form action="register" method="post">
                            <div class="mb-3">
                                <label for="username" class="form-label">Username</label>
                                <input type="text" class="form-control" id="username" name="username" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Password</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>
                            <div class="d-grid">
                                <button type="submit" class="btn btn-success">Signup</button>
                            </div>
                        </form>

                        <div class="text-center mt-3">
                            Already have an account?<a href="login.jsp"> Login here</a>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
