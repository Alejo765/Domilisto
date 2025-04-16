<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Pedidos - Empleado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <!-- Encabezado con botón de cerrar sesión -->
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="text-info">Pedidos Pendientes</h2>
            <!-- Formulario para cerrar sesión (cambia el método a POST) -->
            <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
                <button type="submit" class="btn btn-danger">Cerrar Sesión</button>
            </form>
        </div>

        <p>Lista de pedidos realizados por los clientes:</p>

        <!-- Tabla de pedidos -->
        <table class="table table-striped">
            <thead>
                <tr>
                    <th># Pedido</th>
                    <th>Cliente</th>
                    <th>Producto</th>
                    <th>Estado</th>
                    <th>Acción</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>001</td>
                    <td>Ana Torres</td>
                    <td>Pizza</td>
                    <td>Pendiente</td>
                    <td><button class="btn btn-sm btn-success">Marcar como Listo</button></td>
                </tr>
                <!-- Aquí puedes cargar más filas desde la base de datos -->
            </tbody>
        </table>
    </div>
</body>
</html>

