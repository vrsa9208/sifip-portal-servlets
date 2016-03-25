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
        <div class="container-fluid">
            
        </div>
        
        <!--Scripts-->
        <script type="text/javascript" src="<c:url value="/jquery/jquery-2.2.2.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/bootstrap/js/bootstrap.min.js" />"></script>
    </body>
</html>