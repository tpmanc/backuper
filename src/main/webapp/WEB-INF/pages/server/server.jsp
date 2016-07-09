<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="../layouts/layout_top.jsp" />

<jsp:include page="../components/breadcrumbs.jsp" />

<h3>${title}</h3>

<div class="flexbox">

    <c:forEach items="${backupsDatabase}" var="backup" varStatus="itemStat">
        <div class="backup-card-wide mdl-card mdl-shadow--2dp">
            <div class="mdl-card__title">
                <h2 class="mdl-card__title-text">${backup.title}</h2>
            </div>
            <div class="mdl-card__actions mdl-card--border">
                <a href="<spring:url value="/backup-db?id=${backup.id}" />" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                    Open
                </a>
            </div>
            <div class="mdl-card__menu">
                <button id="dbBackup${backup.id}" class="mdl-button mdl-button--icon mdl-js-button mdl-js-ripple-effect">
                    <i class="material-icons">more_vert</i>
                </button>
                <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="dbBackup${backup.id}">
                    <li class="mdl-menu__item"><a href="<spring:url value="/backup-db/edit?id=${backup.id}" />">Edit</a></li>
                    <li class="mdl-menu__item">Delete</li>
                </ul>
            </div>
        </div>
    </c:forEach>

    <c:forEach items="${backupFiles}" var="backup" varStatus="itemStat">
        <div class="files-card-wide mdl-card mdl-shadow--2dp">
            <div class="mdl-card__title">
                <h2 class="mdl-card__title-text">${backup.title}</h2>
            </div>
            <div class="mdl-card__actions mdl-card--border">
                <a href="<spring:url value="/backup-files?id=${backup.id}" />" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                    Open
                </a>
            </div>
            <div class="mdl-card__menu">
                <button id="filesBackup${backup.id}" class="mdl-button mdl-button--icon mdl-js-button mdl-js-ripple-effect">
                    <i class="material-icons">more_vert</i>
                </button>
                <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="filesBackup${backup.id}">
                    <li class="mdl-menu__item"><a href="<spring:url value="/backup-files/edit?id=${backup.id}" />">Edit</a></li>
                    <li class="mdl-menu__item">Delete</li>
                </ul>
            </div>
        </div>
    </c:forEach>

</div>

<a href="<spring:url value="/backup/add?id=${server.id}" />" id="addBackup" class="fixed-button mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">
    <i class="material-icons">add</i>
</a>
<div class="mdl-tooltip mdl-tooltip--top" for="addBackup">Add new backup</div>

<jsp:include page="../layouts/layout_bottom.jsp" />