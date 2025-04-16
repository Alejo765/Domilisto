package com.domilisto.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Maneja solicitudes GET (por ejemplo, al ingresar directamente en la URL)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cerrarSesion(request, response);
    }

    // Maneja solicitudes POST (por ejemplo, al hacer logout desde un botón)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cerrarSesion(request, response);
    }

    // Método común para cerrar sesión y redirigir
    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Obtener la sesión actual (sin crear una nueva si no existe)
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate(); // Cierra sesión
        }

        // Redirige al login
        response.sendRedirect(request.getContextPath() + "/index.html");
    }
}





