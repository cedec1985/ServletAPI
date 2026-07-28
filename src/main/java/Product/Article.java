package Product;

public class Article {
    private Product product;
    private String deliveryDate;
    private String recipient;

    public Article() {}

    public Article(Product product, String deliveryDate, String recipient) {
        this.product = product;
        this.deliveryDate = deliveryDate;
        this.recipient = recipient;
    }

    public Product getProduct() {
        return product;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getRecipient() {
        return recipient;
    }

    // Convenience getters delegating to Product
    public String getId() { return product != null ? product.getId() : null; }
    public String getName() { return product != null ? product.getName() : null; }
    public String getRef() { return product != null ? product.getRef() : null; }
    public String getEan() { return product != null ? product.getEan() : null; }
}
