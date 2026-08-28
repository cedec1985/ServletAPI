package Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "FindArticleServlet", urlPatterns = {"/servlet/FindArticle"})
public class FindArticleServlet extends HttpServlet {

    private ArticleDAO dao;

    @Override
    public void init() throws ServletException {
        // Récupère le DAO exposé par le JPAListener
        Object o = getServletContext().getAttribute("articleDAO");
        if (o instanceof ArticleDAO) {
            dao = (ArticleDAO) o;
        } else {
            throw new ServletException("ArticleDAO non disponible dans le contexte. Vérifiez JPAListener.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code == null || code.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        code = code.trim();

        Article found = dao.findByEan(code);
        if (found == null) found = dao.findByRef(code);
        if (found == null) {
            // essai si c'est un id numérique
            try {
                Long id = Long.parseLong(code);
                found = dao.findById(id);
            } catch (NumberFormatException ignored) {}
        }

        if (found != null) {
            req.setAttribute("article", found);
            req.getRequestDispatcher("/WEB-INF/articleView.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Aucun article trouvé pour : " + code);
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().println("<html><body>");
            resp.getWriter().println("<p>Aucun article trouvé pour : " + code + "</p>");
            resp.getWriter().println("<p><a href=\"" + req.getContextPath() + "/\">Retour à l'accueil</a></p>");
            resp.getWriter().println("</body></html>");
        }
    }
}