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

    // Constantes para las rutas JSP
    private static final String DASHBOARD_JSP = "/admin/dashboard.jsp";
    private static final String NUEVO_PRODUCTO_JSP = "/admin/nuevoProducto.jsp";
    private static final String EDITAR_PRODUCTO_JSP = "/admin/editarProducto.jsp";

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar"; // Si no envían acción, se lista por defecto

        try (Connection conn = ConexionDB.getConnection()) {
            switch (accion) {
                case "nuevo":
                    // Redirigir al formulario de nuevo producto
                    request.getRequestDispatcher(NUEVO_PRODUCTO_JSP).forward(request, response);
                    break;

                case "editar":
                    // Buscar producto por ID y enviarlo a editar
                    editarProducto(request, response, conn);
                    break;

                case "eliminar":
                    // Eliminar producto por ID
                    eliminarProducto(request, response, conn);
                    break;

                default: // listar
                    // Listar todos los productos
                    listarProductos(request, response, conn);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(DASHBOARD_JSP + "?error=db");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = ConexionDB.getConnection()) {
            guardarProducto(request, conn);
            response.sendRedirect("ProductoServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(DASHBOARD_JSP + "?error=crud");
        }
    }

    // Método para listar todos los productos
    private void listarProductos(HttpServletRequest request, HttpServletResponse response, Connection conn) throws SQLException, ServletException, IOException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
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
        }
        request.setAttribute("productos", productos);
        request.getRequestDispatcher(DASHBOARD_JSP).forward(request, response);
    }

    // Método para editar un producto existente
    private void editarProducto(HttpServletRequest request, HttpServletResponse response, Connection conn) throws SQLException, ServletException, IOException {
        int idEditar = Integer.parseInt(request.getParameter("id"));
        String sql = "SELECT * FROM productos WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEditar);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Producto producto = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getString("categoria"),
                        rs.getString("imagen_url")
                    );
                    request.setAttribute("producto", producto);
                    request.getRequestDispatcher(EDITAR_PRODUCTO_JSP).forward(request, response);
                } else {
                    response.sendRedirect(DASHBOARD_JSP + "?error=productoNoEncontrado");
                }
            }
        }
    }

    // Método para guardar un nuevo producto
    private void guardarProducto(HttpServletRequest request, Connection conn) throws SQLException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String categoria = request.getParameter("categoria");
        String imagenUrl = request.getParameter("imagen_url");

        String sql = "INSERT INTO productos (nombre, descripcion, precio, categoria, imagen_url) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setDouble(3, precio);
            ps.setString(4, categoria);
            ps.setString(5, imagenUrl);
            ps.executeUpdate();
        }
    }

    // Método para eliminar un producto
    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response, Connection conn) throws SQLException, IOException {
        int idEliminar = Integer.parseInt(request.getParameter("id"));
        String sql = "DELETE FROM productos WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEliminar);
            ps.executeUpdate();
        }
        response.sendRedirect("ProductoServlet");
    }
}



