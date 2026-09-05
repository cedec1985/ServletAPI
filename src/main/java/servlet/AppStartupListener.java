package Servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import Product.Article;

@WebListener
public class AppStartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article());
        articles.add(new Article());
        sce.getServletContext().setAttribute("articles", articles);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
