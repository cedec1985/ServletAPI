package Product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FindArticleServlet", urlPatterns = {"/servlet/FindArticle"})
public class FindArticleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Le DAO est injecté depuis le contexte Servlet et ne doit pas être sérialisé.
    private transient ArticleDAO dao;

    @Override
    public void init() throws ServletException {
        // Récupère le DAO exposé par le JPAListener
        Object o = getServletContext().getAttribute("articleDAO");
        if (o instanceof ArticleDAO articledao) {
            dao = articledao;
        } else {
            throw new ServletException("ArticleDAO non disponible dans le contexte. Vérifiez JPAListener.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code == null || code.trim().isEmpty()) {
            try {
                resp.sendRedirect(req.getContextPath() + "/");
            } catch (IOException e) {
                getServletContext().log("Redirection vers l'accueil a échoué", e);
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.setContentType("text/plain;charset=UTF-8");
                try {
                    resp.getWriter().println("Erreur lors de la redirection vers l'accueil.");
                } catch (IOException writerException) {
                    getServletContext().log("Écriture du message d'erreur de redirection a échoué", writerException);
                }
            }
            return;
        }
        code = code.trim();

        Article found = dao.findByEan(code);
        if (found == null) found = dao.findByRef(code);
        if (found == null) {
            // essai si c'est un id numérique
            try {
                Long id = Long.valueOf(code);
                found = dao.findById(id);
            } catch (NumberFormatException ignored) {}
        }

        if (found != null) {
            req.setAttribute("article", found);
            req.getRequestDispatcher("/articleView.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Aucun article trouvé pour : " + code);
            resp.setContentType("text/html;charset=UTF-8");
            try {
                resp.getWriter().println("<html><body>");
                resp.getWriter().println("<p>Aucun article trouvé pour : " + code + "</p>");
                resp.getWriter().println("<p><a href=\"" + req.getContextPath() + "/\">Retour à l'accueil</a></p>");
                resp.getWriter().println("</body></html>");
            } catch (IOException e) {
                getServletContext().log("Écriture de la réponse a échoué", e);
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
}