<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Menú del Cliente - Domilisto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-white">
<div class="container mt-5">
    <h2 class="mb-4 text-primary">¡Bienvenido al Menú de Domilisto!</h2>
    <p class="mb-3">Selecciona tus productos favoritos y haz tu pedido rápido.</p>
    
    <!-- Productos disponibles -->
    <div class="row">
        <div class="col-md-4">
            <div class="card">
                <img src="https://via.placeholder.com/150" class="card-img-top" alt="Hamburguesa">
                <div class="card-body">
                    <h5 class="card-title">Hamburguesa Clásica</h5>
                    <p class="card-text">$12.000</p>
                    <button class="btn btn-success">Agregar</button>
                </div>
            </div>
        </div>
        <!-- Aquí puedes agregar más productos si lo deseas -->
    </div>

    <!-- Botón para cerrar sesión -->
    <div class="mt-4">
        <form action="<%= request.getContextPath() %>/LogoutServlet" method="post">
            <button type="submit" class="btn btn-danger w-100">Cerrar sesión</button>
        </form>
    </div>
</div>

<!-- Bootstrap JS (se puede eliminar si no se usa para interacciones adicionales) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


