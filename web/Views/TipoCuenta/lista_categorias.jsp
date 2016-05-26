<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid">
    <h2>Lista de Tipos de Cuenta</h2>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Id</th>
                <th>Descripción</th>
                <th>Activo</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.listaTiposDeCuenta}" var="tipoCuenta">
                <tr>
                    <td><c:out value="${tipoCuenta.id}" /></td>
                    <td><c:out value="${tipoCuenta.descripcion}" /></td>
                    <td><input type="checkbox" disabled="true" checked="<c:out value="${tipoCuenta.activo}" />" /></td>
                    <td>
                        <button class="btn btn-link">
                            <a href="<c:url value="TipoDeCuenta?action=delete&id=${tipoCuenta.id}" />">
                                Eliminar &nbsp; &nbsp;<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </a>
                        </button>
                        <button class="btn btn-link">
                            <a href="<c:url value="TipoDeCuenta?action=edit&id=${tipoCuenta.id}" />">
                                Editar &nbsp; &nbsp;<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                            </a>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <button class="btn btn-default">
        <a href="<c:url value="TipoDeCuenta?action=add" />">Agregar &nbsp; &nbsp;<span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>
    </button>
</div>