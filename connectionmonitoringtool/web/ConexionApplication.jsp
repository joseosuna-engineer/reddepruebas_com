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

                Dao.INSTANCE.add(1L, 1L, date, 1L, 1L, 1L, 1L, date, 1L, 1L, date,
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
        Total de Registro de <%= conexions.size()%>  Conexiones.

        <table>
            <tr>
                <th>getId </th>
                <th>Conexiones Hoy</th>
                <th>Intentos Hoy</th>
                <th>Fecha Hoy</th>
                <th>Conexiones Ayer</th>
                <th>Intentos Ayer</th>
                <th>Conexiones Mes</th>
                <th>Intentos Mes</th>
                <th>Fecha Mes</th>
                <th>Conexiones A&ntilde;o</th>
                <th>Intentos A&ntilde;o</th>
                <th>Fecha A&ntilde;o</th>
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
                    <%=conexion.getSoloHoyFecha()%>
                </td>
                <td>
                    <%=conexion.getAyerConnexion()%>
                </td>
                <td>
                    <%=conexion.getAyerContador()%>
                </td> 
                <td>
                    <%=conexion.getMesConnexion()%>
                </td>
                <td>
                    <%=conexion.getMesContador()%>
                </td>
                <td>
                    <%=conexion.getSoloMesFecha()%>
                </td>
                <td>
                    <%=conexion.getYearConnexion()%>
                </td>
                <td>
                    <%=conexion.getYearContador()%>
                </td>
                <td>
                    <%=conexion.getSoloYearFecha()%>
                </td>                
            </tr> 
            <%}
            %>
        </table>


        <br />
        % de desconexion 
        <table>
            <tr>               
                <th>% desconexion Hoy</th>
                <th>% desconexion Ayer</th>
                <th>% desconexion Mes</th>
                <th>% desconexion A&ntilde;o</th>                
            </tr>

            <% for (Conexion conexion : conexions) {%>
            <tr> 
                <td>
                    <%=100 - ((conexion.getHoyConnexion() / conexion.getHoyContador()) * 100)%>
                </td>
                <td>
                    <%=100 - ((conexion.getAyerConnexion() / conexion.getAyerContador()) * 100)%>
                </td>
                <td>
                    <%=100 - ((conexion.getMesConnexion() / conexion.getMesContador()) * 100)%>
                </td>
                <td>
                    <%=100 - ((conexion.getYearConnexion() / conexion.getYearContador()) * 100)%>
                </td>
            </tr> 
            <%}
            %>
        </table>

        <br />
        <hr />
    </body>
</html> 