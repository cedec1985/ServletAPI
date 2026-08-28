package Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

// Simple repository en mémoire (remplacez par une DAO/BD si besoin)
public class ArticleRepository {
    private final List<Article> articles = Collections.synchronizedList(new ArrayList<>());
    private final AtomicInteger counter = new AtomicInteger(1);

    private static final ArticleRepository INSTANCE = new ArticleRepository();
    private ArticleRepository(){}

    public static ArticleRepository getInstance() { return INSTANCE; }

    public Article save(Article a) {
        if (a == null) return null;
        // If product has no id, generate one
        if (a.getId() == null ) {
            String id = String.valueOf(counter.getAndIncrement());
            // Product has no setter; create a new Product with the id
            // Avoid calling a.getProduct() if Article does not expose it
            Product newP = new Product(id, null, null, null);
            Article newA = new Article(newP, a.getDeliveryDate(), a.getRecipient());
            articles.add(newA);
            return newA;
        } else {
            articles.add(a);
            return a;
        }
    }

    public List<Article> findAll() {
        return new ArrayList<>(articles);
    }

    public Optional<Article> findByEan(String ean) {
        if (ean == null) return Optional.empty();
        return articles.stream().filter(x -> ean.equals(x.getEan())).findFirst();
    }

    public Optional<Article> findById(String id) {
        if (id == null) return Optional.empty();
        return articles.stream().filter(x -> id.equals(x.getId())).findFirst();
    }

    public Optional<Article> findByRef(String ref) {
        if (ref == null) return Optional.empty();
        return articles.stream().filter(x -> ref.equals(x.getRef())).findFirst();
    }
}
