<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Perfil de Usuario - Domilisto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="text-center mb-4">Perfil de Usuario</h2>

    <div class="row">
        <div class="col-md-6 mx-auto bg-white p-4 rounded shadow-sm">
            <h4>Información Personal</h4>
            <form action="UpdateProfileServlet" method="post">
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre completo</label>
                    <input type="text" class="form-control" name="nombre" id="nombre" value="${user.nombre}" required>
                </div>
                <div class="mb-3">
                    <label for="correo" class="form-label">Correo electrónico</label>
                    <input type="email" class="form-control" name="correo" id="correo" value="${user.correo}" required>
                </div>
                <div class="mb-3">
                    <label for="edad" class="form-label">Edad</label>
                    <input type="number" class="form-control" name="edad" id="edad" value="${user.edad}" required>
                </div>
                <div class="mb-3">
                    <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento</label>
                    <input type="date" class="form-control" name="fechaNacimiento" id="fechaNacimiento" value="${user.fechaNacimiento}" required>
                </div>
                <div class="mb-3">
                    <label for="numero" class="form-label">Número de Teléfono</label>
                    <input type="text" class="form-control" name="numero" id="numero" value="${user.numero}" required>
                </div>

                <!-- Campo para dirección (solo si es un cliente) -->
                <div class="mb-3" id="direccionField">
                    <label for="direccion" class="form-label">Dirección</label>
                    <input type="text" class="form-control" name="direccion" id="direccion" value="${user.direccion}">
                </div>

                <button type="submit" class="btn btn-success w-100">Actualizar Información</button>
            </form>
        </div>
    </div>

    <div class="mt-4 text-center">
        <a href="index.html" class="btn btn-secondary">Volver al Inicio</a>
    </div>
</div>
</body>
</html>

