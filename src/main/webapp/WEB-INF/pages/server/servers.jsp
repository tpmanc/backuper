<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="../layouts/layout_top.jsp" />

<script src="<spring:url value="/resources/js/pages/catalog/index.js" />"></script>

<jsp:include page="../components/breadcrumbs.jsp" />

<h3>Servers</h3>

<div class="flexbox">

    <c:forEach items="${servers}" var="server" varStatus="itemStat">
        <div class="server-card-wide mdl-card mdl-shadow--2dp">
            <div class="mdl-card__title">
                <div class="bg"></div>
                <h2 class="mdl-card__title-text">${server.title}</h2>
            </div>
            <div class="mdl-card__actions mdl-card--border">
                <a href="<spring:url value="/server?id=${server.id}" />" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                    Open
                </a>
            </div>
            <div class="mdl-card__menu">
                <button id="server${server.id}Menu" class="mdl-button mdl-button--icon mdl-js-button mdl-js-ripple-effect">
                    <i class="material-icons">more_vert</i>
                </button>
                <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="server${server.id}Menu">
                    <li class="mdl-menu__item"><a href="${server.url}" target="_blank">Link</a></li>
                    <li class="mdl-menu__item">Edit</li>
                    <li class="mdl-menu__item">Delete</li>
                </ul>
            </div>
        </div>
    </c:forEach>

</div>

<a href="<spring:url value="/server/add" />" id="addServer" class="fixed-button mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored">
    <i class="material-icons">add</i>
</a>
<div class="mdl-tooltip mdl-tooltip--top" for="addServer">Add new server</div>

<jsp:include page="../layouts/layout_bottom.jsp" />