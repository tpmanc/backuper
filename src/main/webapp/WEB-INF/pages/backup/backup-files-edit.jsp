<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="../layouts/layout_top.jsp" />

<jsp:include page="../components/breadcrumbs.jsp" />

<h3>${title}</h3>

<div class="new-server-form">
    <form action="<spring:url value="/backup/save/handler" />" method="post" autocomplete="off">
        <input type="hidden" name="backupType" value="2">
        <input type="hidden" name="backupId" value="${backupFiles.id}">
        <input type="hidden" name="serverId" value="${backupFiles.server.id}">
        <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" autocomplete="off" id="title" name="title" value="${backupFiles.title}">
            <label class="mdl-textfield__label" for="title">Title</label>
        </div>

        <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" autocomplete="off" id="filesFolder" name="filesFolder" value="${backupFiles.folder}">
            <label class="mdl-textfield__label" for="filesFolder">Files Folder</label>
        </div>

        <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <textarea class="mdl-textfield__input" type="text" rows="7" name="filesIgnore" id="filesIgnore">${backupFiles.ignoreFiles}</textarea>
            <label class="mdl-textfield__label" for="filesIgnore">Ignore Files List</label>
        </div>

        <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
            Save
        </button>
    </form>
</div>

<jsp:include page="../layouts/layout_bottom.jsp" />