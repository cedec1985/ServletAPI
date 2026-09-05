package Product;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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

    public Article() {
        /*
         * This no-arg constructor is intentionally left empty because JPA/Hibernate
         * creates entities via reflection and populates fields afterward.
         * Keeping it empty is required for entity instantiation; initialization is
         * done through setters or persistence provider metadata.
         */
    }

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

    public void setProduct(Product p) {
        this.name = p.getName();
        this.ref = p.getRef();
        this.ean = p.getEan();
    }

    public void setDeliveryDate(String deliveryDate2) {
        this.deliveryDate = LocalDate.parse(deliveryDate2);
    }

    public void setDate(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
