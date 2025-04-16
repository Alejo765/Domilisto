package com.domilisto.web.servlet;

import com.domilisto.web.modelo.Producto;
import com.domilisto.web.modelo.ConexionDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuClienteServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producto> productos = new ArrayList<>();
        
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM productos"; // Suponiendo que la tabla de productos se llama 'productos'
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    // Ahora creamos el producto con todos los campos necesarios
                    Producto producto = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"), // Añadido el campo descripcion
                        rs.getDouble("precio"),
                        rs.getString("categoria"), // Añadido el campo categoria
                        rs.getString("imagen_url") // Añadido el campo imagen_url
                    );
                    productos.add(producto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        request.setAttribute("productos", productos);
        request.getRequestDispatcher("menu.jsp").forward(request, response);
    }
}

