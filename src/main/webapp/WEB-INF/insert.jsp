<%--
  Created by IntelliJ IDEA.
  User: Ngoc Huyen
  Date: 08/02/2023
  Time: 6:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Insert employee</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<br>
<div class="container">
    <h2>Insert Employee</h2>
    <hr>
    ${errors }
    <div class="row">
        <div class="col-sm-6">
            <%--map dia chi voi /insert insertServlet.java--%>
            <form method="post" action="<c:url value = "/insert"/> ">
                <div class="form-group">
                    <input name="id" type="text" class="form-control" placeholder="Employee Id"/>
                    <p>${errorId}</p>
                </div>
                <div class="form-group">
                    <input name="name" type="text" class="form-control" placeholder="Employee Name"/>
                    <p>${errorId}</p>
                </div>

                <div class="form-group">
                    <input name="address" type="text" class="form-control" placeholder="Employee Address"/>
                </div>

                <div class="form-group">
                    <input name="age" type="number" class="form-control" placeholder="Employee Age"/>
                </div>

                <button class="btn btn-primary" type="submit">Insert</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
