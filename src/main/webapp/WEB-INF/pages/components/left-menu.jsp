<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="demo-drawer mdl-layout__drawer">
    <header class="header">
        <div class="demo-avatar-dropdown">
            <span>admin@example.com</span>
            <div class="mdl-layout-spacer"></div>
            <button id="accbtn" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
                <i class="material-icons" role="presentation">arrow_drop_down</i>
                <span class="visuallyhidden">Accounts</span>
            </button>
            <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="accbtn">
                <li class="mdl-menu__item">Settings</li>
                <li class="mdl-menu__item">Sign out</li>
            </ul>
        </div>
    </header>
    <nav class="navigation mdl-navigation">
        <a class="mdl-navigation__link" href="<spring:url value="/" />">
            <i class="material-icons" role="presentation">home</i>Home
        </a>
        <a class="mdl-navigation__link" href="<spring:url value="/servers" />">
            <i class="material-icons" role="presentation">web</i>Sites
        </a>
        <div class="mdl-layout-spacer"></div>
        <a class="mdl-navigation__link" href=""><i class="material-icons" role="presentation">help_outline</i><span class="visuallyhidden">Help</span></a>
    </nav>
</div>