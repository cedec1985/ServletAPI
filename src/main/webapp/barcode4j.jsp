<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
         <link rel="stylesheet" href="css/style.css"> 
    </head>
    <body class="bg-light">
        <span class="bord bord-haut">Coller le ruban adhésif ici</span>
        <span class="bord bord-droite">Coller le ruban adhésif ici</span>
        <span class="bord bord-bas">Coller le ruban adhésif ici</span>
        <span class="bord bord-gauche">Coller le ruban adhésif ici</span>
      
<h2>Code‑barres généré par Barcode4J</h2>

<div id="pallet-sheet" class="container p-4 shadow rounded" style=" margin-bottom:100px;">
<div class="text-center mb-3">
<img src="images/BAL.png" width="65">
<img src="images/mondialrelay.jpg" width="200">
</div>
</div>

<%
    String code = request.getParameter("msg");
    if (code == null || code.isEmpty()) {
        code = "CODEBARRE-123456";
    }
    String code2 = request.getParameter("msg");
    if(code2==null || code2.isEmpty()){
    code2="QRCODE 45899663";
    }
    
%>

<div class="bg-light">
<!-- L’image est servie par la servlet -->
<img src="<%= request.getContextPath() %>/PurchaseOrder?msg=<%= code %>" alt="Code-barres" class="generated-code img-fluid" />
<img src="<%= request.getContextPath() %>/PurchaseOrder?msg=<%= code2 %>&size=250&type=datamatrix&fmt=svg" alt="QR Code" class="generated-code img-fluid" />
</div>
<form action="PurchaseOrder" method="get">
    <label>Nouveau CODEBARRE :</label>
    <input type="text" name="msg" value="<%= code %>"/>
    <button type="submit">Générer</button>
</form>
<form action="PurchaseOrder" method="get">
    <label>Nouveau Code QR :</label>
    <input type="text" name="msg" value="<%= code2 %>"/>
    <button type="submit">Générer</button>
</form>
</body>
</html>
