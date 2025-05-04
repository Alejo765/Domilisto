package com.domilisto.web.servlet;

import com.domilisto.web.modelo.ConexionDB;
import com.domilisto.web.modelo.Usuario; // Asegúrate de tener esta clase con sus getters/setters
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoadProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String correo = (String) session.getAttribute("correo");

        if (correo != null) {
            try (Connection conn = ConexionDB.getConnection()) {
                String sql = "SELECT nombre, correo, edad, fechaNacimiento, numero, direccion FROM usuarios WHERE correo = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, correo);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            Usuario user = new Usuario();
                            user.setNombre(rs.getString("nombre"));
                            user.setCorreo(rs.getString("correo"));
                            user.setEdad(rs.getInt("edad"));
                            user.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                            user.setNumero(rs.getString("numero"));
                            user.setDireccion(rs.getString("direccion"));

                            request.setAttribute("user", user);
                            request.getRequestDispatcher("profile.jsp").forward(request, response);
                        } else {
                            response.sendRedirect("login.jsp");
                        }
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
