package org.develop;

public class Tree extends Product {

    private final float height;

    public Tree(int ID, String name, int quantity, double price, float height) {
        super(ID, name, quantity, price, ProductType.TREE);
        super.generateReference('T');
        this.height = height;
    }

    //Constructor pel mètode JSONArrayToHashMap
    public Tree(int ID, String ref, String name, int quantity, double price, float height) {
        super(ID, ref, name, quantity, price, ProductType.TREE);
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

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

    public String storeCatalogtoString() {
        return "Tree{" +
                "ref='" + super.getRef() +
                ", name='" + super.getName() +
                ", height=" + this.height +
                ", price=" + super.getPrice() +
                '}';
    }
}

