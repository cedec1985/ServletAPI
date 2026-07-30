<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="Product.Article" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
        
         <link rel="stylesheet" href="css/style.css">
    </head>
  <body class="bg-light text-center justify-content-center">

<div class="container py-5>
        <span class="bord bord-haut">Coller le ruban adhésif ici</span>
        <span class="bord bord-droite">Coller le ruban adhésif ici</span>
        <span class="bord bord-bas">Coller le ruban adhésif ici</span>
        <span class="bord bord-gauche">Coller le ruban adhésif ici</span>
      

<div class="text-center mb-5">

    <img src="images/BAL.png" width="70">

    <img src="images/mondialrelay.jpg"
         class="ms-3"
         width="220">

    <h2 class="mt-4">
        Génération des étiquettes
    </h2>

    <p class="text-muted">
        Mondial Relay
    </p>

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
   <div class="card shadow-sm mb-4">
    <div class="card-header bg-primary text-white">
        Fiche article
    </div>

    <div class="card-body">
        <table class="table table-striped table-bordered">
            <tbody>
                <tr>
                    <th width="30%">ID</th>
                    <td><%= article.getId() %></td>
                </tr>
                <tr>
                    <th>Nom</th>
                    <td><%= article.getName() %></td>
                </tr>
                <tr>
                    <th>Référence</th>
                    <td><%= article.getRef() %></td>
                </tr>
                <tr>
                    <th>EAN</th>
                    <td><%= article.getEan() %></td>
                </tr>
                <tr>
                    <th>Date livraison</th>
                    <td><%= article.getDeliveryDate() %></td>
                </tr>
                <tr>
                    <th>Destinataire</th>
                    <td><%= article.getRecipient() %></td>
                </tr>
            </tbody>
        </table>

        <div class="alert alert-info mb-0">
            Code utilisé :
            <strong><%= code %></strong>
        </div>

    </div>
</div>
    <p>Code utilisé pour la génération: <strong><%= code %></strong></p>
<% } else { %>
    <p>Aucun article trouvé pour articleId=<%= articleId %></p>
<% } %>

<div class="row g-4">

    <div class="col-md-6">
        <div class="card text-center shadow-sm">

            <div class="card-header">
                Code-barres
            </div>

            <div class="card-body">

                <img
                    src="<%= request.getContextPath() %>/servlet/PurchaseOrder?msg=<%= java.net.URLEncoder.encode(code,"UTF-8") %>"
                    class="img-fluid p-3"
                    alt="Code-barres">

            </div>

        </div>
    </div>

    <div class="col-md-6">
        <div class="card text-center shadow-sm">

            <div class="card-header">
                DataMatrix / QR
            </div>

            <div class="card-body">

                <img
                    src="<%= request.getContextPath() %>/servlet/PurchaseOrder?msg=<%= java.net.URLEncoder.encode(code2,"UTF-8") %>&size=250&type=datamatrix&fmt=svg"
                    class="img-fluid p-3"
                    alt="QR">

            </div>

        </div>
    </div>

</div>

<hr/>
<form action="PurchaseOrder" method="get" class="row g-3 mb-4">

    <div class="col-md-10">
        <label class="form-label">Nouveau Code-barres</label>
        <input
            type="text"
            name="msg"
            value="<%= code %>"
            class="form-control">
    </div>

    <div class="col-md-2 d-grid align-items-end">
        <button class="btn btn-primary">
            Générer
        </button>
    </div>

</form>
<form action="PurchaseOrder" method="get" class="row g-3 mb-4">

    <div class="col-md-10">
        <label class="form-label">Nouveau DataMatrix</label>
        <input
            type="text"
            name="msg"
            value="<%= code2 %>"
            class="form-control">
    </div>

    <div class="col-md-2 d-grid align-items-end">
        <button class="btn btn-success">
            Générer
        </button>
    </div>

</form>
<form method="get" action="barcode4j.jsp" class="row g-3">

    <div class="col-md-10">
        <label class="form-label">
            Rechercher un article (id, ref ou EAN)
        </label>

        <input
            type="text"
            class="form-control"
            name="articleId"
            value="<%= articleId != null ? articleId : "" %>">
    </div>

    <div class="col-md-2 d-grid align-items-end">
        <button class="btn btn-dark">
            Rechercher
        </button>
    </div>

</form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</body>
</html>
