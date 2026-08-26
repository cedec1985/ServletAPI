package Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "ArticleServlet", urlPatterns = {"/servlet/Article"})
public class ArticleServlet extends HttpServlet {

    private ArticleDAO dao;

    @Override
    public void init() throws ServletException {
        Object o = getServletContext().getAttribute("articleDAO");
        if (o instanceof ArticleDAO) dao = (ArticleDAO) o;
        else throw new ServletException("ArticleDAO non disponible");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String ref = req.getParameter("ref");
        String ean = req.getParameter("ean");
        String delivery = req.getParameter("deliveryDate");
        String recipient = req.getParameter("recipient");

        Article a = new Article();
        a.setName(name);
        a.setRef(ref);
        a.setEan(ean);
        if (delivery != null && !delivery.trim().isEmpty()) {
            a.setDeliveryDate(LocalDate.parse(delivery));
        }
        a.setRecipient(recipient);

        dao.save(a);

        resp.sendRedirect(req.getContextPath() + "/"); // ou montrer la liste
    }
}
