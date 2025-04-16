<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión - Domilisto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="text-center mb-4">Iniciar Sesión</h2>

    <!-- Mostrar mensaje de error si existe -->
    <%
        String error = request.getParameter("error");
        if (error != null) {
    %>
        <div class="alert alert-danger text-center" role="alert">
            <%
                if ("datos_invalidos".equals(error)) {
                    out.print("Correo o contraseña incorrectos. Intenta de nuevo.");
                } else if ("rol_desconocido".equals(error)) {
                    out.print("Rol de usuario no reconocido. Contacta al administrador.");
                } else if ("bd".equals(error)) {
                    out.print("Error al conectar con la base de datos. Por favor, intenta nuevamente.");
                } else {
                    out.print("Ha ocurrido un error desconocido.");
                }
            %>
        </div>
    <%
        }
    %>

    <form action="LoginServlet" method="post" class="col-md-6 mx-auto bg-white p-4 rounded shadow-sm">
        <div class="mb-3">
            <label for="correo" class="form-label">Correo electrónico</label>
            <input type="email" class="form-control" name="correo" id="correo" required>
        </div>
        <div class="mb-3">
            <label for="clave" class="form-label">Contraseña</label>
            <input type="password" class="form-control" name="clave" id="clave" required>
        </div>
        <button type="submit" class="btn btn-primary w-100">Ingresar</button>
    </form>

    <!-- Enlace para volver al inicio -->
    <div class="mt-3 text-center">
        <p>¿No tienes cuenta o deseas volver al inicio?</p>
        <a href="index.html" class="btn btn-secondary">Volver al Inicio</a>
    </div>
</div>
</body>
</html>




