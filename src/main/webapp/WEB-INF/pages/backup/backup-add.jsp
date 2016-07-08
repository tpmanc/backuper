<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="../layouts/layout_top.jsp" />

<script src="<spring:url value="/resources/js/pages/backup/backup-add.js" />"></script>

<jsp:include page="../components/breadcrumbs.jsp" />

<h3>${title}</h3>

<div class="new-server-form">
    <form action="<spring:url value="/backup/add/handler" />" method="post" autocomplete="off">
        <input type="hidden" name="serverId" value="${server.id}">
        <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" autocomplete="off" id="title" name="title">
            <label class="mdl-textfield__label" for="title">Title</label>
        </div>

        <div class="form-field">
            <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="backupType1">
                <input type="radio" id="backupType1" class="mdl-radio__button" name="backupType" value="1" checked>
                <span class="mdl-radio__label">Database Backup</span>
            </label>
        </div>
        <div class="form-field">
            <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="backupType2">
                <input type="radio" id="backupType2" class="mdl-radio__button" name="backupType" value="2">
                <span class="mdl-radio__label">Files Backup</span>
            </label>
        </div>
        <br>
        <div id="databaseSettings">
            <div class="form-field">
                <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="dbType1">
                    <input type="radio" id="dbType1" class="mdl-radio__button" name="dbType" value="1" checked>
                    <span class="mdl-radio__label">MySQL</span>
                </label>
            </div>
            <div class="form-field">
                <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="dbType2">
                    <input type="radio" id="dbType2" class="mdl-radio__button" name="dbType" value="2">
                    <span class="mdl-radio__label">PostgreSQL</span>
                </label>
            </div>

            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" autocomplete="off" id="dbUser" name="dbUser">
                <label class="mdl-textfield__label" for="dbUser">Database User</label>
            </div>

            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="password" autocomplete="off" id="dbPassword" name="dbPassword">
                <label class="mdl-textfield__label" for="dbPassword">Database Password</label>
            </div>

            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" autocomplete="off" id="dbName" name="dbName">
                <label class="mdl-textfield__label" for="dbPassword">Database Name</label>
            </div>
        </div>

        <div id="filesSettings">
            <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="password" autocomplete="off" id="filesFolder" name="filesFolder">
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