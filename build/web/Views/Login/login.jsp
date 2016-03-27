<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <title>Login Sifip</title>
        <link rel="stylesheet" href="<c:url value="/bootstrap/css/bootstrap.min.css" />" />
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-lg-offset-3">
                    <h1 class="text-center">Sistema de Finanzas Personales</h1>
                    <h3 class="text-center">SIFIP</h3>
                </div>
            </div>
            <!--Panel de Login-->
            <div style="margin-top: 50px"></div>
            <div class="row">
                <div class="col-lg-4 col-lg-offset-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Login</h3>
                        </div>
                        <div class="panel-body">
                            <form class="form" method="post" action="<c:url value="/Login" />">
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
                                <div class="form-group">
                                    <label class="sr-only" for="email">e-mail</label>
                                    <div class="input-group">
                                        <div class="input-group-addon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></div>
                                        <input type="text" class="form-control" id="email" placeholder="e-mail" name="email" value="<c:out value="${requestScope.email}" />" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="password">Password</label>
                                    <div class="input-group">
                                        <div class="input-group-addon"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></div>
                                        <input type="password" class="form-control" id="password" placeholder="password" name="password" value="<c:out value="${requestScope.password}" />" />
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary">Ingresar</button> &nbsp;&nbsp;
                                <a href="#">Olvidé mi password</a> &nbsp;&nbsp;
                                <a href="<c:url value="Usuario?action=register" />">Registrarse</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--Scripts-->
        <script type="text/javascript" src="<c:url value="/jquery/jquery-2.2.2.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/bootstrap/js/bootstrap.min.js" />"></script>
    </body>
</html>