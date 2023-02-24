package org.develop;

/**
 * Flower class extends from Product class and inherits all its attributes.
 * Has a specific attribute for the colour of the flower.
 */
public class Flower extends Product {

    private final String colour;

    /**
     * Constructor used to create a new flower product added to the store.
     */
    public Flower(int ID, String name, int quantity, double price, String colour) {
        super(ID, name, quantity, price, ProductType.FLOWER);
        super.generateReference('F');
        this.colour = colour;
    }

    /**
     * Constructor overloading. Used to initialize flower products in method JSONProductsToHashMap.
     */
    public Flower(int ID, String ref, String name, int quantity, double price, String colour) {
        super(ID, ref, name, quantity, price, ProductType.FLOWER);
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    /**
     * ToString used to print a flower product with all its attributes.
     */
    @Override
    public String toString() {
        return "Flower{" +
                "ID=" + super.getID() +
                ", ref='" + super.getRef() +
                ", name='" + super.getName() +
                ", colour=" + this.colour +
                ", quantity=" + super.getQuantity() +
                ", price=" + super.getPrice() +
                '}';
    }

    /**
     * ToString used to print a flower product in method printCatalog().
     */
    public String storeCatalogtoString() {
        return "Flower{" +
                "ref='" + super.getRef() +
                ", name='" + super.getName() +
                ", colour=" + this.colour +
                ", price=" + super.getPrice() +
                '}';
    }
}