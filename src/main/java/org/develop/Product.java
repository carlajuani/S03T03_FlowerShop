package org.develop;

/**
 * Superclass of Tree, Flower and Decoration.
 * Has as attributes a unique ID number, a reference of the product, the name, the quantity, the price and the type of product.
 */
public class Product {
    private final int ID;
    private String ref;
    private final String name;
    private int quantity;
    private final double price;
    private final ProductType productType;

    /**
     * Constructor used to create a new product added to the store.
     */
    public Product(int ID, String name, int quantity, double price, ProductType productType) {
        this.ID = ID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.productType = productType;
    }

    /**
     * Constructor overloading. Used to initialize products in method JSONProductsToHashMap().
     */
    public Product(int ID, String ref, String name, int quantity, double price, ProductType productType) {
        this.ID = ID;
        this.ref = ref;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.productType = productType;
    }

    /**
     * Enum to indicate the three unchangeable type of products.
     */
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

    /**
     * Generates an aleatory reference consisting of a single character followed by three random digits.
     * @param type To indicate the type of product (T, F or D).
     *
     */
    public void generateReference(char type) {
        String ref = String.valueOf(type);
        for (int i = 0; i < 3 ; i++) {
            int numInitial = (int) (Math.random() * 10 + 1);
            String numToAdd = Integer.toString(numInitial);
            ref += numToAdd;
        }
        this.ref = ref;
    }

    /**
     * Updates the quantity of products by subtracting the specified quantity from it.
     * @param quantity To indicate the quantity of products to sell.
     */
    public void sellQuantity(int quantity) {
        this.quantity -= quantity;
    }

    /**
     * ToString used to print a product with all its attributes.
     */
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

    /**
     * ToString used in Tree, Flower and Decoration class, to print the specified product in method printCatalog().
     */
    public String storeCatalogtoString() {
        return "";
    }

    /**
     * ToString used to print a product in method printStockQuantity().
     */
    public String stockQuantitytoString() {
        return productType+"{" +
                "ref='" + ref +
                ", name='" + name +
                ", quantity=" + quantity +
                '}';
    }

}
