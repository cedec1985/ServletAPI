package Product;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "articles", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ref"}),
        @UniqueConstraint(columnNames = {"ean"})
})
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String ref;

    private String ean;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    private String recipient;

    public Article() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRef() { return ref; }
    public void setRef(String ref) { this.ref = ref; }

    public String getEan() { return ean; }
    public void setEan(String ean) { this.ean = ean; }

    public LocalDate getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ref='" + ref + '\'' +
                ", ean='" + ean + '\'' +
                '}';
    }
}
