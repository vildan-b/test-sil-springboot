<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

        <title>Test Seite</title>
    </head>
    <body>
    Test
        <table>
            <thead>
                <tr style="font-weight:bold;" bgcolor="lightblue">
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${owners}" var="owner" varStatus="status">
                    <tr  bgcolor="${status.index % 2 == 0 ? 'white': 'lightgray'}">
                        <td>${owner.id}</td>
                        <td>${owner.firstName}</td>
                        <td>${owner.lastName}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>