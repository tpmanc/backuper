<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="../layouts/layout_top.jsp" />

<script src="<spring:url value="/resources/js/pages/backup/backup-add.js" />"></script>

<jsp:include page="../components/breadcrumbs.jsp" />

<h3>${title}</h3>

<div class="new-server-form">
    <form action="<spring:url value="/backup/save/handler" />" method="post" autocomplete="off">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="serverId" value="${server.id}">
        <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label <c:if test="${errors.containsKey('title')}">is-invalid</c:if>">
            <input class="mdl-textfield__input" type="text" autocomplete="off" id="title" name="title" value="${backup.title}">
            <label class="mdl-textfield__label" for="title">Title</label>
            <c:if test="${errors.containsKey('title')}">
                <span class="mdl-textfield__error">${errors.get('title')[0]}</span>
            </c:if>
        </div>

        <div class="form-field">
            <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="backupType1">
                <input type="radio" id="backupType1" class="mdl-radio__button" name="backupType" value="1" <c:if test="${backupType == 1 || backupType == null}">checked</c:if>>
                <span class="mdl-radio__label">Database Backup</span>
            </label>
        </div>
        <div class="form-field">
            <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="backupType2">
                <input type="radio" id="backupType2" class="mdl-radio__button" name="backupType" value="2" <c:if test="${backupType == 2}">checked</c:if>>
                <span class="mdl-radio__label">Files Backup</span>
            </label>
        </div>
        <br>
        <div id="databaseSettings">
            <div class="form-field">
                <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="dbType1">
                    <input type="radio" id="dbType1" class="mdl-radio__button" name="dbType" value="1" <c:if test="${backup.databaseType == 1 || backup == null || backup.databaseType == null}">checked</c:if>>
                    <span class="mdl-radio__label">MySQL</span>
                </label>
            </div>
            <div class="form-field">
                <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="dbType2">
                    <input type="radio" id="dbType2" class="mdl-radio__button" name="dbType" value="2" <c:if test="${backup.databaseType == 2}">checked</c:if>>
                    <span class="mdl-radio__label">PostgreSQL</span>
                </label>
            </div>

            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label <c:if test="${errors.containsKey('databaseUser')}">is-invalid</c:if>">
                <input class="mdl-textfield__input" type="text" autocomplete="off" id="dbUser" name="dbUser" value="${backup.databaseUser}">
                <label class="mdl-textfield__label" for="dbUser">Database User</label>
                <c:if test="${errors.containsKey('databaseUser')}">
                    <span class="mdl-textfield__error">${errors.get('databaseUser')[0]}</span>
                </c:if>
            </div>

            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label <c:if test="${errors.containsKey('databasePassword')}">is-invalid</c:if>">
                <input class="mdl-textfield__input" type="password" autocomplete="off" id="dbPassword" name="dbPassword" value="${backup.databasePassword}">
                <label class="mdl-textfield__label" for="dbPassword">Database Password</label>
                <c:if test="${errors.containsKey('databasePassword')}">
                    <span class="mdl-textfield__error">${errors.get('databasePassword')[0]}</span>
                </c:if>
            </div>

            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label <c:if test="${errors.containsKey('databaseName')}">is-invalid</c:if>">
                <input class="mdl-textfield__input" type="text" autocomplete="off" id="dbName" name="dbName" value="${backup.databaseName}">
                <label class="mdl-textfield__label" for="dbName">Database Name</label>
                <c:if test="${errors.containsKey('databaseName')}">
                    <span class="mdl-textfield__error">${errors.get('databaseName')[0]}</span>
                </c:if>
            </div>

            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label <c:if test="${errors.containsKey('databasePort')}">is-invalid</c:if>">
                <input class="mdl-textfield__input" type="text" autocomplete="off" id="dbPort" name="dbPort" value="${backup.databasePort}">
                <label class="mdl-textfield__label" for="dbPort">Database Port</label>
                <c:if test="${errors.containsKey('databasePort')}">
                    <span class="mdl-textfield__error">${errors.get('databasePort')[0]}</span>
                </c:if>
            </div>
        </div>

        <div id="filesSettings">
            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" autocomplete="off" id="filesFolder" name="filesFolder">
                <label class="mdl-textfield__label" for="filesFolder">Files Folder</label>
            </div>

            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <textarea class="mdl-textfield__input" type="text" rows="7" name="filesIgnore" id="filesIgnore" ></textarea>
                <label class="mdl-textfield__label" for="filesIgnore">Ignore Files List</label>
            </div>
        </div>

        <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
            Add Backup
        </button>
    </form>
</div>

<jsp:include page="../layouts/layout_bottom.jsp" />