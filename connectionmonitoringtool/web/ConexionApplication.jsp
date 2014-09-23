<%@page import="java.util.Date"%>
<%@page import="org.september.entity.Conexion"%>
<%@page import="org.september.dao.Dao"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>


<%@page import="java.util.ArrayList"%>

<html>
    <head>
        <title>Conexiones</title>
        <link rel="stylesheet" type="text/css" href="css/main.css"/>
        <meta charset="utf-8"> 
    </head>
    <body>
        <%
            Dao dao = Dao.INSTANCE;
            List<Conexion> conexions = dao.getConexions();

            if (conexions.size() < 1) {
                Date date = new Date();

                Dao.INSTANCE.add(1L, 1L, date, 1L, 1L, date, 1L, 1L, date, 1L, 1L, date,
                        1L);

                conexions = dao.getConexions();
            }

        %>
        <div style="width: 100%;">
            <div class="line"></div>
            <div class="topLine">                
                <div style="float: left;" class="headline">Conexiones</div>

            </div>
        </div>

        <div style="clear: both;"/>  
        You have a total number of <%= conexions.size()%>  Conexiones.

        <table>
            <tr>
                <th>getId </th>
                <th>getHoyConnexion</th>
                <th>getHoyContador</th>
                <th>getHoyFecha</th>
            </tr>

            <% for (Conexion conexion : conexions) {%>
            <tr> 
                <td>
                    <%=conexion.getId()%>
                </td>
                <td>
                    <%=conexion.getHoyConnexion()%>
                </td>
                <td>
                    <%=conexion.getHoyContador()%>
                </td>
                <td>
                    <%=conexion.getHoyFecha()%>
                </td>
            </tr> 
            <%}
            %>
        </table>


        <hr />


    </body>
</html> 