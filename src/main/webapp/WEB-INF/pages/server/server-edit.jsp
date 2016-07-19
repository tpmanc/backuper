<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="../layouts/layout_top.jsp" />

<jsp:include page="../components/breadcrumbs.jsp" />

<h3>${title}</h3>

<div class="new-server-form">
    <form action="<spring:url value="/server/save/handler" />" method="post" autocomplete="off">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="serverId" value="${server.id}"/>
        <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label <c:if test="${errors.containsKey('title')}">is-invalid</c:if>">
            <input class="mdl-textfield__input" type="text" autocomplete="off" id="title" name="title" value="${server.title}">
            <label class="mdl-textfield__label" for="title">Title</label>
            <c:if test="${errors.containsKey('title')}">
                <span class="mdl-textfield__error">${errors.get('title')[0]}</span>
            </c:if>
        </div>

        <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label <c:if test="${errors.containsKey('host')}">is-invalid</c:if>">
            <input class="mdl-textfield__input" type="text" autocomplete="off" id="url" name="url" value="${server.host}">
            <label class="mdl-textfield__label" for="url">Host</label>
            <c:if test="${errors.containsKey('host')}">
                <span class="mdl-textfield__error">${errors.get('host')[0]}</span>
            </c:if>
        </div>

        <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label <c:if test="${errors.containsKey('sshUser')}">is-invalid</c:if>">
            <input class="mdl-textfield__input" type="text" autocomplete="off" id="sshUser" name="sshUser" value="${server.sshUser}">
            <label class="mdl-textfield__label" for="sshUser">Ssh User</label>
            <c:if test="${errors.containsKey('sshUser')}">
                <span class="mdl-textfield__error">${errors.get('sshUser')[0]}</span>
            </c:if>
        </div>

        <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label <c:if test="${errors.containsKey('sshPassword')}">is-invalid</c:if>">
            <input class="mdl-textfield__input" type="password" autocomplete="off" id="sshPassword" name="sshPassword" value="${server.sshPassword}">
            <label class="mdl-textfield__label" for="sshPassword">Ssh Password</label>
            <c:if test="${errors.containsKey('sshPassword')}">
                <span class="mdl-textfield__error">${errors.get('sshPassword')[0]}</span>
            </c:if>
        </div>

        <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label <c:if test="${errors.containsKey('sshPort')}">is-invalid</c:if>">
            <input class="mdl-textfield__input" type="number" autocomplete="off" id="sshPort" name="sshPort"  value="${server.sshPort}">
            <label class="mdl-textfield__label" for="sshPort">Ssh Port</label>
            <c:if test="${errors.containsKey('sshPort')}">
                <span class="mdl-textfield__error">${errors.get('sshPort')[0]}</span>
            </c:if>
        </div>

        <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
            Save Server
        </button>
    </form>
</div>

<jsp:include page="../layouts/layout_bottom.jsp" />