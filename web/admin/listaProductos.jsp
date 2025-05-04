<%@ page import="java.sql.*, com.domilisto.web.modelo.ConexionDB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String usuario = (String) session.getAttribute("usuario");
    String rol = (String) session.getAttribute("rol");
    if (usuario == null || rol == null || !rol.equals("admin")) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h3>Productos Disponibles</h3>
    <table class="table table-bordered table-striped mt-3">
        <thead>
            <tr>
                <th>ID</th><th>Nombre</th><th>Precio</th><th>Descripción</th>
            </tr>
        </thead>
        <tbody>
        <%
            try (Connection conn = ConexionDB.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT id, nombre, precio, descripcion FROM productos")) {
                while (rs.next()) {
        %>
            <tr>
                <td><%= rs.getInt("id") %></td>
                <td><%= rs.getString("nombre") %></td>
                <td>$<%= rs.getDouble("precio") %></td>
                <td><%= rs.getString("descripcion") %></td>
            </tr>
        <%
                }
            } catch (Exception e) {
                out.println("<tr><td colspan='4'>Error: " + e.getMessage() + "</td></tr>");
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>


