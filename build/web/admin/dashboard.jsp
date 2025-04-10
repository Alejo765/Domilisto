<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.domilisto.web.modelo.Producto" %>
<%
    List<Producto> listaProductos = (List<Producto>) request.getAttribute("productos");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel de Administración</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>Lista de Productos</h3>
        <a href="nuevoProducto.jsp" class="btn btn-primary">Agregar Producto</a>
    </div>

    <div class="table-responsive shadow rounded">
        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Precio</th>
                    <th>Categoría</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
            <%
                if (listaProductos != null && !listaProductos.isEmpty()) {
                    for (Producto p : listaProductos) {
            %>
                <tr>
                    <td><%= p.getId() %></td>
                    <td><%= p.getNombre() %></td>
                    <td><%= p.getDescripcion() %></td>
                    <td>$<%= p.getPrecio() %></td>
                    <td><%= p.getCategoria() %></td>
                    <td>
                        <form action="<%=request.getContextPath()%>/ProductoServlet" method="get" style="display:inline;">
                            <input type="hidden" name="accion" value="editar" />
                            <input type="hidden" name="id" value="<%= p.getId() %>" />
                            <input type="hidden" name="nombre" value="<%= p.getNombre() %>" />
                            <input type="hidden" name="descripcion" value="<%= p.getDescripcion() %>" />
                            <input type="hidden" name="precio" value="<%= p.getPrecio() %>" />
                            <input type="hidden" name="categoria" value="<%= p.getCategoria() %>" />
                            <button type="submit" class="btn btn-sm btn-warning">Editar</button>
                        </form>
                        <form action="<%=request.getContextPath()%>/ProductoServlet" method="post" style="display:inline;">
                            <input type="hidden" name="accion" value="eliminar" />
                            <input type="hidden" name="id" value="<%= p.getId() %>" />
                            <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('¿Eliminar este producto?')">Eliminar</button>
                        </form>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="6" class="text-center">No hay productos registrados.</td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>




