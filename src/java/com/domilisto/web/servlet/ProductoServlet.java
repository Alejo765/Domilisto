package com.domilisto.web.servlet;

import com.domilisto.web.modelo.ConexionDB;
import com.domilisto.web.modelo.Producto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        try (Connection conn = ConexionDB.getConnection()) {
            switch (accion) {
                case "nuevo":
                    request.getRequestDispatcher("/admin/nuevoProducto.jsp").forward(request, response);
                    break;

                case "editar":
                    int idEditar = Integer.parseInt(request.getParameter("id"));
                    PreparedStatement psEditar = conn.prepareStatement("SELECT * FROM productos WHERE id = ?");
                    psEditar.setInt(1, idEditar);
                    ResultSet rsEditar = psEditar.executeQuery();
                    if (rsEditar.next()) {
                        Producto producto = new Producto(
                            rsEditar.getInt("id"),
                            rsEditar.getString("nombre"),
                            rsEditar.getString("descripcion"),
                            rsEditar.getDouble("precio"),
                            rsEditar.getString("categoria"),
                            rsEditar.getString("imagen_url")
                        );
                        request.setAttribute("producto", producto);
                        request.getRequestDispatcher("/admin/editarProducto.jsp").forward(request, response);
                    }
                    break;

                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    PreparedStatement psEliminar = conn.prepareStatement("DELETE FROM productos WHERE id = ?");
                    psEliminar.setInt(1, idEliminar);
                    psEliminar.executeUpdate();
                    response.sendRedirect("ProductoServlet");
                    break;

                default: // listar
                    List<Producto> productos = new ArrayList<>();
                    PreparedStatement ps = conn.prepareStatement("SELECT * FROM productos");
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        productos.add(new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getDouble("precio"),
                            rs.getString("categoria"),
                            rs.getString("imagen_url")
                        ));
                    }
                    request.setAttribute("productos", productos);
                    request.getRequestDispatcher("/admin/dashboard.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin/dashboard.jsp?error=db");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = ConexionDB.getConnection()) {
            String id = request.getParameter("id");
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            double precio = 0.0;
            try {
                precio = Double.parseDouble(request.getParameter("precio"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            String categoria = request.getParameter("categoria");
            String imagenUrl = request.getParameter("imagen_url");

            if (id == null || id.isEmpty()) {
                PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO productos(nombre, descripcion, precio, categoria, imagen_url) VALUES (?, ?, ?, ?, ?)");
                ps.setString(1, nombre);
                ps.setString(2, descripcion);
                ps.setDouble(3, precio);
                ps.setString(4, categoria);
                ps.setString(5, imagenUrl);
                ps.executeUpdate();
            } else {
                PreparedStatement ps = conn.prepareStatement(
                    "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, categoria = ?, imagen_url = ? WHERE id = ?");
                ps.setString(1, nombre);
                ps.setString(2, descripcion);
                ps.setDouble(3, precio);
                ps.setString(4, categoria);
                ps.setString(5, imagenUrl);
                ps.setInt(6, Integer.parseInt(id));
                ps.executeUpdate();
            }

            response.sendRedirect("ProductoServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin/dashboard.jsp?error=crud");
        }
    }
}


