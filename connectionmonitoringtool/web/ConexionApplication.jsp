<%
   /**
     * Copyright 2015 Desarrollos Red de Pruebas C.A.
     *
     * Licensed under the Apache License, Version 2.0 (the "License");
     * you may not use this file except in compliance with the License.
     * You may obtain a copy of the License at

     *     http://www.apache.org/licenses/LICENSE-2.0

     * Unless required by applicable law or agreed to in writing, software
     * distributed under the License is distributed on an "AS IS" BASIS,
     * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     * See the License for the specific language governing permissions and
     * limitations under the License.
     */
%>

<%@page import="org.september.util.StatusEnum"%>
<%@page import="org.september.util.GeneralUtil"%>
<%@page import="java.util.Calendar"%>
<%@page import="org.september.entity.ConexionAnual"%>
<%@page import="org.september.dao.DaoConexionAnual"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="org.september.util.ConexionMensualComparable"%>
<%@page import="org.september.util.DateUtil"%>
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
                        2L, date, 2L,StatusEnum.STATUS_ESTABLE.getValue(),0,false);

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
            
            DaoConexionAnual daoConexionAnual = DaoConexionAnual.INSTANCE;
            List<ConexionAnual> conexionsAnual = daoConexionAnual.
                    getConexionsAnual();

            if (conexionsAnual.size() < 1) {
                Calendar cHoy = Calendar.getInstance();
                long yeari = cHoy.get(Calendar.YEAR); 
                Long year = new Long(yeari);
                DaoConexionAnual.INSTANCE.add(year, 2L, 2L);
               
                conexionsAnual = daoConexionAnual.getConexionsAnual();
            }

            List<ConexionMensual> conexionsMensualTemp
                    = new ArrayList<ConexionMensual>(conexionsMensual);
            Collections.sort(conexionsMensualTemp, new ConexionMensualComparable());

            List<ConexionMensual> conexionsMensualOrd = new ArrayList<ConexionMensual>(conexionsMensualTemp);
            
            DecimalFormat decimalFormat = new DecimalFormat("0.00");

        %>
        <div style="width: 100%;">
            <div class="line"></div>
            <div class="topLine">                
                <div style="float: left;" class="headline">Conexiones</div>

            </div>
        </div>

        <div style="clear: both;"/>  
        
        <br />
        Estado de la conexion: <% if(conexions.size()>0){ %>
        <span style="font-size: 18px;font-weight: bolder; color: 
              <%=  (conexions.get(0).getStatus()==StatusEnum.STATUS_CRITICO.getValue())?
                      "red":(conexions.get(0).getStatus()==StatusEnum.STATUS_ESTABLE.getValue())?
                      "green":"orange"  %>"> <%=  GeneralUtil.getStatusName(conexions.get(0).getStatus())  %></span>  
          <%    }  %>  
        <br />
        <br />
        Total de Registro de <%= conexions.size()%>  Conexiones.

        <table>
            <tr>
                <th>getId </th>
                <th>Minutos sin conexion</th>
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
                <th>Contador de Estados</th>
                <th>Correo enviado</th>
            </tr>

            <% for (Conexion conexion : conexions) {%>
            <tr> 
                <td>
                    <%=conexion.getId()%>
                </td>
                <td class="red-color-table">
                    <%=conexion.getHoyContador()-conexion.getHoyConnexion()%>
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
                    <%=DateUtil.getMonthName(conexion.getSoloMesFecha())%>
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
                <td>
                    <%=conexion.getConStatus()%>
                </td> 
                <td>
                    <%=conexion.isSendEmail()%>
                </td> 
            </tr> 
            <%}
            %>
        </table>


        <br />
        % de desconexion 
        <table class="red-color-table-td">
            <tr>               
                <th>% desconexion Hoy</th>
                <th>% desconexion Ayer</th>
                <th>% desconexion Mes</th>
                <th>% desconexion A&ntilde;o</th>                
            </tr>

            <% for (Conexion conexion : conexions) {
                    double coxH = conexion.getHoyConnexion();
                    double conH = conexion.getHoyContador();
                    double perH = 100 - ((coxH / conH) * 100);
                    double coxA = conexion.getAyerConnexion();
                    double conA = conexion.getAyerContador();
                    double perA = 100 - ((coxA / conA) * 100);
                    double coxM = conexion.getMesConnexion();
                    double conM = conexion.getMesContador();
                    double perM = 100 - ((coxM / conM) * 100);
                    double coxY = conexion.getYearConnexion();
                    double conY = conexion.getYearContador();
                    double perY = 100 - ((coxY / conY) * 100);
            %>
            <tr> 
                <td>
                    <%=decimalFormat.format(perH)%>
                </td>
                <td>
                    <%=decimalFormat.format(perA)%>
                </td>
                <td>
                    <%=decimalFormat.format(perM)%>
                </td>
                <td>
                    <%=decimalFormat.format(perY)%>
                </td>
            </tr> 
            <%}
            %>
        </table>

        <br />

        % de desconexion Mensual
        <table >
            <tr>               
                <th>Mes</th>
                <th>% desconexion</th>                            
            </tr>

            <% for (ConexionMensual conexionMensual : conexionsMensualOrd) {
                    double coxM = conexionMensual.getMesConnexion();
                    double conM = conexionMensual.getMesContador();
                    double perM = 100 - ((coxM / conM) * 100);
            %>
            <tr> 
                <td>
                    <%=DateUtil.getMonthName(conexionMensual.getMes().intValue() - 1)%>                   
                </td>
                <td class="red-color-table">
                    <%=decimalFormat.format(perM)%>
                </td>              
            </tr> 
            <%}
            %>
        </table>
        
        
        <br />
        
         % de desconexion Anual
        <table >
            <tr>               
                <th>A&ntilde;o</th>
                <th>% desconexion</th>                            
            </tr>

            <% for (ConexionAnual conexionAnual : conexionsAnual) {
                    double coxA = conexionAnual.getYearConnexion();
                    double conA = conexionAnual.getYearContador();
                    double perA = 100 - ((coxA / conA) * 100);
            %>
            <tr> 
                <td>
                    <%=conexionAnual.getYear()%>                   
                </td>
                <td class="red-color-table">
                    <%=decimalFormat.format(perA)%>
                </td>              
            </tr> 
            <%}
            %>
        </table>

        <br />
        <hr />

    </body>
</html> 
