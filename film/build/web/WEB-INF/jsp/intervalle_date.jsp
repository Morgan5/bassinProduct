<%-- 
    Document   : intervalle_date
    Created on : 29 mars 2023, 13:03:05
    Author     : morga
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Choisir une date début et fin</h1>
        <br/>
        <%
            int idFilm = Integer.valueOf(request.getParameter("idFilm"));
        %>
        <form action="http://localhost:8080/film/planningFilm2" >
            date début: <input type="date" name="date1"/>
            <br/>
            date fin: <input type="date" name="date2"/>
            <input type="hidden" name="idFilm" value="<%= idFilm %>">
            <br/>
            <input type="submit" value="valider">
        </form>
    </body>
</html>
