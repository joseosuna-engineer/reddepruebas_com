package org.september.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.september.dao.Dao;
import org.september.entity.Conexion;

public class VerifyConectionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            URL url = new URL("http://reddepruebas.com.ve");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();
            Date date = new Date();

            Dao dao = Dao.INSTANCE;

            List<Conexion> conexions = dao.getConexions();

            if (conexions.size() > 0) {
                
                Conexion conexion = conexions.get(0);

                if (code == 200) {
                    conexion.setHoyConnexion(conexion.getHoyConnexion()+1);
                    conexion.setHoyContador(conexion.getHoyContador()+1);
                    
                    dao.update(conexion);
                } else {
                    conexion.setHoyConnexion(conexion.getHoyConnexion()+1);
                    conexion.setHoyContador((conexion.getHoyContador()>0)?conexion.getHoyContador()-1:0);
                    
                    dao.update(conexion);
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
