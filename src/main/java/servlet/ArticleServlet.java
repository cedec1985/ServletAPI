package Servlet;

import Product.Article;
import Product.ArticleRepository;
import Product.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/servlet/Article"})
public class ArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ArticleRepository repo = ArticleRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Article> articles = repo.findAll();
        req.setAttribute("articles", articles);
        req.getRequestDispatcher("/articleForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String ref = req.getParameter("ref");
        String ean = req.getParameter("ean");
        String deliveryDate = req.getParameter("deliveryDate");
        String recipient = req.getParameter("recipient");

        Product p = new Product(id, name, ref, ean);
        Article a = new Article(p, deliveryDate, recipient);
        Article saved = repo.save(a);

        // update application attribute for barcode4j.jsp lookup convenience
        req.getServletContext().setAttribute("articles", repo.findAll());

        req.setAttribute("article", saved);
        req.getRequestDispatcher("/articleView.jsp").forward(req, resp);
    }
}
