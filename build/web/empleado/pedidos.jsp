<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Pedidos - Empleado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="mb-4 text-info">Pedidos Pendientes</h2>
    <p>Lista de pedidos realizados por los clientes:</p>

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
        </tbody>
    </table>
</div>
</body>
</html>



