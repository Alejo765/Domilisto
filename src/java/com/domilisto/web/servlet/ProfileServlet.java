package com.domilisto.web.servlet;

import com.domilisto.web.modelo.ConexionDB;
import com.domilisto.web.modelo.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  // Asegúrate de importar ResultSet
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String correo = (String) session.getAttribute("correo");

        if (correo != null) {
            try (Connection conn = ConexionDB.getConnection()) {
                String sql = "SELECT * FROM usuarios WHERE correo = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, correo);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        Usuario usuario = new Usuario();
                        usuario.setNombre(rs.getString("nombre"));
                        usuario.setCorreo(rs.getString("correo"));
                        usuario.setEdad(rs.getInt("edad"));
                        usuario.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                        usuario.setNumero(rs.getString("numero"));
                        usuario.setDireccion(rs.getString("direccion"));

                        request.setAttribute("user", usuario);
                        request.getRequestDispatcher("profile.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("login.jsp");
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if ("editar".equals(accion)) {
            String correo = request.getParameter("correo");
            String nombre = request.getParameter("nombre");
            int edad = Integer.parseInt(request.getParameter("edad"));
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String numero = request.getParameter("numero");
            String direccion = request.getParameter("direccion");

            try (Connection conn = ConexionDB.getConnection()) {
                String sql = "UPDATE usuarios SET nombre = ?, edad = ?, fechaNacimiento = ?, numero = ?, direccion = ? WHERE correo = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, nombre);
                    ps.setInt(2, edad);
                    ps.setString(3, fechaNacimiento);
                    ps.setString(4, numero);
                    ps.setString(5, direccion);
                    ps.setString(6, correo);

                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        HttpSession session = request.getSession();
                        session.setAttribute("correo", correo);
                        response.sendRedirect("profile.jsp");
                    } else {
                        response.sendRedirect("error.jsp");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        }
    }
}
