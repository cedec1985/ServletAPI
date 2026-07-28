<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="Product.Article" %>
<%
    Article article = (Article) request.getAttribute("article");
    if (article == null) {
        out.println("Aucun article à afficher.");
        return;
    }
%>
<html>
<head><title>Fiche article</title></head>
<body>
<h2>Fiche article</h2>
<ul>
  <li>Id: <%= article.getId() %></li>
  <li>Nom: <%= article.getName() %></li>
  <li>Référence: <%= article.getRef() %></li>
  <li>EAN: <%= article.getEan() %></li>
  <li>Date livraison: <%= article.getDeliveryDate() %></li>
  <li>Destinataire: <%= article.getRecipient() %></li>
</ul>

<img alt="Code-barres" src="<%= request.getContextPath() %>/servlet/PurchaseOrder?msg=<%= java.net.URLEncoder.encode(article.getEan() != null ? article.getEan() : article.getRef(), "UTF-8") %>" />

<!-- QR en SVG -->
<object type="image/svg+xml" data="<%= request.getContextPath() %>/servlet/PurchaseOrder?msg=<%= java.net.URLEncoder.encode(article.getEan() != null ? article.getEan() : article.getRef(), "UTF-8") %>&size=250&type=datamatrix&fmt=svg" width="250" height="250">
  Votre navigateur ne supporte pas les SVG ou le QR n'a pas pu être chargé.
</object>

</body>
</html>
