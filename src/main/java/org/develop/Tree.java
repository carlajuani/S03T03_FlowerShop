package org.develop;

/**
 * Tree class extends from Product class and inherits all its attributes.
 * Has a specific attribute for the height of the tree.
 */
public class Tree extends Product {

    private final float height;

    /**
     * Constructor used to create a new tree product added to the store.
     */
    public Tree(int ID, String name, int quantity, double price, float height) {
        super(ID, name, quantity, price, ProductType.TREE);
        super.generateReference('T');
        this.height = height;
    }

    /**
     * Constructor overloading. Used to initialize tree products in method JSONProductsToHashMap.
     */
    public Tree(int ID, String ref, String name, int quantity, double price, float height) {
        super(ID, ref, name, quantity, price, ProductType.TREE);
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    /**
     * ToString used to print a tree product with all its attributes.
     */
    @Override
    public String toString() {
        return "Tree{" +
                "ID=" + super.getID() +
                ", ref='" + super.getRef() +
                ", name='" + super.getName() +
                ", height=" + this.height +
                ", quantity=" + super.getQuantity() +
                ", price=" + super.getPrice() +
                '}';
    }

    /**
     * ToString used to print a tree product in method printCatalog().
     */
    public String storeCatalogtoString() {
        return "Tree{" +
                "ref='" + super.getRef() +
                ", name='" + super.getName() +
                ", height=" + this.height +
                ", price=" + super.getPrice() +
                '}';
    }
}

