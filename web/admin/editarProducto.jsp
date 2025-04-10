<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("id") != null ? request.getParameter("id") : "";
    String nombre = request.getParameter("nombre") != null ? request.getParameter("nombre") : "";
    String descripcion = request.getParameter("descripcion") != null ? request.getParameter("descripcion") : "";
    String categoria = request.getParameter("categoria") != null ? request.getParameter("categoria") : "";

    String precioStr = request.getParameter("precio");
    String precioValido = "";
    try {
        if (precioStr != null && !precioStr.trim().isEmpty()) {
            Double.parseDouble(precioStr); // validamos que sea número
            precioValido = precioStr;
        }
    } catch (NumberFormatException e) {
        precioValido = "";
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header bg-warning text-dark">
            <h4 class="mb-0">Editar Producto</h4>
        </div>
        <div class="card-body">
            <form action="<%=request.getContextPath()%>/ProductoServlet" method="post">
                <input type="hidden" name="accion" value="editar" />
                <input type="hidden" name="id" value="<%= id %>" />

                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre del producto</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" 
                           value="<%= nombre %>" required />
                </div>

                <div class="mb-3">
                    <label for="descripcion" class="form-label">Descripción</label>
                    <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required><%= descripcion %></textarea>
                </div>

                <div class="mb-3">
                    <label for="precio" class="form-label">Precio</label>
                    <input type="number" class="form-control" id="precio" name="precio" step="0.01"
                           <%= !precioValido.isEmpty() ? "value=\"" + precioValido + "\"" : "" %> required />
                </div>

                <div class="mb-3">
                    <label for="categoria" class="form-label">Categoría</label>
                    <input type="text" class="form-control" id="categoria" name="categoria" 
                           value="<%= categoria %>" required />
                </div>

                <button type="submit" class="btn btn-warning">Actualizar Producto</button>
                <a href="admin/dashboard.jsp" class="btn btn-secondary">Cancelar</a>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


