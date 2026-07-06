<%-- -
    Document   : result
    Created on : 1 juin 2026, 12:35:57
    Author     : cedric
--%>

<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
       <!-- <link rel="stylesheet" href="style.css">  -->
    </head>
<body class="bg-light">
<div>
    <p>Bienvenue sur <strong>${header["Host"]}</strong> !</p>

    <p>Vous accédez actuellement à la page <strong>${pageContext.request.requestURI}</strong></p>
    <p>Votre navigateur Web est : <strong>${header["user-agent"]}</strong>.</p>
</div>

<%
  String codeType = request.getParameter("codeType");
  String msg = request.getParameter("msg");
  String pattern = request.getParameter("hrpat");
  String type =request.getParameter("type");
  
  String imgUrl;
  
  if("qrcode".equals(codeType)){
    String size=request.getParameter("size");
    imgUrl = request.getContextPath() + "/QRCode?msg=" + msg + "&size=" + size;
    
    }
  else if("ean-13".equals(type)){
         String code = request.getParameter("msg");
         if (code == null || !code.matches("\\d{12}")) {
                response.sendError(500,"EAN‑13 doit contenir exactement 12 chiffres. Exemple : 123456789012");
            }
    String fmt = request.getParameter("fmt") != null ? request.getParameter("fmt") : "png";
    String height = request.getParameter("height") != null ? request.getParameter("height") : "15";
    String moduleWidth = request.getParameter("module-width") != null ? request.getParameter("module-width") : "0.2";
    String quietZone = request.getParameter("quiet-zone") != null ? request.getParameter("quiet-zone") : "2";
    String resolution = request.getParameter("resolution") != null ? request.getParameter("resolution") : "150";
    }
   else{
        imgUrl = request.getQueryString();
}
%>
<%
        String param =request.getQueryString();
        %>
        
<div class="mt-4" id="print-area" style="text-align:center; margin-top:40px;">
<h2 class="text-center mb-4">QR Code & EAN‑13 générés</h2>
<div class="row text-center">
<!-- QR CODE -->
<div class="col-md-6">
<h4>QR Code</h4>
<img src="QRCode?msg=" + msg + "&size=" + size class="generated-code img-fluid">
</div>
<div class="col-md-6">
<h4>CODE BARRES</h4>
<img src="Barcode?fmt=" + fmt + "&height=" + height + "&moduleWidth=" + moduleWidth + "&quietZone=" + quietZone + "&resolution=" + resolution + "&human-readable-pattern=" + pattern class="img-fluid generated-code">      
</div>
<br><br>
<div class="col-md-6">
<h4>PDF</h4>
<img src="PdfExportServlet?Barcode=<%= param %>&QRCode=<%= param %>"  class="img-fluid generated-code">      
</div>
<button class="btn btn-dark" onclick="printCodes()">Imprimer</button>
<a href="PdfExportServlet?Barcode=<%= param %>&QRCode=<%= param %>" 
   class="btn btn-danger" target="_blank">
    Télécharger PDF
</a>
</div>  
</div>
<script>
function printCodes() {
    // On clone uniquement la zone à imprimer
    const printContent = document.getElementById("print-area").innerHTML;

    // Nouvelle fenêtre propre
    const win = window.open("", "", "width=800,height=600");

    win.document.write(`
        <html>
        <head>
            <title>Impression du code</title>
            <style>
                body { font-family: Arial, sans-serif; text-align: center; margin: 40px; }
                img { max-width: 300px; margin: 20px; }
                h2 { margin-bottom: 20px; }
            </style>
        </head>
        <body>
            ${printContent}
        </body>
        </html>
    `);

    win.document.close();
    win.focus();
    win.print();
    win.close();
}
</script>
</body>
</html>
