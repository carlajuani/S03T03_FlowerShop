package org.develop;

/**
 * Decoration class extends from Product class and inherits all its attributes.
 * Has a specific attribute for the type of material.
 */
public class Decoration extends Product {

    private final MaterialType material;

    /**
     * Constructor used to create a new decoration product added to the store.
     */
    public Decoration(int ID, String name, int quantity, double price, MaterialType material) {
        super(ID, name, quantity, price, ProductType.DECORATION);
        super.generateReference('D');
        this.material = material;
    }

    /**
     * Constructor overloading. Used to initialize decoration products in method JSONProductsToHashMap().
     */
    public Decoration(int ID, String ref, String name, int quantity, double price, MaterialType material) {
        super(ID, ref, name, quantity, price, ProductType.DECORATION);
        this.material = material;
    }

    /**
     * Enum to indicate the two unchangeable material type.
     */
    public enum MaterialType {
        WOOD, PLASTIC
    }

    public MaterialType getMaterial() {
        return material;
    }

    /**
     * ToString used to print a decoration product with all its attributes.
     */
    @Override
    public String toString() {
        return "Decoration{" +
                "ID=" + super.getID() +
                ", ref='" + super.getRef() +
                ", name='" + super.getName() +
                ", material=" + this.material +
                ", quantity=" + super.getQuantity() +
                ", price=" + super.getPrice() +
                '}';
    }

    /**
     * ToString used to print a decoration product in method printCatalog().
     */
    public String storeCatalogtoString() {
        return "Decoration{" +
                "ref='" + super.getRef() +
                ", name='" + super.getName() +
                ", material=" + this.material +
                ", price=" + super.getPrice() +
                '}';
    }
}
