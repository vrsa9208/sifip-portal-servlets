<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="mx.com.vrsa9208.sifiplibrary.util.DateHelper" %>
<%@ page import="mx.com.vrsa9208.sifiplibrary.model.Usuario" %>
<div class="container-fluid">
    <h2 class="text-left">Mi Perfil</h2>
    <table class="table table-bordered">
        <tbody>
            <tr>
                <th>Nombre</th>
                <td><c:out value="${sessionScope.usuario.nombre}" /></td>
            </tr>
            <tr>
                <th>Primer Apellido</th>
                <td><c:out value="${sessionScope.usuario.primerApellido}" /></td>
            </tr>
            <tr>
                <th>Segundo Apellido</th>
                <td><c:out value="${sessionScope.usuario.segundoApellido}" /></td>
            </tr>
            <tr>
                <th>e-mail</th>
                <td><c:out value="${sessionScope.usuario.email}" /></td>
            </tr>
            <tr>
                <th>Fecha Creacion</th>
                <td>
                    <%
                        Usuario usuario = (Usuario) session.getAttribute("usuario");
                        out.print(DateHelper.CalendarToString(usuario.getFechaCreacion()));
                    %>
                </td>
            </tr>
            <tr>
                <th>Activo</th>
                <td>
                    <input type="checkbox" checked="<c:out value="${sessionScope.usuario.activo}" />" disabled>
                </td>
            </tr>
            <tr>
                <th>Perfil</th>
                <td>
                    <c:out value="${requestScope.perfilTexto}" />
                </td>
            </tr>
        </tbody>
    </table>
    <a href="<c:url value="Perfil?action=cambiarPassword" />">Cambiar Password</a>
</div>