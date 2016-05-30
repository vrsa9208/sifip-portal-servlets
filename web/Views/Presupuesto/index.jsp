
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container-fluid">
    <h3>Presupuesto</h3>
    <h5 style="margin-bottom: 2em;">Lista</h5>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Id</th>
                <th>Descripción</th>
                <th>Fecha Inicio</th>
                <th>Fecha Fin</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.model}" var="item">
                <tr>
                    <td><c:out value="${item.id}" /></td>
                    <td><c:out value="${item.descripcion}" /></td>
                    <td><fmt:formatDate value="${item.fechaInicio.time}" pattern="yyyy-MM-dd" /></td>
                    <td><fmt:formatDate value="${item.fechaFin.time}" pattern="yyyy-MM-dd" /></td>
                    <td>
                        <a href="<c:url value="Presupuesto?action=editar&id=${item.id}" />">Editar</a> |
                        <a href="<c:url value="Presupuesto?action=detalles&id=${item.id}" />">Detalles</a> |
                        <a href="<c:url value="Presupuesto?action=eliminar&id=${item.id}" />">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="<c:url value="Presupuesto?action=agregar" />">Agregar</a>
</div>