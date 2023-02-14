<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ngoc Huyen
  Date: 08/02/2023
  Time: 6:56 PM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>list</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h3>Employee List</h3>
    <hr>
    <a href="<c:url value="insert"/> " class="btn btn-outline-success">Create Employee</a>
    <div class="row">
        <div class="col-sm-8">
            <table class="table">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Age</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${employeeList}" var="emp">
                    <tr>
                        <td>${emp.id}</td>
                        <td>${emp.name}</td>
                        <td>${emp.address}</td>
                        <td>${emp.age}</td>
                        <td>
                            <a href="<c:url value="/update?id=${emp.id}"/> " class="btn btn-outline-warning">Update</a>
                            <a href="<c:url value="/delete?id=${emp.id}"/>"
                               onclick="return confirm('Are you sure you want to delete this item?');"
                               class="btn btn-outline-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
