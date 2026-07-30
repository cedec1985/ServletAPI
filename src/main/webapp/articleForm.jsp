<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="Product.Article" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Créer un Article</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- CSS personnalisé -->
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="bg-light">

<div class="container mt-5">

    <div class="row justify-content-center">
        <div class="col-lg-8">

            <!-- Carte Formulaire -->
            <div class="card shadow mb-4">
                <div class="card-header bg-primary text-white">
                    <h3 class="mb-0">Créer un article</h3>
                </div>

                <div class="card-body">

                    <form method="post" action="<%= request.getContextPath()%>/servlet/Article">

                        <div class="mb-3">
                            <label class="form-label">Nom</label>
                            <input type="text" class="form-control" name="name" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Référence</label>
                            <input type="text" class="form-control" name="ref" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">EAN</label>
                            <input type="text" class="form-control" name="ean" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Date de livraison</label>
                            <input type="date" class="form-control" name="deliveryDate">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Destinataire</label>
                            <input type="text" class="form-control" name="recipient">
                        </div>

                        <button type="submit" class="btn btn-success">
                            Ajouter l'article
                        </button>

                    </form>

                </div>
            </div>

            <!-- Liste des articles -->
            <div class="card shadow">
                <div class="card-header bg-dark text-white">
                    <h4 class="mb-0">Articles existants</h4>
                </div>

                <div class="card-body">

                    <%
                        java.util.List<Product.Article> list =
                            (java.util.List<Product.Article>) request.getAttribute("articles");
                    %>

                    <% if (list != null && !list.isEmpty()) { %>

                    <div class="table-responsive">
                        <table class="table table-striped table-hover align-middle">
                            <thead class="table-primary">
                            <tr>
                                <th>Nom</th>
                                <th>Référence</th>
                                <th>EAN</th>
                                <th>Action</th>
                            </tr>
                            </thead>

                            <tbody>

                            <% for(Product.Article a : list){ %>

                            <tr>
                                <td><%= a.getName() %></td>
                                <td><%= a.getRef() %></td>
                                <td><%= a.getEan() %></td>
                                <td>
                                    <a class="btn btn-outline-primary btn-sm"
                                       href="<%= request.getContextPath() %>/barcode4j.jsp?articleId=<%= a.getEan()!=null ? a.getEan() : a.getId() %>">
                                        Voir
                                    </a>
                                </td>
                            </tr>

                            <% } %>

                            </tbody>
                        </table>
                    </div>

                    <% } else { %>

                    <div class="alert alert-warning text-center mb-0">
                        Aucun article enregistré.
                    </div>

                    <% } %>

                </div>
            </div>

        </div>
    </div>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>