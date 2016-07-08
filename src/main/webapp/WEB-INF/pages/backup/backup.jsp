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
        <th>Download</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td class="mdl-data-table__cell--non-numeric">Acrylic (Transparent)</td>
        <td>25</td>
        <td>2.9 Mb</td>
        <td>
            <button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--mini-fab">
                <i class="material-icons">file_download</i>
            </button>
        </td>
    </tr>
    <tr>
        <td class="mdl-data-table__cell--non-numeric">Plywood (Birch)</td>
        <td>50</td>
        <td>1.25 Mb</td>
        <td>
            <button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--mini-fab">
                <i class="material-icons">file_download</i>
            </button>
        </td>
    </tr>
    <tr>
        <td class="mdl-data-table__cell--non-numeric">Laminate (Gold on Blue)</td>
        <td>10</td>
        <td>2.35 Mb</td>
        <td>
            <button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--mini-fab">
                <i class="material-icons">file_download</i>
            </button>
        </td>
    </tr>
    </tbody>
</table>

<jsp:include page="../layouts/layout_bottom.jsp" />