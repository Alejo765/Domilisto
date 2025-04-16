package com.domilisto.web.servlet;

import com.domilisto.web.modelo.ConexionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                // Verificar si el correo ya existe
                String checkSql = "SELECT id FROM usuarios WHERE correo = ?";
                try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                    checkStmt.setString(1, correo);
                    ResultSet rs = checkStmt.executeQuery();

                    if (rs.next()) {
                        // Ya existe un usuario con ese correo
                        response.sendRedirect("registro.jsp?error=correo_existente");
                        return;
                    }
                }

                // Insertar nuevo usuario
                String insertSql = "INSERT INTO usuarios (nombre, correo, clave, rol) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                    ps.setString(1, nombre);
                    ps.setString(2, correo);
                    ps.setString(3, clave);
                    ps.setString(4, rol);
                    ps.executeUpdate();

                    // Registro exitoso
                    response.sendRedirect("login.jsp");
                }

            } else {
                // Error al conectar con la base de datos
                response.sendRedirect("registro.jsp?error=bd");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Error del servidor o SQL
            response.sendRedirect("registro.jsp?error=servidor");
        }
    }
}





