<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <h2 class="text-left">Cambiar Password</h2>
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
                <div class="form-group">
                    <label for="currentPassword">Password Actual</label>
                    <input type="password" class="form-control" id="currentPassword" name="currentPassword"
                           value="${requestScope.currentPassword}" placeholder="Password actual">
                </div>
                <div class="form-group">
                    <label for="newPassword">Password Nuevo</label>
                    <input type="password" class="form-control" id="newPassword" name="newPassword" 
                           value="${requestScope.newPassword}" placeholder="Password nuevo">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="newPassword2" name="newPassword2"
                           value="${requestScope.newPassword2}" placeholder="Repite el password nuevo">
                </div>
                <button type="submit" class="btn btn-success">Guardar</button>
            </form>
        </div>
    </div>
</div>
