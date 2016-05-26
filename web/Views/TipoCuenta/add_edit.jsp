<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <h2 class="text-left"><c:out value="${requestScope.title}" /></h2>
            <div style="margin-top: 20px" />
            <c:set var="mensajeError" value="${requestScope.mensajeError}" />
            <c:if test="${not empty mensajeError}">
                <div class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Error!</strong> <c:out value="${requestScope.mensajeError}" />
                </div>
            </c:if>
            <c:set var="mensajeSuccess" value="${requestScope.mensajeSuccess}" />
            <c:if test="${not empty mensajeSuccess}">
                <div class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <c:out value="${requestScope.mensajeSuccess}" />
                </div>
            </c:if>
            <form method="post">
                <input type="hidden" name="id" value="${requestScope.tipoCuenta.id}" />
                <input type="hidden" name="activo" value="${requestScope.tipoCuenta.activo}" />
                <div class="form-group">
                    <label for="descripcion">Descripción</label>
                    <div class="input-group">
                        <div class="input-group-addon"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></div>
                        <input type="descripcion" class="form-control" id="descripcion" name="descripcion"
                               value="${requestScope.tipoCuenta.descripcion}" placeholder="Descripción">
                    </div>
                </div>
                <button type="submit" class="btn btn-success">Guardar</button>
                <button class="btn btn-link"><a href="<c:url value="TipoDeCuenta" />">Regresar</a></button>
            </form>
        </div>
    </div>
</div>
