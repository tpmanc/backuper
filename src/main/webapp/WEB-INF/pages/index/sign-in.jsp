<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="../layouts/layout_top.jsp" />

<h3>Sign In</h3>

<form action="<spring:url value='/j_spring_security_check' />" method="post" class="form-full-width">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <input class="mdl-textfield__input" name="j_username" type="text" id="email">
        <label class="mdl-textfield__label" for="email">E-mail</label>
        <span class="mdl-textfield__error">Email is invalid</span>
    </div>

    <div class="form-field mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
        <input class="mdl-textfield__input" name="j_password" type="password" id="password">
        <label class="mdl-textfield__label" for="password">Password</label>
    </div>

    <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">
        Sign In
    </button>

    <a href="<spring:url value='/sign-up' />" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
        Sign Up
    </a>
</form>

<jsp:include page="../layouts/layout_bottom.jsp" />