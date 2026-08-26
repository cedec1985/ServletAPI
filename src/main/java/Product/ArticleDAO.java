package Product;

import java.util.List;

public interface ArticleDAO {
    Article save(Article article);
    Article findByEan(String ean);
    Article findByRef(String ref);
    Article findById(Long id);
    List<Article> listAll();
}
