package org.develop;

public class Flower extends Product {

    private final String colour;

    public Flower(int ID, String name, int quantity, double price, String colour) {
        super(ID, name, quantity, price, ProductType.FLOWER);
        super.generateReference('F');
        this.colour = colour;
    }

    //Constructor pel mètode JSONArrayToHashMap
    public Flower(int ID, String ref, String name, int quantity, double price, String colour) {
        super(ID, ref, name, quantity, price, ProductType.FLOWER);
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

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

    public String storeCatalogtoString() {
        return "Flower{" +
                "ref='" + super.getRef() +
                ", name='" + super.getName() +
                ", colour=" + this.colour +
                ", price=" + super.getPrice() +
                '}';
    }
}