<%-- 
    Document   : bookok
    Created on : 18-feb-2019, 4:19:37
    Author     : Borja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>New Book Confirmed and Validated!</h1>
        
        <ul>
            <li>${requestScope.book.title}</li>
            <li>${requestScope.book.author}</li>
            <li>${requestScope.book.genre}</li>
            <li>${requestScope.book.yearPublished}</li>
        </ul>
    </body>
</html>
