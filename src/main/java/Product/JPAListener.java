package Product;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener
public class JPAListener implements ServletContextListener {

    private EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Le nom "servletapiPU" doit correspondre à persistence-unit dans persistence.xml
        emf = Persistence.createEntityManagerFactory("servletapiPU");
        JpaArticleDAO dao = new JpaArticleDAO(emf);
        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("articleDAO", dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        ctx.removeAttribute("articleDAO");
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
