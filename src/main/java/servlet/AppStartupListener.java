package Servlet;

import Product.Article;
import Product.Product;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class AppStartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(new Product("1","Widget A","REF-001","1234567890123"), "2026-08-01", "Alice"));
        articles.add(new Article(new Product("2","Widget B","REF-002","2345678901234"), "2026-08-02", "Bob"));
        sce.getServletContext().setAttribute("articles", articles);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
