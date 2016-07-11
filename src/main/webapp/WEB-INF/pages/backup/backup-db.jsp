<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="../layouts/layout_top.jsp" />

<script src="<spring:url value="/resources/js/pages/backup/backup-add.js" />"></script>

<jsp:include page="../components/breadcrumbs.jsp" />

<h3>${title}</h3>

<table class="backup-table mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
    <thead>
    <tr>
        <th class="mdl-data-table__cell--non-numeric">Title</th>
        <th>Table count</th>
        <th>File size</th>
        <th>Date</th>
        <th>Download</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${archives}" var="archive">
        <tr>
            <td class="mdl-data-table__cell--non-numeric">${archive.name}</td>
            <td>${archive.tableCount}</td>
            <td>${archive.size}</td>
            <td>${archive.date}</td>
            <td>
                <button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--mini-fab">
                    <i class="material-icons">file_download</i>
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="<spring:url value="/backup/database/run/${backupDatabase.id}" />" id="runBackup" class="fixed-button mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">
    <i class="material-icons">play_arrow</i>
</a>
<div class="mdl-tooltip mdl-tooltip--top" for="runBackup">Run database backup</div>

<jsp:include page="../layouts/layout_bottom.jsp" />