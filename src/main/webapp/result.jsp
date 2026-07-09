<%-- -
    Document   : result
    Created on : 1 juin 2026, 12:35:57
    Author     : cedric
--%>


<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>
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
<!-- PARTIE CHECKBOX -->
<body class="bg-light">
 
<%
        String param =request.getQueryString();
        String message = request.getParameter("msg");
        String size = request.getParameter("size");
        String []codes = request.getParameterValues("codeTypes");
        String qrUrl = request.getContextPath() + "/QRCode?msg=" + message + "&size=" + size + "&type=" + codes[1] ;
        String eanUrl = request.getContextPath() + "/Barcode?msg=" + message + "&type=" + codes[0];
 %>

<%
    List<String> urls = (List<String>) request.getAttribute("urls");
 
    
%>

<div class="row mt-4">

    <% for (String u : urls) { %>
        <div class="col-md-4 text-center mb-4">
            <img src="<%= u %>" class="img-fluid">
        </div>
    <% } %>

</div>
<!-- PARTIE SELECT -->
<%
    String codeType = request.getParameter("codeType");
    String msg = request.getParameter("msg");
    String imgUrl;
    String params = request.getQueryString();
    if ("qrcode".equals(codeType)) {
        imgUrl = request.getContextPath() + "/QRCode?msg=" + msg + "&size=250";
    } else if ("barcode".equals(codeType)){
        
        String type = request.getParameter("type");
        String height = request.getParameter("height");
        String moduleWidth = request.getParameter("module-width");
        String quietZone = request.getParameter("quiet-zone");
        String resolution = request.getParameter("resolution");
        String fmt = request.getParameter("fmt");
        
 }  

%>
<!-- zone imprimable -->
<div class="mt-4" id="print-area" style="text-align:center; margin-top:40px;">
<img src="images/samsung.png" width="150" style="margin-bottom:20px;">
<h2 class="text-center mb-4">QR Code & EAN‑13 générés</h2>
<div class="row text-center">
<!-- QR CODE -->
<div class="col-md-6">
<h4>QR Code</h4>
<img src="<%= req.getContextPath() %>/QRCode?msg=<%= msg %>&size=250" class="generated-code img-fluid">
</div>
<div class="col-md-6">
<h4>CODE BARRES</h4>
<img src="<%= params %>" class="img-fluid generated-code">      
</div>
<br><br>
<div class="col-md-6">
<h4>PDF</h4>
<img src="PdfExportServlet?ean=<%= eanUrl %>&qr=<%= qrUrl %>"  class="img-fluid generated-code">      

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
