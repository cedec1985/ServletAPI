package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Product.Article;
import Product.ArticleRepository;
import Product.Product;

@WebServlet(urlPatterns = {"/servlet/ArticleServlet"})
public class ArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final transient ArticleRepository repo = ArticleRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Article> articles = repo.findAll();
        req.setAttribute("articles", articles);
        try {
            req.getRequestDispatcher("/articleForm.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            try {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Unable to forward request to articleForm.jsp");
            } catch (IOException ioException) {
                throw new RuntimeException("Unable to send error response for articleForm.jsp", ioException);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String ref = req.getParameter("ref");
        String ean = req.getParameter("ean");
        String deliveryDate = req.getParameter("deliveryDate");
        String recipient = req.getParameter("recipient");

        Product p = new Product(id, name, ref, ean);
        Article a = new Article();
        a.setProduct(p);
        a.setDeliveryDate(deliveryDate);
        a.setRecipient(recipient);
        Article saved = repo.save(a);

        // update application attribute for barcode4j.jsp lookup convenience
        req.getServletContext().setAttribute("articles", repo.findAll());

        req.setAttribute("article", saved);
        try {
            req.getRequestDispatcher("/articleView.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            try {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Unable to forward request to articleView.jsp");
            } catch (IOException ioException) {
                throw new RuntimeException("Unable to send error response for articleView.jsp", ioException);
            }
        }
    }
}
