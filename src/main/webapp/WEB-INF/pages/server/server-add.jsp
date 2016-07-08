<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="../layouts/layout_top.jsp" />

<jsp:include page="../components/breadcrumbs.jsp" />

<h3>${title}</h3>

<div class="new-server-form">
    <form action="<spring:url value="/server/add/handler" />" method="post" autocomplete="off">
        <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" autocomplete="off" id="title" name="title">
            <label class="mdl-textfield__label" for="title">Title</label>
        </div>

        <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" autocomplete="off" id="url" name="url">
            <label class="mdl-textfield__label" for="url">URL</label>
        </div>

        <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" autocomplete="off" id="sftpUser" name="sftpUser">
            <label class="mdl-textfield__label" for="sftpUser">SFTP User</label>
        </div>

        <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="password" autocomplete="off" id="sftpPassword" name="sftpPassword">
            <label class="mdl-textfield__label" for="sftpPassword">SFTP Password</label>
        </div>

        <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="number" autocomplete="off" id="sftpPort" name="sftpPort">
            <label class="mdl-textfield__label" for="sftpPort">SFTP Port</label>
        </div>

        <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
            Add server
        </button>
    </form>
</div>

<jsp:include page="../layouts/layout_bottom.jsp" />