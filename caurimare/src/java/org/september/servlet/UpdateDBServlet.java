package org.september.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.september.dao.DaoConexionMensual;
import org.september.entity.ConexionMensual;

public class UpdateDBServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DaoConexionMensual daoConexionMensual = DaoConexionMensual.INSTANCE;
        List<ConexionMensual> conexionMensuals = daoConexionMensual.
                getConexionsMensual();

        if (conexionMensuals.size() > 0) {
            Calendar cHoy = Calendar.getInstance();
            int mes = cHoy.get(Calendar.MONTH);

            for (ConexionMensual cms : conexionMensuals) {
                Long mesL = new Long(mes);
                if (cms.getMes().equals(mesL)) {
                    cms.setMesConnexion(4367L);
                    cms.setMesContador(4377L);
                    daoConexionMensual.update(cms);
                }
            }
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
