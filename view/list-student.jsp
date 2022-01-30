<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/taglib.jsp" %>

<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" href="templates/css/style.css">
<body>

<div id="wrapper">
    <div id="header">
        <h2>Nong lam university</h2>
    </div>
</div>

<div id="container">
    <div id="content">
        <input type="button" value="Add Student" onclick="window.location.href='add.jsp'; return false;"
               class="add-student-button"/>
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>

            <c:forEach var="tempStudent" items="${STUDENT_LIST}">
                <%--  các tham số này sẽ hiện thị trên url khi chạy  --%>
                <c:url var="tempLink" value="StudentController">
                    <c:param name="command" value="LOAD"/>
                    <%--  nhúng id vào link  --%>
                    <c:param name="studentId" value="${tempStudent.id}"/>
                </c:url>

                <c:url var="deleteLink" value="StudentController">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="studentId" value="${tempStudent.id}"/>
                </c:url>

                <tr>
                    <td>${tempStudent.firstName}</td>
                    <td>${tempStudent.lastName}</td>
                    <td>${tempStudent.email}</td>
                    <td>
                        <a href="${tempLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if(!(confirm('are you sure you want to delete this student?'))) return false">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>

</html>
