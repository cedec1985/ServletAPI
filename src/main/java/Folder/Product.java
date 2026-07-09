
package Folder;

/**
 *
 * @author cedric
 */

public class Product {
    private String id;
    private String name;
    private String ref;
    private String ean;

    public Product(String id, String name, String ref, String ean) {
        this.id = id;
        this.name = name;
        this.ref = ref;
        this.ean = ean;
    }
    
    public Product (){}
    public String getId() { return id; }
    public String getName() { return name; }
    public String getRef() { return ref; }
    public String getEan() { return ean; }

public static void main(String args[]) {
        // TODO code application logic here
    }
}