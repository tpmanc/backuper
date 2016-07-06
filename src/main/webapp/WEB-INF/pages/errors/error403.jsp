<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../layouts/layout_top.jsp" />

<div class="middle-box text-center">
    <h1>403</h1>
    <h3 class="font-bold">
        <c:choose>
        <c:when test="${message != null}">
            ${message}
        </c:when>
        <c:otherwise>
            Доступ запрещен
        </c:otherwise>
        </c:choose>
    </h3>
</div>

<jsp:include page="../layouts/layout_bottom.jsp" />
