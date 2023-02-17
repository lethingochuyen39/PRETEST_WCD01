<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP-Pretest01</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <%--hieu la goi pt get ben insertServlet.java--%>
    <div class="container col-md-4 mt-5">
        <!-- Default form login -->
        <form class="text-center border border-primary p-5" method="post" action="login">
            <p class="h2 mb-4">Sign in</p>
            <!-- Username -->
            <input type="text" class="form-control mb-4" placeholder="userName" name="username">

            <!-- Password -->
            <input type="password" class="form-control mb-4" placeholder="Password" name="password">

            <!-- Sign in button -->
            <div class="d-flex justify-content-end">
                <button class="btn-lg btn-primary" type="submit">Login</button>
            </div>
        </form>
        <!-- Default form login -->
    </div>
</body>
</html>