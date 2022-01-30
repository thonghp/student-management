<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="templates/css/style.css">
    <link rel="stylesheet" href="templates/css/add-student-style.css">
</head>
<body>
<div id="container">
    <h3>Add student</h3>

    <form action="StudentController" method="GET">

        <%--    đọc parameter của input chính là add    --%>
        <input type="hidden" name="command" value="ADD"/>

        <table>
            <tbody>
            <tr>
                <td><label>First Name:</label></td>
                <td><input type="text" name="firstName"/></td>
            </tr>

            <tr>
                <td><label>Last Name:</label></td>
                <td><input type="text" name="lastName"/></td>
            </tr>

            <tr>
                <td><label>Email:</label></td>
                <td><input type="text" name="email"/></td>
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
