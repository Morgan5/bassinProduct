<%-- 
    Document   : planningFilm
    Created on : 1 mars 2023, 16:08:09
    Author     : morga
--%>

<%@page import="java.time.LocalDate"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Scene"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  
            ArrayList<List<Scene>> lls = (ArrayList<List<Scene>>) request.getAttribute("lls");
            HashMap<Integer, LocalDate> map = (HashMap<Integer, LocalDate>) request.getAttribute("map");
            //List<Scene> ls = (List<Scene>) request.getAttribute("ls");
        %>
        <h1>Planning !</h1>
        <%
            for(int i=0; i<lls.size(); i++){
                out.print("---------------");
                out.print("<br/>");
                out.print(map.get(i));
                out.print("<br/>");
                out.print("---------------");
                out.print("<br/>");
                for(int j=0; j<lls.get(i).size(); j++){
                    out.print(lls.get(i).get(j).getLabel());
                    out.print("<br/>");
                }
            }
        
        %>
    </body>
</html>
