package com.domilisto.web.servlet;

import com.domilisto.web.modelo.ConexionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
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
            String edadStr = request.getParameter("edad");
            String fechaNacimientoStr = request.getParameter("fechaNacimiento");
            String telefono = request.getParameter("numero"); // input se llama "numero"
            String direccion = request.getParameter("direccion");

            try {
                int edad = Integer.parseInt(edadStr);
                Date fechaNacimiento = Date.valueOf(fechaNacimientoStr);

                try (Connection conn = ConexionDB.obtenerConexion()) {
                    String sql = "UPDATE usuarios SET nombre = ?, edad = ?, fecha_nacimiento = ?, telefono = ?, direccion = ? WHERE correo = ?";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setString(1, nombre);
                        ps.setInt(2, edad);
                        ps.setDate(3, fechaNacimiento);
                        ps.setString(4, telefono);
                        ps.setString(5, direccion);
                        ps.setString(6, correo);

                        ps.executeUpdate();

                        // Redirige a perfil actualizado
                        response.sendRedirect("perfil.jsp");
                    }
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

