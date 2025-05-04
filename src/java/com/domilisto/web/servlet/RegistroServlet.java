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

/**
 * Servlet encargado de procesar el registro de nuevos usuarios.
 * Este servlet utiliza el método POST para recibir los datos desde un formulario HTML.
 * Verifica si el correo ya existe en la base de datos antes de insertar un nuevo usuario.
 * Tecnología utilizada: Servlets, JDBC, HTML5
 * Autor: Mario Alejandro Peña Martínez
 */
public class RegistroServlet extends HttpServlet {

    /**
     * Maneja las solicitudes HTTP POST para registrar un nuevo usuario.
     * @param request solicitud del cliente con los datos del formulario.
     * @param response respuesta que se envía al cliente (redirección).
     */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recuperar los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        String rol = request.getParameter("rol");

        try (Connection conn = ConexionDB.getConnection()) {
            if (conn != null) {

                // Verificar si el correo ya existe en la base de datos
                String checkSql = "SELECT id FROM usuarios WHERE correo = ?";
                try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                    checkStmt.setString(1, correo);
                    ResultSet rs = checkStmt.executeQuery();

                    if (rs.next()) {
                        // Si el correo ya está registrado, redirigir con mensaje de error
                        response.sendRedirect("registro.jsp?error=correo_existente");
                        return;
                    }
                }

                // Insertar nuevo usuario en la base de datos
                String insertSql = "INSERT INTO usuarios (nombre, correo, clave, rol) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                    ps.setString(1, nombre);
                    ps.setString(2, correo);
                    ps.setString(3, clave); // En producción se recomienda cifrar la contraseña
                    ps.setString(4, rol);

                    ps.executeUpdate();

                    // Redirigir al login tras un registro exitoso
                    response.sendRedirect("login.jsp");
                }

            } else {
                // Si no hay conexión a la base de datos, redirigir con error
                response.sendRedirect("registro.jsp?error=conexion_bd");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // En caso de excepción general, redirigir con error de servidor
            response.sendRedirect("registro.jsp?error=servidor");
        }
    }
}






