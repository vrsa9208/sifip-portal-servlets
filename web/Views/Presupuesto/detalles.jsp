<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container-fluid">
    <h3>Presupuesto</h3>
    <h5 style="margin-bottom: 2em;">Detalles</h5>
    <div>
        <div class="form-group">
            <label for="descripcion">Descripción</label>
            <input disabled="true" type="text" class="form-control" id="descripcion" name="descripcion" placeholder="Descripción" 
                   value="<c:out value="${requestScope.model.descripcion}" />">
        </div>
        <div class="form-group">
            <label for="fechaInicio">Fecha Inicio</label>
            <input disabled="true" type="date" class="form-control" id="fechaInicio" name="fechaInicio" placeholder="dd/mm/yyyy"
                   value="<fmt:formatDate value="${requestScope.model.fechaInicio.time}" pattern="yyyy-MM-dd" />">
        </div>
        <div class="form-group">
            <label for="fechaFin">Fecha Fin</label>
            <input disabled="true" type="date" class="form-control" id="fechaFin" name="fechaFin" placeholder="dd/mm/yyyy"
                   value="<fmt:formatDate value="${requestScope.model.fechaFin.time}" pattern="yyyy-MM-dd" />">
        </div>
        <div class="form-group pull-right">
            <a href="<c:url value="Presupuesto?action=movimientos&id=${requestScope.model.id}" />">Movimientos</a> |
            <a href="<c:url value="Presupuesto?action=editar&id=${requestScope.model.id}" />">Editar</a> |
            <a href="<c:url value="Presupuesto" />">Regresar</a>
        </div>
    </div>
</div>