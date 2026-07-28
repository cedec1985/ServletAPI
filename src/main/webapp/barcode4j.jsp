<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="Product.Article" %>
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
    // Lecture du paramètre articleId
    String articleId = request.getParameter("articleId");
    // On cherche d'abord un attribut "article" (forward depuis une servlet)
    Article article = (Article) request.getAttribute("article");

    // Si aucun article en attribut mais un articleId fourni, tenter de retrouver dans l'attribut application "articles"
    if (article == null && articleId != null && !articleId.isEmpty()) {
        Object articlesObj = application.getAttribute("articles");
        if (articlesObj instanceof java.util.List) {
            java.util.List list = (java.util.List) articlesObj;
            for (Object o : list) {
                if (o instanceof Article) {
                    Article a = (Article) o;
                    if (articleId.equals(a.getEan()) || (a.getId() != null && articleId.equals(a.getId())) || (a.getRef() != null && articleId.equals(a.getRef()))) {
                        article = a;
                        break;
                    }
                }
            }
        }
    }

    // Valeurs par défaut pour la génération des images
    String code = request.getParameter("msg");
    String code2 = request.getParameter("msg");
    if (article != null) {
        // Prioriser l'EAN pour le code-barres, sinon la référence
        code = article.getEan() != null && !article.getEan().isEmpty() ? article.getEan() : article.getRef();
        code2 = code;
    }

    if (code == null || code.isEmpty()) {
        code = "CODEBARRE-123456";
    }
    if (code2 == null || code2.isEmpty()) {
        code2 = "QRCODE 45899663";
    }
%>

<div class="bg-light p-3">
<% if (article != null) { %>
    <h3>Fiche article</h3>
    <ul>
        <li>Id: <%= article.getId() %></li>
        <li>Nom: <%= article.getName() %></li>
        <li>Référence: <%= article.getRef() %></li>
        <li>EAN: <%= article.getEan() %></li>
        <li>Date livraison: <%= article.getDeliveryDate() %></li>
        <li>Destinataire: <%= article.getRecipient() %></li>
    </ul>
    <p>Code utilisé pour la génération: <strong><%= code %></strong></p>
<% } else { %>
    <p>Aucun article trouvé pour articleId=<%= articleId %></p>
<% } %>

<!-- L’image est servie par la servlet PurchaseOrder (utilise le paramètre msg) -->
<div class="row">
  <div class="col-md-6 text-center">
    <img src="<%= request.getContextPath() %>/servlet/PurchaseOrder?msg=<%= java.net.URLEncoder.encode(code, "UTF-8") %>" alt="Code-barres" class="generated-code img-fluid" />
  </div>
  <div class="col-md-6 text-center">
    <img src="<%= request.getContextPath() %>/servlet/PurchaseOrder?msg=<%= java.net.URLEncoder.encode(code2, "UTF-8") %>&size=250&type=datamatrix&fmt=svg" alt="QR Code" class="generated-code img-fluid" />
  </div>
</div>
</div>

<hr/>

<div class="container">
<form action="PurchaseOrder" method="get" class="mb-2">
    <label>Nouveau CODEBARRE :</label>
    <input type="text" name="msg" value="<%= code %>"/>
    <button type="submit">Générer</button>
</form>
<form action="PurchaseOrder" method="get">
    <label>Nouveau Code QR :</label>
    <input type="text" name="msg" value="<%= code2 %>"/>
    <button type="submit">Générer</button>
</form>

<!-- Formulaire simple pour tester la lecture d'un article via articleId -->
<form method="get" action="barcode4j.jsp" class="mt-3">
    <label>Afficher article par articleId (id/ref/ean) : </label>
    <input type="text" name="articleId" value="<%= articleId != null ? articleId : "" %>" />
    <button type="submit">Afficher</button>
</form>
</div>
</body>
</html>
