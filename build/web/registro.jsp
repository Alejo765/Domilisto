<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro - Domilisto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="text-center mb-4">Registro de Usuario</h2>

    <!-- Mensaje de error si existe -->
    <%
        String error = request.getParameter("error");
        if (error != null) {
    %>
        <div class="alert alert-danger text-center" role="alert">
            <%
                if ("correo_existente".equals(error)) {
                    out.print("El correo ingresado ya está registrado. Intenta con otro.");
                } else if ("bd".equals(error)) {
                    out.print("Error al conectar con la base de datos. Intenta más tarde.");
                } else {
                    out.print("Ocurrió un error inesperado. Intenta nuevamente.");
                }
            %>
        </div>
    <%
        }
    %>

    <!-- Formulario de registro -->
    <form action="RegistroServlet" method="post" class="col-md-6 mx-auto bg-white p-4 rounded shadow-sm">
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre completo</label>
            <input type="text" class="form-control" name="nombre" id="nombre" required>
        </div>
        <div class="mb-3">
            <label for="correo" class="form-label">Correo electrónico</label>
            <input type="email" class="form-control" name="correo" id="correo" required>
        </div>
        <div class="mb-3">
            <label for="clave" class="form-label">Contraseña</label>
            <input type="password" class="form-control" name="clave" id="clave" required>
        </div>
        <div class="mb-3">
            <label for="rol" class="form-label">Rol</label>
            <select name="rol" id="rol" class="form-select" required>
                <option value="cliente">Cliente</option>
                <option value="empleado">Empleado</option>
                <option value="soporte">Soporte</option>
                <option value="admin">Administrador</option>
            </select>
        </div>
        <button type="submit" class="btn btn-success w-100">Registrarse</button>
    </form>

    <!-- Enlace para volver al login o inicio -->
    <div class="mt-4 text-center">
        <p>¿Ya tienes cuenta? <a href="login.jsp">Inicia sesión aquí</a></p>
        <a href="index.html" class="btn btn-secondary mt-2">Volver al Inicio</a>
    </div>
</div>
</body>
</html>
