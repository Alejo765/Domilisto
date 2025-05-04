<%@ page import="java.sql.*, com.domilisto.web.modelo.ConexionDB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String usuario = (String) session.getAttribute("usuario");
    String rol = (String) session.getAttribute("rol");
    if (usuario == null || rol == null || !rol.equals("admin")) {
        response.sendRedirect("../login.jsp");
        return;
    }

    int totalUsuarios = 0;
    int totalProductos = 0;

    try (Connection conn = ConexionDB.getConnection()) {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM usuarios");
        if (rs.next()) totalUsuarios = rs.getInt(1);

        rs = stmt.executeQuery("SELECT COUNT(*) FROM productos");
        if (rs.next()) totalProductos = rs.getInt(1);
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reportes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h3>Reportes del Sistema</h3>
    <table class="table table-hover mt-3">
        <tr><th>Total de Usuarios</th><td><%= totalUsuarios %></td></tr>
        <tr><th>Total de Productos</th><td><%= totalProductos %></td></tr>
    </table>
</div>
</body>
</html>
