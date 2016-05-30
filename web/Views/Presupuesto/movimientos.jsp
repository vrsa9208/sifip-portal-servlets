<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container-fluid">
    <h3><c:out value="${requestScope.presupuesto.descripcion}" /></h3>
    <h5 style="margin-bottom: 2em;"><c:out value="${requestScope.title}" /></h5>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Descripción</th>
                <th>Fecha</th>
                <th>Cantidad</th>
                <th>Tipo Movimiento</th>
                <th>Categoria</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.model}" var="item">
                <tr>
                    <td><c:out value="${item.descripcion}" /></td>
                    <td><fmt:formatDate value="${item.fechaCreacion.time}" pattern="yyyy-MM-dd" /></td>
                    <td>$ <c:out value="${item.cantidad}" /></td>
                    <td><c:out value="${item.idTipoMovimiento}" /></td>
                    <td><c:out value="${item.idCategoria}" /></td>
                    <td>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="<c:url value="Presupuesto?action=detalles&id=${requestScope.presupuesto.id}" />">Regresar</a> |
    <a href="<c:url value="Presupuesto?action=agregar" />">Agregar</a>
</div>