<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String nombre = request.getParameter("nombre") != null ? request.getParameter("nombre") : "";
    String descripcion = request.getParameter("descripcion") != null ? request.getParameter("descripcion") : "";
    String categoria = request.getParameter("categoria") != null ? request.getParameter("categoria") : "";

    String precioStr = request.getParameter("precio");
    String precioInput = ""; // Valor por defecto vacío

    // Validación para asegurar que el precio es un número válido
    if (precioStr != null && !precioStr.trim().isEmpty()) {
        try {
            Double precio = Double.parseDouble(precioStr); // Intentamos parsear el precio
            if (precio >= 0) { // Solo permitimos precios mayores o iguales a cero
                precioInput = String.format("%.2f", precio); // Formateamos con dos decimales
            } else {
                precioInput = ""; // Si el precio es negativo, lo dejamos vacío
            }
        } catch (NumberFormatException e) {
            precioInput = ""; // Si no se puede parsear, dejamos vacío
        }
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Nuevo Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">Agregar Nuevo Producto</h4>
        </div>
        <div class="card-body">
            <form action="<%=request.getContextPath()%>/ProductoServlet" method="post">
                <input type="hidden" name="accion" value="agregar" />

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
                    <label for="categoria" class="form-label">Categoría</label>
                    <input type="text" class="form-control" id="categoria" name="categoria"
                           value="<%= categoria %>" required />
                </div>

                <button type="submit" class="btn btn-success">Guardar Producto</button>
                <a href="admin/dashboard.jsp" class="btn btn-secondary">Cancelar</a>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>





