package com.domilisto.web.servlet;

import com.domilisto.web.modelo.ConexionDB;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");

        try (Connection conn = ConexionDB.getConnection()) {
            if (conn != null) {
                String sql = "SELECT * FROM usuarios WHERE correo = ? AND clave = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, correo);
                    ps.setString(2, clave);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String rol = rs.getString("rol");
                            HttpSession session = request.getSession();
                            session.setAttribute("usuario", correo);
                            session.setAttribute("rol", rol);

                            switch (rol) {
                                case "admin":
                                    response.sendRedirect("admin/dashboard.jsp");
                                    break;
                                case "empleado":
                                    response.sendRedirect("empleado/pedidos.jsp");
                                    break;
                                case "cliente":
                                    response.sendRedirect("cliente/menu.jsp");
                                    break;
                                case "soporte":
                                    response.sendRedirect("soporte/consultas.jsp");
                                    break;
                                default:
                                    response.sendRedirect("login.jsp?error=rol");
                                    break;
                            }
                        } else {
                            response.sendRedirect("login.jsp?error=datos");
                        }
                    }
                }
            } else {
                response.sendRedirect("login.jsp?error=bd");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=servidor");
        }
    }
}




