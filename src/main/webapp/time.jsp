<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="now" scope="page" class="java.util.Date"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Afficher la date et l'heure du jour</title>
</head>
<body>
    <p>Bienvenue sur <strong>${header["Host"]}</strong> !</p>

    <p>Vous accédez actuellement à la page <strong>${pageContext.request.requestURI}</strong></p>
    <p>Votre navigateur Web est : <strong>${header["user-agent"]}</strong>.</p>
    <p>${empty param ? "Vous n'avez pas envoyé de paramètre au serveur"
                        : "Vous avez envoyé des paramètres au serveur"}</p>
    <p>${empty monCookie ? "Vous n'avez pas envoyé de cookie au serveur"
                        : "Vous avez envoyé des cookies au serveur"}</p>
        <fmt:setLocale value="fr_BE"/>
        <c:out value="Quelle est la date du jour et l'heure ?" />
        <br>
	<fmt:formatDate value="${now}" pattern="dd/MM/yyyy HH:mm:ss" type="both"/>
        <p>Date et Heure formatées : 
            <br>
            ${now}</p>
        <p>Cookie : 
            <br>
            ${monCookie}</p>

</body>
</html>