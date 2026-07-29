<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="Product.Article" %>
<html>
<head>
    <title>Créer Article</title>
</head>
<body>
<h2>Créer un article</h2>
<form method="post" action="<%= request.getContextPath()%>/servlet/Article">
    Nom: <input type="text" name="name" required/><br/>
    Référence: <input type="text" name="ref" required/><br/>
    EAN: <input type="text" name="ean" required/><br/>
    Date livraison: <input type="date" name="deliveryDate"/><br/>
    Destinataire: <input type="text" name="recipient"/><br/>
    <button type="submit">Ajouter</button>
</form>

<h3>Articles existants</h3>
<ul>
<% java.util.List<Product.Article> list = (java.util.List<Product.Article>) request.getAttribute("articles");
   if (list != null) {
       for (Product.Article a : list) {
%>
    <li><strong><%= a.getName() %></strong> — ref: <%= a.getRef() %> — ean: <%= a.getEan() %>
        — <a href="<%= request.getContextPath() %>/barcode4j.jsp?articleId=<%= a.getEan() != null ? a.getEan() : a.getId() %>">Voir</a>
    </li>
<%     }
   } else { %>
    <li>Aucun article</li>
<% } %>
</ul>
</body>
</html>
