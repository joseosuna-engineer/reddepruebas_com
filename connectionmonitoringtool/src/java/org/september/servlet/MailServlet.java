package org.september.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.september.dao.DaoConexion;
import org.september.dao.DaoConexionMensual;
import org.september.entity.Conexion;
import org.september.entity.ConexionMensual;
import org.september.util.ConexionMensualComparable;
import org.september.util.DateUtil;

public class MailServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sendEmailFrom = "soporte.red.social.prueba@gmail.com";
        String sendMailTo = "soporte.red.social.prueba@gmail.com";
        String recipientName = "Mr. Pruebas";
        String messageSubject = "INFORME DE CONEXION";
        String htmlBody = buildHtmlMessage();

        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(sendEmailFrom));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(sendMailTo, recipientName));
            msg.setSubject(messageSubject);

            Multipart mp = new MimeMultipart();

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlBody, "text/html");
            mp.addBodyPart(htmlPart);

            msg.setContent(mp);

            Transport.send(msg);

        } catch (AddressException e) {

        } catch (MessagingException e) {

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private String buildHtmlMessage() {

        StringBuffer message = new StringBuffer(400);
        message.append("<!DOCTYPE html>");
        message.append("<html>");
        message.append("   <head>");
        message.append("       <title>Conexiones</title>");
        message.append("      <meta charset='utf-8'>");

        message.append(" <style type='text/css'><!-- ");

        message.append(" body { margin: 5px; font-family: Arial, Helvetica, sans-serif; } hr { border: 0; background-color: #DDDDDD; height: 1px; margin: 5px; } table th { background:#EFEFEF none repeat scroll 0 0; border-top:1px solid #CCCCCC; font-size:small; padding-left:5px; padding-right:4px; padding-top:4px; vertical-align:top; text-align:left; } table tr { background-color: #e5ecf9; font-size:small; } .topLine { height: 1.25em; background-color: #e5ecf9; padding-left: 4px; padding-right: 4px; padding-bottom: 3px; padding-top: 2px; } .headline { font-weight: bold; color: #3366cc; } .done { font-size: x-small; vertical-align: top; } .email { font-size: x-small; vertical-align: top; } form td { font-size: smaller; } .red-color-table { font-weight: bold; color: red; font-size: 14px; text-align: center; } .red-color-table-td td { font-weight: bold; color: red; font-size: 14px; text-align: center; } table td { text-align: center; } ");
        message.append("--></style> ");

        message.append("  </head>");
        message.append("   <body>");

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

        List<ConexionMensual> conexionsMensualTemp
                = new ArrayList<ConexionMensual>(conexionsMensual);
        Collections.sort(conexionsMensualTemp, new ConexionMensualComparable());

        List<ConexionMensual> conexionsMensualOrd = new ArrayList<ConexionMensual>(conexionsMensualTemp);

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        message.append("  <hr />");
        message.append("<div style='width: 100%;'>");
        message.append("<div class='line'></div>");
        message.append("<div class='topLine'>");
        message.append("<div style='float: left;' class='headline'>Conexiones</div>");

        message.append("</div>");
        message.append("</div>");

        message.append("<div style='clear: both;'/>");
        message.append("<br />");
        message.append("Total de Registro de ");
        message.append(conexions.size());
        message.append(" Conexiones.");

        message.append("<table>");
        message.append("<tbody>");
        message.append("<tr>");
        message.append("<th>getId </th>");
        message.append(" <th>Conexiones Hoy</th>");
        message.append("<th>Intentos Hoy</th>");
        message.append(" <th>Fecha Hoy</th>");
        message.append("  <th>Conexiones Ayer</th>");
        message.append("  <th>Intentos Ayer</th>");
        message.append("   <th>Conexiones Mes</th>");
        message.append("  <th>Intentos Mes</th>");
        message.append("  <th>Fecha Mes</th>");
        message.append("   <th>Conexiones A&ntilde;o</th>");
        message.append("   <th>Intentos A&ntilde;o</th>");
        message.append("   <th>Fecha A&ntilde;o</th>");
        message.append("  </tr>");

        for (Conexion conexion : conexions) {

            message.append("<tr>");
            message.append(" <td>");
            message.append(conexion.getId());
            message.append(" </td>");
            message.append("  <td>");
            message.append(conexion.getHoyConnexion());
            message.append("   </td>");
            message.append("   <td>");
            message.append(conexion.getHoyContador());
            message.append("    </td>");
            message.append("   <td style='color:grey'>");
            message.append(conexion.getSoloHoyFecha());
            message.append("     </td>");
            message.append("    <td>");
            message.append(conexion.getAyerConnexion());
            message.append("    </td>");
            message.append("   <td>");
            message.append(conexion.getAyerContador());
            message.append("    </td>");
            message.append("     <td>");
            message.append(conexion.getMesConnexion());
            message.append("    </td>");
            message.append("     <td>");
            message.append(conexion.getMesContador());
            message.append("    </td>");
            message.append("     <td style='color:grey'>");
            message.append(DateUtil.getMonthName(conexion.getSoloMesFecha()));
            message.append("    </td>");
            message.append("    <td>");
            message.append(conexion.getYearConnexion());
            message.append("    </td>");
            message.append("    <td>");
            message.append(conexion.getYearContador());
            message.append("    </td>");
            message.append("    <td style='color:grey'>");
            message.append(conexion.getSoloYearFecha());
            message.append("    </td> ");
            message.append("  </tr>");
        }

        message.append("</tbody>");
        message.append("</table>");

        message.append("  <br />");
        message.append("  % de desconexion");
        message.append("  <table class='red-color-table-td'>");
        message.append("<tbody>");
        message.append("  <tr>");
        message.append("  <th>% desconexion Hoy</th>");
        message.append("  <th>% desconexion Ayer</th>");
        message.append("  <th>% desconexion Mes</th>");
        message.append("  <th>% desconexion A&ntilde;o</th>");
        message.append("  </tr>");

        for (Conexion conexion : conexions) {
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

            message.append("  <tr>");
            message.append("  <td>");
            message.append(decimalFormat.format(perH));
            message.append("  </td>");
            message.append("  <td>");
            message.append(decimalFormat.format(perA));
            message.append("  </td>");
            message.append("  <td>");
            message.append(decimalFormat.format(perM));
            message.append("  </td>");
            message.append("  <td>");
            message.append(decimalFormat.format(perY));
            message.append("  </td>");
            message.append("  </tr>");
        }

        message.append("</tbody>");
        message.append("</table>");

        message.append("  <br />");

        message.append("  % de desconexion Mensual");
        message.append("  <table >");
        message.append("<tbody>");
        message.append("  <tr>");
        message.append("  <th>Mes</th>");
        message.append("  <th>% desconexion</th>");
        message.append("  </tr>");

        for (ConexionMensual conexionMensual : conexionsMensualOrd) {
            double coxM = conexionMensual.getMesConnexion();
            double conM = conexionMensual.getMesContador();
            double perM = 100 - ((coxM / conM) * 100);

            message.append("  <tr>");
            message.append("  <td>");
            message.append(DateUtil.getMonthName(conexionMensual.getMes().intValue() - 1));
            message.append("  </td>");
            message.append("  <td class='red-color-table'>");
            message.append(decimalFormat.format(perM));
            message.append("  </td>");
            message.append("  </tr>");
        }

        message.append("</tbody>");
        message.append("</table>");

        message.append("  <br />");
        message.append("  <hr />");
        message.append("</body>");
        message.append("</html>");

        return message.toString();
    }

}
