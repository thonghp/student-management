<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 1/30/2022
  Time: 2:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="templates/css/style.css">
    <link rel="stylesheet" href="templates/css/add-student-style.css">
</head>
<body>
<div id="container">
    <h3>Update student</h3>

    <form action="StudentController" method="GET">

        <input type="hidden" name="command" value="UPDATE"/>
        <input type="hidden" name="studentId" value="${THE_STUDENT.id}">

        <table>
            <tbody>
            <tr>
                <td><label>First Name:</label></td>
                <td><input type="text" name="firstName" value="${THE_STUDENT.firstName}"/></td>
            </tr>

            <tr>
                <td><label>Last Name:</label></td>
                <td><input type="text" name="lastName" value="${THE_STUDENT.lastName}"/></td>
            </tr>

            <tr>
                <td><label>Email:</label></td>
                <td><input type="text" name="email" value="${THE_STUDENT.email}"/></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"/></td>
            </tr>
            </tbody>
        </table>
    </form>

    <div style="clear: both;"></div>

    <p>
        <a href="StudentController">Back list</a>
    </p>
</div>
</body>
</html>
