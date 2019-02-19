<%-- 
    Document   : bookform
    Created on : 18-feb-2019, 1:12:15
    Author     : Borja
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Page</title>
    </head>
    <body>
        <h1>Create a new Book</h1>
        
        <c:if test="${not empty requestScope.mistakes}">
            <ol>
                <c:forEach items="${requestScope.mistakes}" var="mistake">
                    <li>Please correct this issue: ${mistake.propertyPath} ${mistake.message}</li>
                </c:forEach>
            </ol>
        </c:if>
        
        <form method="POST" action="/bgonzalez2-fp/book">
            <div>
                <label for="bookTitle">Book Title:</label>
                <input value="${requestScope.book.title}" type="text" id="bookTitle" name="bookTitle"/>
            </div>
            <div>
                <label for="bookAuthor">Book Author:</label>
                <input value="${requestScope.book.author}" type="text" id="bookAuthor" name="bookAuthor"/>
            </div>
            <div>
                <label for="bookGenre">Book Genre:</label>
                <input value="${requestScope.book.genre}" type="text" id="bookGenre" name="bookGenre"/>
            </div>
            <div>
                <label for="bookYearPublished">Book Publishing Date:</label>
                <input value="${requestScope.formattedPublishedYear}" type="date" id="bookYearPublished" name="bookYearPublished"/>
            </div>
            <button type="submit">Create New Book</button>
        </form>
    </body>
</html>
