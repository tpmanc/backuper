<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="../layouts/layout_top.jsp" />

<script src="<spring:url value="/resources/js/pages/backup/backup.js" />"></script>

<jsp:include page="../components/breadcrumbs.jsp" />

<h3>${title}</h3>

<table class="backup-table event-table mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
    <thead>
    <tr>
        <th class="mdl-data-table__cell--non-numeric">Title</th>
        <th>File size</th>
        <th>Date</th>
        <th>Download</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${archives}" var="archive">
        <tr>
            <td class="mdl-data-table__cell--non-numeric">${archive.name}</td>
            <td>
                <c:choose>
                    <c:when test="${archive.size < 1024}">
                        <fmt:formatNumber var="res" value="${archive.size}" maxFractionDigits="2" />${res} B
                    </c:when>
                    <c:when test="${archive.size < 1024 * 1024}">
                        <fmt:formatNumber var="res" value="${archive.size/1024}" maxFractionDigits="2" />${res} Kb
                    </c:when>
                    <c:when test="${archive.size < 1024 * 1024 * 1024}">
                        <fmt:formatNumber var="res" value="${archive.size/1024/1024}" maxFractionDigits="2" />${res} Mb
                    </c:when>
                    <c:when test="${archive.size < 1024 * 1024 * 1024 * 1024}">
                        <fmt:formatNumber var="res" value="${archive.size/1024/1024/1024}" maxFractionDigits="2" />${res} Gb
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber var="res" value="${archive.size}" maxFractionDigits="2" />${res} B
                    </c:otherwise>
                </c:choose>
            </td>
            <jsp:useBean id="dateValue" class="java.util.Date"/>
            <jsp:setProperty name="dateValue" property="time" value="${archive.date}"/>
            <td><fmt:formatDate value="${dateValue}" pattern="HH:mm dd.MM.yyyy" /></td>
            <td>
                <a href="<spring:url value="/backup/download/database/${archive.id}" />" class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--mini-fab">
                    <i class="material-icons">file_download</i>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="<spring:url value="/backup/database/run/${backupDatabase.id}" />" id="runBackup" class="run-backup-btn fixed-button mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">
    <i class="material-icons">play_arrow</i>
</a>
<div class="mdl-tooltip mdl-tooltip--top" for="runBackup">Run database backup</div>


<button id="deleteBackup" class="delete-btn delete-btn-hidden fixed-button mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">
    <i class="material-icons">delete</i>
</button>
<div class="mdl-tooltip mdl-tooltip--top" for="deleteBackup">Delete selected backups</div>

<jsp:include page="../layouts/layout_bottom.jsp" />