package com.domilisto.web.servlet;

import com.domilisto.web.modelo.ConexionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        String rol = request.getParameter("rol");

        try (Connection conn = ConexionDB.getConnection()) {
            if (conn != null) {
                String sql = "INSERT INTO usuarios (nombre, correo, clave, rol) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, nombre);
                    ps.setString(2, correo);
                    ps.setString(3, clave);
                    ps.setString(4, rol);
                    ps.executeUpdate();
                    response.sendRedirect("login.jsp");
                }
            } else {
                response.sendRedirect("registro.jsp?error=bd");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("registro.jsp?error=servidor");
        }
    }
}



