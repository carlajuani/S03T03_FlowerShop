package org.develop;

public class Decoration extends Product {

    private final MaterialType material;

    public Decoration(int ID, String name, int quantity, double price, MaterialType material) {
        super(ID, name, quantity, price, ProductType.DECORATION);
        super.generateReference('D');
        this.material = material;
    }

    //Constructor pel mètode JSONArrayToHashMap
    public Decoration(int ID, String ref, String name, int quantity, double price, MaterialType material) {
        super(ID, ref, name, quantity, price, ProductType.DECORATION);
        this.material = material;
    }

    public enum MaterialType {
        WOOD, PLASTIC
    }

    public MaterialType getMaterial() {
        return material;
    }

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

    public String storeCatalogtoString() {
        return "Decoration{" +
                "ref='" + super.getRef() +
                ", name='" + super.getName() +
                ", material=" + this.material +
                ", price=" + super.getPrice() +
                '}';
    }
}
