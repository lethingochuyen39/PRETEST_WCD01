<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ngoc Huyen
  Date: 14/02/2023
  Time: 10:55 AM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>update employee</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h2>Update Employee</h2>
    <hr>
    <div class="row">
        <div class="col-sm-6">
            <%--map dia chi voi /update updateServlet.java--%>
            <form method="post" action="<c:url value = "/update"/> ">
                <div class="form-group">
                    <input name="id" type="hidden"  value="${emp.id}"
                           class="form-control" placeholder="Employee Id"/>
                </div>
                <div class="form-group">
                    <input name="name" type="text"
                           value="${emp.name}"  class="form-control" placeholder="Employee Name"/>
                </div>

                <div class="form-group">
                    <input name="address" type="text" value="${emp.address}" class="form-control" placeholder="Employee Address"/>
                </div>

                <div class="form-group">
                    <input name="age" type="number" value="${emp.age}" class="form-control" placeholder="Employee Age"/>
                </div>

                <button class="btn btn-primary" type="submit">Update</button>
                <a class="btn btn-outline-secondary" href="<c:url value = "/list"/>" role="button">back</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>
