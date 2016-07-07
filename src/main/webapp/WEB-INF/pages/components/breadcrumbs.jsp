<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="breadcrumbs">
    <ul>
        <li><a href="<spring:url value="/" />">Home</a></li>
        <c:forEach items="${breadcrumbs}" var="item">
            <c:choose>
                <c:when test="${item.value != null}">
                    <li><a href="<spring:url value="${item.value}" />">${item.key}</a></li>
                </c:when>
                <c:otherwise>
                    <li>${item.key}</li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>
</div>