package Product;

import javax.persistence.*;
import java.util.List;

public class JpaArticleDAO implements ArticleDAO {

    private final EntityManagerFactory emf;

    public JpaArticleDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Article save(Article article) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (article.getId() == null) {
                em.persist(article);
            } else {
                article = em.merge(article);
            }
            tx.commit();
            return article;
        } catch (RuntimeException ex) {
            if (tx.isActive()) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public Article findByEan(String ean) {
        if (ean == null) return null;
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Article> q = em.createQuery("SELECT a FROM Article a WHERE a.ean = :ean", Article.class);
            q.setParameter("ean", ean);
            List<Article> list = q.getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public Article findByRef(String ref) {
        if (ref == null) return null;
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Article> q = em.createQuery("SELECT a FROM Article a WHERE a.ref = :ref", Article.class);
            q.setParameter("ref", ref);
            List<Article> list = q.getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public Article findById(Long id) {
        if (id == null) return null;
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Article.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Article> listAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Article> q = em.createQuery("SELECT a FROM Article a ORDER BY a.id", Article.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
