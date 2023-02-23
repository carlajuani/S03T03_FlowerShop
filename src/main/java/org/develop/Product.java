package org.develop;

public class Product {
    private final int ID;
    private String ref;
    private final String name;
    private int quantity;
    private final double price;
    private final ProductType productType;


    public Product(int ID, String name, int quantity, double price, ProductType productType) {
        this.ID = ID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.productType = productType;
    }

    //Constructor pel mètode JSONArrayToHashMap
    public Product(int ID, String ref, String name, int quantity, double price, ProductType productType) {
        this.ID = ID;
        this.ref = ref;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.productType = productType;
    }

    public enum ProductType {
        TREE, FLOWER, DECORATION
    }

    public int getID() {
        return ID;
    }

    public String getRef() {
        return ref;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void generateReference(char type) {
        String ref = String.valueOf(type);
        for (int i = 0; i < 3 ; i++) {
            int numInitial = (int) (Math.random() * 10 + 1);
            String numToAdd = Integer.toString(numInitial);
            ref += numToAdd;
        }
        this.ref = ref;
    }

    public void sellQuantity(int quantity) {
        this.quantity -= quantity;
    }

    @Override
    public String toString() {
        return productType+"{" +
                "ID=" + ID +
                ", ref='" + ref +
                ", name='" + name +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public String storeCatalogtoString() {
        return "";
    }

    public String stockQuantitytoString() {
        return productType+"{" +
                "ref='" + ref +
                ", name='" + name +
                ", quantity=" + quantity +
                '}';
    }

}
