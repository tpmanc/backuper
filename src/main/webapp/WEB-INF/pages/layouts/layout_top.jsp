<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>${title}</title>

    <link rel="shortcut icon" href="<spring:url value="/resources/img/" />">


    <link rel="apple-touch-icon" sizes="180x180" href="<spring:url value="/resources/img/favicon/apple-touch-icon.png" />">
    <link rel="icon" type="image/png" href="<spring:url value="/resources/img/favicon/favicon-32x32.png" />" sizes="32x32">
    <link rel="icon" type="image/png" href="<spring:url value="/resources/img/favicon/favicon-16x16.png" />" sizes="16x16">
    <link rel="manifest" href="<spring:url value="/resources/img/favicon/manifest.json" />">
    <link rel="mask-icon" href="<spring:url value="/resources/img/favicon/safari-pinned-tab.svg" />" color="#5bbad5">
    <meta name="theme-color" content="#ffffff">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="<spring:url value="/resources/production/main.css" />">
    <script src="<spring:url value="/resources/production/main.min.js" />"></script>
</head>
<body>
    <div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-header">
        <header class="demo-header mdl-layout__header">
            <div class="mdl-layout__header-row">
                <span class="mdl-layout-title">BackupCloud</span>
                <div class="mdl-layout-spacer"></div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable">
                    <label class="mdl-button mdl-js-button mdl-button--icon" for="search">
                        <i class="material-icons">search</i>
                    </label>
                    <div class="mdl-textfield__expandable-holder">
                        <input class="mdl-textfield__input" type="text" id="search">
                        <label class="mdl-textfield__label" for="search">Enter your query...</label>
                    </div>
                </div>
                <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon" id="hdrbtn">
                    <i class="material-icons">more_vert</i>
                </button>
                <ul class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="hdrbtn">
                    <li class="mdl-menu__item">About</li>
                    <li class="mdl-menu__item">Contact</li>
                    <li class="mdl-menu__item">Legal information</li>
                </ul>
            </div>
        </header>
        <jsp:include page="../components/left-menu.jsp" />
        <main class="mdl-layout__content mdl-color--grey-100">
            <div class="mdl-grid content">