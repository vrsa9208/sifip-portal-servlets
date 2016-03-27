<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid">
    <h2>Lista de Categorias</h2>
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
            <c:forEach items="${requestScope.listaCategorias}" var="categoria">
                <tr>
                    <td><c:out value="${categoria.id}" /></td>
                    <td><c:out value="${categoria.descripcion}" /></td>
                    <td><input type="checkbox" disabled="true" checked="<c:out value="${categoria.activo}" />" /></td>
                    <td>
                        <button class="btn btn-link">
                            <a href="<c:url value="Categoria?action=delete&id=${categoria.id}" />">
                                Eliminar &nbsp; &nbsp;<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </a>
                        </button>
                        <button class="btn btn-link">
                            <a href="<c:url value="Categoria?action=edit&id=${categoria.id}" />">
                                Editar &nbsp; &nbsp;<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                            </a>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <button class="btn btn-default">
        <a href="<c:url value="Categoria?action=add" />">Agregar &nbsp; &nbsp;<span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>
    </button>
</div>