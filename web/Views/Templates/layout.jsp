<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <c:set var="title" value="${requestScope.title}" />
        <c:if test="${not empty title}">
            <title><c:out value="${requestScope.title}"/></title>
        </c:if>
        <c:if test="${empty title}">
            <title>Sifip</title>
        </c:if>
        <link rel="stylesheet" href="<c:url value="/bootstrap/css/bootstrap.min.css" />" />
    </head>
    <body>
        <c:set var="usuarioActual" value="${sessionScope.usuario}" />
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<c:url value="Home" />">SIFIP</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                <c:out value="${usuarioActual.nombre}" />
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value="Perfil" />">Mi Perfil</a></li>
                                <li><a href="<c:url value="Perfil?action=cambiarPassword" />">Cambiar Password</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="<c:url value="Logout" />">Cerrar Sesión</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

        <!--Contenedor Principal-->
        <div class="container">
            <div class="row">
                <div class="col-lg-2">
                    <ul class="nav nav-pills nav-stacked">
                        <li 
                            <c:if test="${not empty requestScope.menuCategorias}">
                                class="active" 
                            </c:if>
                            role="presentation"><a href="<c:url value="Categoria" />">Categorías</a></li>
                        <li
                            <c:if test="${not empty requestScope.menuCuentas}">
                                class="active" 
                            </c:if>
                            role="presentation"><a href="#">Cuentas</a></li>
                        <li 
                            <c:if test="${not empty requestScope.menuDeudas}">
                                class="active" 
                            </c:if>
                            role="presentation"><a href="#">Deudas</a></li>
                        <li 
                            <c:if test="${not empty requestScope.menuMiPerfil}">
                                class="active" 
                            </c:if>
                            role="presentation"><a href="<c:url value="Perfil" />">Mi Perfil</a></li>
                        <li 
                            <c:if test="${not empty requestScope.menuMovimientos}">
                                class="active" 
                            </c:if>
                            role="presentation"><a href="#">Movimientos</a></li>
                        <li 
                            <c:if test="${not empty requestScope.menuPresupuestos}">
                                class="active" 
                            </c:if>
                            role="presentation"><a href="<c:url value="Presupuesto" />">Presupuestos</a></li>
                        <li 
                            <c:if test="${not empty requestScope.menuTiposDeCuenta}">
                                class="active" 
                            </c:if>
                            role="presentation"><a href="<c:url value="TipoDeCuenta" />">Tipos de Cuenta</a></li>
                    </ul>
                </div>
                <div class="col-lg-10">
                    <c:if test="${not empty requestScope.error}">
                        <div class="alert alert-danger alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong>Error !</strong> <c:out value="${requestScope.error}" />
                        </div>
                    </c:if>
                    <c:if test="${not empty requestScope.page}">
                        <jsp:include page="${requestScope.page}" />
                    </c:if>
                </div>
            </div>
        </div>

        <!--Scripts-->
        <script type="text/javascript" src="<c:url value="/jquery/jquery-2.2.2.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/bootstrap/js/bootstrap.min.js" />"></script>
    </body>
</html>