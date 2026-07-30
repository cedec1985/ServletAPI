<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="Product.Article" %>

<%
    Article article = (Article) request.getAttribute("article");

    if (article == null) {
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Fiche article</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<div class="container mt-5">
    <div class="alert alert-warning text-center shadow">
        <h4>Aucun article à afficher.</h4>
    </div>
</div>

</body>
</html>

<%
        return;
    }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Fiche article</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="bg-light">

<div class="container mt-5">

    <div class="card shadow">

        <div class="card-header bg-primary text-white">
            <h3 class="mb-0">Fiche article</h3>
        </div>

        <div class="card-body">

            <table class="table table-bordered table-striped">
                <tbody>
                <tr>
                    <th width="25%">ID</th>
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
                    <th>Date de livraison</th>
                    <td><%= article.getDeliveryDate() %></td>
                </tr>
                <tr>
                    <th>Destinataire</th>
                    <td><%= article.getRecipient() %></td>
                </tr>
                </tbody>
            </table>

            <div class="row mt-4">

                <!-- Code-barres -->
                <div class="col-md-6 text-center">
                    <div class="card h-100">
                        <div class="card-header bg-secondary text-white">
                            Code-barres
                        </div>

                        <div class="card-body">
                            <img class="img-fluid"
                                 alt="Code-barres"
                                 src="<%= request.getContextPath() %>/servlet/PurchaseOrder?msg=<%= java.net.URLEncoder.encode(article.getEan()!=null ? article.getEan() : article.getRef(),"UTF-8") %>">
                        </div>
                    </div>
                </div>

                <!-- DataMatrix -->
                <div class="col-md-6 text-center">
                    <div class="card h-100">
                        <div class="card-header bg-success text-white">
                            DataMatrix (SVG)
                        </div>

                        <div class="card-body">

                            <object
                                type="image/svg+xml"
                                data="<%= request.getContextPath() %>/servlet/PurchaseOrder?msg=<%= java.net.URLEncoder.encode(article.getEan()!=null ? article.getEan() : article.getRef(),"UTF-8") %>&size=250&type=datamatrix&fmt=svg"
                                width="250"
                                height="250">

                                Votre navigateur ne supporte pas les SVG.

                            </object>

                        </div>
                    </div>
                </div>

            </div>

        </div>

        <div class="card-footer text-end">

            <a href="<%= request.getContextPath() %>/servlet/Article"
               class="btn btn-outline-primary">
                ← Retour à la liste
            </a>

        </div>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>