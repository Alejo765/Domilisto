<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.domilisto.web.modelo.Producto" %>
<%@ page import="com.domilisto.web.modelo.Usuario" %>
<%
    // Recupera los datos del usuario desde la sesión
    String usuario = (String) session.getAttribute("usuario");
    String rol = (String) session.getAttribute("rol");

    // Si no hay sesión o el rol no es 'admin', redirige a login
    if (usuario == null || rol == null || !rol.equals("admin")) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel de Administración</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-4">
    <!-- Información de Usuario y enlace a Perfil -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <div>
            <h5>👤 Usuario: <strong><%= usuario != null ? usuario : "Invitado" %></strong></h5>
            <p class="text-muted mb-0">Rol: <%= rol != null ? rol : "Sin rol" %></p>
        </div>
        <div>
            <a href="../profile.jsp" class="btn btn-info">Editar Perfil</a>
            <form action="<%=request.getContextPath()%>/LogoutServlet" method="post" style="display:inline;">
                <button type="submit" class="btn btn-danger">Cerrar Sesión</button>
            </form>
        </div>
    </div>

    <!-- Panel de navegación -->
    <div class="row mb-4">
        <div class="col-md-3">
            <div class="list-group">
                <a href="dashboard.jsp" class="list-group-item list-group-item-action active">Dashboard</a>
                <a href="listaUsuarios.jsp" class="list-group-item list-group-item-action">Usuarios</a>
                <a href="listaProductos.jsp" class="list-group-item list-group-item-action">Productos</a>
                <a href="reportes.jsp" class="list-group-item list-group-item-action">Reportes</a>
            </div>
        </div>

        <!-- Contenido del Dashboard -->
        <div class="col-md-9">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h4 class="mb-0">Bienvenido al Panel de Administración</h4>
                </div>
                <div class="card-body">
                    <h5 class="card-title">Resumen del Sistema</h5>
                    <p class="card-text">Aquí puedes gestionar todos los aspectos del sistema de Domilisto.</p>

                    <div class="row">
                        <div class="col-md-4">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <h5 class="card-title">Usuarios Registrados</h5>
                                    <p class="card-text">Administra los usuarios del sistema.</p>
                                    <a href="listaUsuarios.jsp" class="btn btn-info">Ver Usuarios</a>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <h5 class="card-title">Productos</h5>
                                    <p class="card-text">Administra los productos disponibles para los clientes.</p>
                                    <a href="listaProductos.jsp" class="btn btn-info">Ver Productos</a>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="card shadow-sm">
                                <div class="card-body">
                                    <h5 class="card-title">Reportes</h5>
                                    <p class="card-text">Accede a los reportes generados por el sistema.</p>
                                    <a href="reportes.jsp" class="btn btn-info">Ver Reportes</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>









