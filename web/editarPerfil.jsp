<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.domilisto.web.modelo.Usuario" %>
<%
    Usuario user = (Usuario) request.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Perfil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2>✏️ Editar Perfil</h2>
    <div class="card shadow mt-4">
        <div class="card-body">
            <form action="<%= request.getContextPath() %>/ProfileServlet" method="post">
                <input type="hidden" name="accion" value="editar" />
                <input type="hidden" name="correo" value="<%= user.getCorreo() %>" />

                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" value="<%= user.getNombre() %>" required />
                </div>

                <div class="mb-3">
                    <label for="edad" class="form-label">Edad</label>
                    <input type="number" class="form-control" id="edad" name="edad" value="<%= user.getEdad() %>" required />
                </div>

                <div class="mb-3">
                    <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento</label>
                    <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" value="<%= user.getFechaNacimiento() %>" required />
                </div>

                <div class="mb-3">
                    <label for="numero" class="form-label">Número de Teléfono</label>
                    <input type="text" class="form-control" id="numero" name="numero" value="<%= user.getNumero() %>" required />
                </div>

                <div class="mb-3">
                    <label for="direccion" class="form-label">Dirección</label>
                    <input type="text" class="form-control" id="direccion" name="direccion" value="<%= user.getDireccion() %>" required />
                </div>

                <button type="submit" class="btn btn-success">Guardar Cambios</button>
                <a href="profile.jsp" class="btn btn-secondary">Cancelar</a>
            </form>
        </div>
    </div>
</div>

</body>
</html>

