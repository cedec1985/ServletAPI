<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
         <link rel="stylesheet" href="style.css"> 
    </head>
    <body class="bg-light">
<h2>Code‑barres généré par Barcode4J</h2>

<%
    String code = request.getParameter("msg");
    if (code == null || code.isEmpty()) {
        code = "REF-123456";
    }
%>

<p>Message encodé : <b><%= code %></b></p>

<div class="bg-light">
<!-- L’image est servie par la servlet -->
<img src="<%= request.getContextPath() %>/BarcodeUtil?msg=<%= code %>" alt="Code-barres" class="generated-code img-fluid" />
<img src="<%= request.getContextPath() %>/BarcodeUtil?msg=<%= code %>" alt="Code-barres" class="generated-code img-fluid" />
</div>
<form action="BarcodeUtil" method="get">
    <label>Nouveau code :</label>
    <input type="text" name="msg" value="<%= code %>"/>
    <button type="submit">Générer</button>
</form>
</body>
</html>
