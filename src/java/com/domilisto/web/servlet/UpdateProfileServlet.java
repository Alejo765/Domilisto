package com.domilisto.web.servlet;

import com.domilisto.web.modelo.ConexionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String correo = (String) session.getAttribute("correo");

        if (correo != null) {
            String nombre = request.getParameter("nombre");
            String edad = request.getParameter("edad");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String numero = request.getParameter("numero");
            String direccion = request.getParameter("direccion"); // Obtener la dirección

            try (Connection conn = ConexionDB.getConnection()) {
                String sql = "UPDATE usuarios SET nombre = ?, edad = ?, fechaNacimiento = ?, numero = ?, direccion = ? WHERE correo = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, nombre);
                    ps.setInt(2, Integer.parseInt(edad));
                    ps.setString(3, fechaNacimiento);
                    ps.setString(4, numero);
                    ps.setString(5, direccion);  // Actualizar la dirección
                    ps.setString(6, correo);
                    ps.executeUpdate();

                    response.sendRedirect("profile.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
