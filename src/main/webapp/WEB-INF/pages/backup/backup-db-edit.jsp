<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="../layouts/layout_top.jsp" />

<jsp:include page="../components/breadcrumbs.jsp" />

<h3>${title}</h3>

<div class="new-server-form">
    <form action="<spring:url value="/backup/save/handler" />" method="post" autocomplete="off">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="backupType" value="1">
        <input type="hidden" name="backupId" value="${backupDatabase.id}">
        <input type="hidden" name="serverId" value="${backupDatabase.server.id}">
        <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label <c:if test="${errors.containsKey('title')}">is-invalid</c:if>">
            <input class="mdl-textfield__input" type="text" autocomplete="off" id="title" name="title" value="${backupDatabase.title}">
            <label class="mdl-textfield__label" for="title">Title</label>
            <c:if test="${errors.containsKey('title')}">
                <span class="mdl-textfield__error">${errors.get('title')[0]}</span>
            </c:if>
        </div>

        <div id="databaseSettings">
            <div class="form-field">
                <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="dbType1">
                    <input type="radio" id="dbType1" class="mdl-radio__button" name="dbType" value="1" <c:if test="${backupDatabase.databaseType == 1 || backupDatabase == null || backupDatabase.databaseType == null}">checked="checked"</c:if>>
                    <span class="mdl-radio__label">MySQL</span>
                </label>
            </div>
            <div class="form-field">
                <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="dbType2">
                    <input type="radio" id="dbType2" class="mdl-radio__button" name="dbType" value="2" <c:if test="${backupDatabase.databaseType == 2}">checked="checked"</c:if> >
                    <span class="mdl-radio__label">PostgreSQL</span>
                </label>
            </div>

            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label <c:if test="${errors.containsKey('databaseUser')}">is-invalid</c:if>">
                <input class="mdl-textfield__input" type="text" autocomplete="off" id="dbUser" name="dbUser" value="${backupDatabase.databaseUser}">
                <label class="mdl-textfield__label" for="dbUser">Database User</label>
                <c:if test="${errors.containsKey('databaseUser')}">
                    <span class="mdl-textfield__error">${errors.get('databaseUser')[0]}</span>
                </c:if>
            </div>

            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label <c:if test="${errors.containsKey('databasePassword')}">is-invalid</c:if>">
                <input class="mdl-textfield__input" type="password" autocomplete="off" id="dbPassword" name="dbPassword" value="${backupDatabase.databasePassword}">
                <label class="mdl-textfield__label" for="dbPassword">Database Password</label>
                <c:if test="${errors.containsKey('databasePassword')}">
                    <span class="mdl-textfield__error">${errors.get('databasePassword')[0]}</span>
                </c:if>
            </div>

            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label <c:if test="${errors.containsKey('databaseName')}">is-invalid</c:if>">
                <input class="mdl-textfield__input" type="text" autocomplete="off" id="dbName" name="dbName" value="${backupDatabase.databaseName}">
                <label class="mdl-textfield__label" for="dbPassword">Database Name</label>
                <c:if test="${errors.containsKey('databaseName')}">
                    <span class="mdl-textfield__error">${errors.get('databaseName')[0]}</span>
                </c:if>
            </div>
        </div>

        <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
            Save
        </button>
    </form>
</div>

<jsp:include page="../layouts/layout_bottom.jsp" />