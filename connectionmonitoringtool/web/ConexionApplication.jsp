<%@page import="java.util.Collections"%>
<%@page import="org.september.entity.ConexionMensual"%>
<%@page import="java.util.Date"%>
<%@page import="org.september.entity.Conexion"%>
<%@page import="org.september.dao.DaoConexion"%>
<%@page import="org.september.dao.DaoConexionMensual"%>
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
            DaoConexion daoConexion = DaoConexion.INSTANCE;
            List<Conexion> conexions = daoConexion.getConexions();

            if (conexions.size() < 1) {
                Date date = new Date();

                DaoConexion.INSTANCE.add(1L, 1L, date, 1L, 1L, 1L, 2L, date, 2L,
                        2L, date, 2L);

                conexions = daoConexion.getConexions();
            }

            DaoConexionMensual daoConexionMensual = DaoConexionMensual.INSTANCE;
            List<ConexionMensual> conexionsMensual = daoConexionMensual.
                    getConexionsMensual();

            if (conexionsMensual.size() < 1) {
                for (int i = 1; i < 13; i++) {
                    Long iL = new Long(i);
                    DaoConexionMensual.INSTANCE.add(iL, 1L, 1L);
                }
                conexionsMensual = daoConexionMensual.getConexionsMensual();
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
                <td style="color:grey">
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
                <td style="color:grey">
                    <%=conexion.getSoloMesFecha()%>
                </td>
                <td>
                    <%=conexion.getYearConnexion()%>
                </td>
                <td>
                    <%=conexion.getYearContador()%>
                </td>
                <td style="color:grey">
                    <%=conexion.getSoloYearFecha()%>
                </td>                
            </tr> 
            <%}
            %>
        </table>


        <br />
        % de desconexion 
        <table class="red-color-table">
            <tr>               
                <th>% desconexion Hoy</th>
                <th>% desconexion Ayer</th>
                <th>% desconexion Mes</th>
                <th>% desconexion A&ntilde;o</th>                
            </tr>

            <% for (Conexion conexion : conexions) {%>
            <tr> 
                <td>
                    <%=100 - ((conexion.getHoyConnexion()
                            / conexion.getHoyContador()) * 100)%>
                </td>
                <td>
                    <%=100 - ((conexion.getAyerConnexion()
                            / conexion.getAyerContador()) * 100)%>
                </td>
                <td>
                    <%=100 - ((conexion.getMesConnexion()
                            / conexion.getMesContador()) * 100)%>
                </td>
                <td>
                    <%=100 - ((conexion.getYearConnexion()
                            / conexion.getYearContador()) * 100)%>
                </td>
            </tr> 
            <%}
            %>
        </table>

        <br />

        % de desconexion Mensual
        <table class="red-color-table">
            <tr>               
                <th>Mes</th>
                <th>% desconexion</th>                            
            </tr>

            <% for (ConexionMensual conexionMensual : conexionsMensual) {%>
            <tr> 
                <td>
                    <%=conexionMensual.getMes()%>
                </td>
                <td>
                    <%= 100 - ((conexionMensual.getMesConnexion()
                            / conexionMensual.getMesContador()) * 100)%>
                </td>              
            </tr> 
            <%}
            %>
        </table>

        <br />
        <hr />

    </body>
</html> 