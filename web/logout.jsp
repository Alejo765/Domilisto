<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%
    HttpSession sesion = request.getSession(false);
    if (sesion != null) {
        sesion.invalidate(); // Elimina la sesi�n
    }
    response.sendRedirect("login.jsp");
%>

