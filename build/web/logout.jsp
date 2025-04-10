<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%
    HttpSession sesion = request.getSession(false);
    if (sesion != null) {
        sesion.invalidate(); // Elimina la sesión
    }
    response.sendRedirect("login.jsp");
%>

