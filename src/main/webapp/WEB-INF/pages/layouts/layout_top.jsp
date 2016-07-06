<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
    <head>
        <title>${pageTitle}</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta charset="UTF-8">
        <link rel="shortcut icon" href="<spring:url value="/resources/favicon.png" />" type="image/png">

        <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet" />

        <script src="<spring:url value="/resources/js/libs/jquery-2.1.4.min.js"/>"></script>
    </head>
    <body>
        <div id="wrapper">
            <jsp:include page="header.jsp">
                <jsp:param name="firstname" value="${param.activePage}" />
            </jsp:include>