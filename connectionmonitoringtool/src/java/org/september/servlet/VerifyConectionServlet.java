package org.september.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.september.dao.DaoConexion;
import org.september.dao.DaoConexionAnual;
import org.september.dao.DaoConexionMensual;
import org.september.entity.Conexion;
import org.september.entity.ConexionAnual;
import org.september.entity.ConexionMensual;
import org.september.util.EnumProperty;

public class VerifyConectionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            URL url = new URL(EnumProperty.URL_CHECK.getValue());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();

            DaoConexion daoConexion = DaoConexion.INSTANCE;
            DaoConexionMensual daoConexionMensual = DaoConexionMensual.INSTANCE;
            DaoConexionAnual daoConexionAnual = DaoConexionAnual.INSTANCE;

            List<Conexion> conexions = daoConexion.getConexions();
            List<ConexionMensual> conexionMensuals = daoConexionMensual.
                    getConexionsMensual();
            List<ConexionAnual> conexionAnuals = daoConexionAnual.
                    getConexionsAnual();

            if (conexions.size() > 0 && conexionMensuals.size()>0 && conexionAnuals.size()>0) {

                Calendar cHoy = Calendar.getInstance();
                int hoy = cHoy.get(Calendar.DATE);
                int mes = cHoy.get(Calendar.MONTH);
                int year = cHoy.get(Calendar.YEAR);

                Conexion conexion = conexions.get(0);

                Date dateRegistroHoy = conexion.getHoyFecha();
                Date dateRegistroMes = conexion.getMesFecha();
                Date dateRegistroYear = conexion.getYearFecha();
                Calendar cRegistroHoy = Calendar.getInstance();
                Calendar cRegistroMes = Calendar.getInstance();
                Calendar cRegistroYear = Calendar.getInstance();
                cRegistroHoy.setTime(dateRegistroHoy);
                cRegistroMes.setTime(dateRegistroMes);
                cRegistroYear.setTime(dateRegistroYear);
                int hoyRegistro = cRegistroHoy.get(Calendar.DATE);
                int mesRegistro = cRegistroMes.get(Calendar.MONTH);
                int yearRegistro = cRegistroYear.get(Calendar.YEAR);

                if (code == 200) { // se conecto

                    if (hoy == hoyRegistro) {  // el dia no ha cambiado
                        conexion.setHoyConnexion(conexion.getHoyConnexion() + 1);
                        conexion.setHoyContador(conexion.getHoyContador() + 1);
                    } else { // el dia cambio
                        conexion.setAyerConnexion(conexion.getHoyConnexion());
                        conexion.setAyerContador(conexion.getHoyContador());
                        conexion.setHoyConnexion(1L);
                        conexion.setHoyContador(1L);
                        conexion.setHoyFecha(new Date());
                    }
                    if (mes == mesRegistro) { // el mes es el mismo
                        conexion.setMesConnexion(conexion.getMesConnexion() + 1);
                        conexion.setMesContador(conexion.getMesContador() + 1);
                    } else { // el mes cambio
                             // busco el mes y actualizo                        
                        for (ConexionMensual cms : conexionMensuals) {
                            Long mesL = new Long(mes);
                            if (cms.getMes().equals(mesL)) {
                                cms.setMesConnexion(conexion.getMesConnexion());
                                cms.setMesContador(conexion.getMesContador());                        
                                daoConexionMensual.update(cms);
                            }
                        }                       

                        conexion.setMesConnexion(1L);
                        conexion.setMesContador(1L);
                        conexion.setMesFecha(new Date());
                    }
                    if (year == yearRegistro) {
                        conexion.setYearConnexion(conexion.getYearConnexion() + 1);
                        conexion.setYearContador(conexion.getYearContador() + 1);
                    } else { // el year cambio
                        // busco el year y actualizo                        
                        for (ConexionAnual cas : conexionAnuals) {
                            Long yearL = new Long(year);
                            if (cas.getYear().equals(yearL)) {
                                cas.setYearConnexion(conexion.getYearConnexion());
                                cas.setYearContador(conexion.getYearContador());                        
                                daoConexionAnual.update(cas);
                            }
                        }  
                      
                        conexion.setYearConnexion(1L);
                        conexion.setYearContador(1L);
                        conexion.setYearFecha(new Date());
                    }

                    daoConexion.update(conexion);
                } else { // no se conecto

                    if (hoy == hoyRegistro) {
                        conexion.setHoyContador(conexion.getHoyContador() + 1);
                    } else {
                        conexion.setAyerConnexion(conexion.getHoyConnexion());
                        conexion.setAyerContador(conexion.getHoyContador());
                        conexion.setHoyConnexion(0L);
                        conexion.setHoyContador(1L);
                        conexion.setHoyFecha(new Date());
                    }
                    if (mes == mesRegistro) {
                        conexion.setMesContador(conexion.getMesContador() + 1);
                    } else { // el mes cambio
                        // busco el mes y actualizo                        
                        for (ConexionMensual cms : conexionMensuals) {
                            Long mesL = new Long(mes);
                            if (cms.getMes().equals(mesL)) {
                                cms.setMesConnexion(conexion.getMesConnexion());
                                cms.setMesContador(conexion.getMesContador());                        
                                daoConexionMensual.update(cms);
                            }
                        } 

                        conexion.setMesConnexion(0L);
                        conexion.setMesContador(1L);
                        conexion.setMesFecha(new Date());
                    }
                    if (year == yearRegistro) {
                        conexion.setYearContador(conexion.getYearContador() + 1);
                    } else {// el year cambio
                        // busco el year y actualizo                        
                        for (ConexionAnual cas : conexionAnuals) {
                            Long yearL = new Long(year);
                            if (cas.getYear().equals(yearL)) {
                                cas.setYearConnexion(conexion.getYearConnexion());
                                cas.setYearContador(conexion.getYearContador());                        
                                daoConexionAnual.update(cas);
                            }
                        }
                        conexion.setYearConnexion(0L);
                        conexion.setYearContador(1L);
                        conexion.setYearFecha(new Date());
                    }

                    daoConexion.update(conexion);
                }

            }

        } finally {
            out.close();
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

}
