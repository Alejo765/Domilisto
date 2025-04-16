package com.domilisto.web.servlet;

import com.domilisto.web.modelo.Usuario;
import com.domilisto.web.modelo.ConexionDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");

        try (Connection con = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM usuarios WHERE correo = ? AND clave = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, clave);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                String rol = rs.getString("rol");
                session.setAttribute("usuario", rs.getString("nombre"));
                session.setAttribute("rol", rol);

                // Redirige según el rol
                switch (rol) {
                    case "cliente":
                        response.sendRedirect("cliente/menu.jsp");
                        break;
                    case "empleado":
                        response.sendRedirect("empleado/pedidos.jsp");
                        break;
                    case "soporte":
                        response.sendRedirect("soporte/consultas.jsp");
                        break;
                    case "admin":
                        response.sendRedirect("admin/dashboard.jsp");
                        break;
                    default:
                        response.sendRedirect("login.jsp?error=rol_desconocido");
                }
            } else {
                // Datos incorrectos
                response.sendRedirect("login.jsp?error=datos_invalidos");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=bd");
        }
    }
}





