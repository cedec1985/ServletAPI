/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Folder;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository {

    private static final Map<String, Product> products = new HashMap<>();

    static {
        products.put("SKU12345", new Product("SKU12345", "Gants de jardin", "GJ-001", "1234567890123"));
        products.put("SKU99999", new Product("SKU99999", "Sécateur Pro", "SP-200", "9876543210987"));
        products.put("PALLET-001", new Product("PALLET-001", "Palette mixte", "PLT-001", "1111111111111"));
    }

    public static Product find(String id) {
        return products.get(id);
    }

    public static void main(String args[]) {
        // TODO 
    }
}
