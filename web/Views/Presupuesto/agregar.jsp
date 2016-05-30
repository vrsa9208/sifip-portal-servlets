<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container-fluid">
    <h3>Presupuesto</h3>
    <h5 style="margin-bottom: 2em;">
        <c:out value="${requestScope.title}" />
    </h5>
    <form method="POST">
        <div class="form-group">
            <label for="descripcion">Descripción</label>
            <input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="Descripción" 
                   value="<c:out value="${requestScope.model.descripcion}" />">
        </div>
        <div class="form-group">
            <label for="fechaInicio">Fecha Inicio</label>
            <input type="date" class="form-control" id="fechaInicio" name="fechaInicio" placeholder="dd/mm/yyyy"
                   value="<fmt:formatDate value="${requestScope.model.fechaInicio.time}" pattern="yyyy-MM-dd" />">
        </div>
        <div class="form-group">
            <label for="fechaFin">Fecha Fin</label>
            <input type="date" class="form-control" id="fechaFin" name="fechaFin" placeholder="dd/mm/yyyy"
                   value="<fmt:formatDate value="${requestScope.model.fechaFin.time}" pattern="yyyy-MM-dd" />">
        </div>
        <div class="form-group pull-right">
            <a href="<c:url value="Presupuesto" />">Regresar</a> &nbsp;&nbsp;
            <button type="submit" class="btn btn-primary">Guardar</button>
        </div>
    </form>
</div>