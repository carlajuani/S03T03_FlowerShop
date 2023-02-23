package org.develop;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tools {

    public static Product.ProductType chooseProductType () {
        boolean choose = true;
        int type;
        Product.ProductType productType = null;
        do {
            type = Input.scanningForInt("""
                    Choose the type of the product:
                    1. Tree.
                    2. Flower.
                    3. Decoration.
                    Write a number between 1 to 3:""");
            switch (type) {
                case 1 -> productType = Product.ProductType.TREE;
                case 2 -> productType = Product.ProductType.FLOWER;
                case 3 -> productType = Product.ProductType.DECORATION;
                default -> {
                    System.out.println("Error. Introduce a number between 0 to 3.");
                    choose = false;
                }
            }
        } while (!choose);
        return productType;
    }

    public static Decoration.MaterialType chooseMaterialType () {
        boolean choose = true;
        int type;
        Decoration.MaterialType materialType = null;
        do {
            type = Input.scanningForInt("""
                    Choose the type of the material:
                    1. Wood.
                    2. Plastic.
                    Write a number between 1 or 2:""");
            switch (type) {
                case 1 -> materialType = Decoration.MaterialType.WOOD;
                case 2 -> materialType = Decoration.MaterialType.PLASTIC;
                default -> {
                    System.out.println("Error. You have to write 1 or 2.");
                    choose = false;
                }
            }
        } while (!choose);
        return materialType;
    }

    public static Product createProduct (String storeName) {
        Product product = null;
        Product.ProductType productType = Tools.chooseProductType();
        String name = Input.scanningForString("Introduce name:");
        int quantity = Input.scanningForInt("Introduce quantity:");
        double price = Input.scanningForDouble("Introduce price:");
        String trimmedStoreName = storeName.trim().replace(" ","_");
        int ID = Reader.readLastID("Products"+trimmedStoreName+".txt");

        if (productType == Product.ProductType.TREE) {
            float height = Input.scanningForFloat("Introduce height:");
            product = new Tree(ID, name, quantity, price, height);
        } else if (productType == Product.ProductType.FLOWER) {
            String colour = Input.scanningForString("Introduce colour:");
            product = new Flower(ID, name, quantity, price, colour);
        } else if (productType == Product.ProductType.DECORATION) {
            Decoration.MaterialType materialType = Tools.chooseMaterialType();
            product = new Decoration(ID, name, quantity, price, materialType);
        }
        System.out.println("Product of type " +productType+ " added correctly.");
        return product;
    }

    //PRODUCT
    public static Product JSONProductToProduct (JSONObject productJSON) {
        Product product = null;
        int ID = productJSON.getInt("ID");
        String ref = productJSON.getString("ref");
        String name = productJSON.getString("name");
        int quantity = productJSON.getInt("quantity");
        double price = productJSON.getDouble("price");
        Product.ProductType type = Product.ProductType.valueOf(productJSON.getString("productType"));

        if (type == Product.ProductType.TREE) {
            float height = productJSON.getFloat("height");
            product = new Tree(ID, ref, name, quantity, price, height);
        } else if (type == Product.ProductType.FLOWER) {
            String colour = productJSON.getString("colour");
            product = new Flower(ID, ref, name, quantity, price, colour);
        } else if (type == Product.ProductType.DECORATION) {
            Decoration.MaterialType materialType = Decoration.MaterialType.valueOf(productJSON.getString("material"));
            product = new Decoration(ID, ref, name, quantity, price, materialType);
        }
        return product;
    }

    //PRODUCTS
    public static HashMap<String,Product> JSONProductsToHashMap (JSONArray storeStockJSON) {
        HashMap<String,Product> storeStock = new HashMap<>();
        Product product = null;
        String ref;

        for (Object obj: storeStockJSON) {
            JSONObject object = (JSONObject) obj;
            int ID = object.getInt("ID");
            ref = object.getString("reference");
            String name = object.getString("name");
            int quantity = object.getInt("quantity");
            double price = object.getDouble("price");
            Product.ProductType type = Product.ProductType.valueOf(object.getString("type"));

            if (type == Product.ProductType.TREE) {
                float height = object.getFloat("height");
                product = new Tree(ID, ref, name, quantity, price, height);
            } else if (type == Product.ProductType.FLOWER) {
                String colour = object.getString("colour");
                product = new Flower(ID, ref, name, quantity, price, colour);
            } else if (type == Product.ProductType.DECORATION) {
                Decoration.MaterialType materialType = Decoration.MaterialType.valueOf(object.getString("material"));
                product = new Decoration(ID, ref, name, quantity, price, materialType);
            }
            storeStock.put(ref, product);
        }
        return storeStock;
    }

    //TICKETS
    public static HashMap<Integer,ITicket> JSONTicketsToHashMap (JSONArray ticketArrayJSON) {
        HashMap<Integer,ITicket> salesHistory = new HashMap<>();
        //estem recuperant tots els tickets en un hashmap salesHistory
        for (Object ticket: ticketArrayJSON) {
            JSONObject object = (JSONObject) ticket;
            int ID = object.getInt("ID");
            //estem recuperant totes les ticketLines en un array ticketLines
            JSONArray ticketLines = object.getJSONArray("ticketLines");
            List<TicketLine> tLines = new ArrayList<>();
            for (Object line: ticketLines) {
                JSONObject lineJSON = (JSONObject) line;
                JSONObject productJSON = lineJSON.getJSONObject("product");
                Product product = JSONProductToProduct(productJSON);
                int quantity = lineJSON.getInt("quantity");
                tLines.add(tLines.size(), new TicketLine(product, quantity));
            }
            double totalPrice = object.getDouble("totalPrice");
            //una vegada recuperats id, array de lines i el preu fem nou ticket i l'afegim al HashMap de tickets
            Ticket newTicket = new Ticket(ID, tLines, totalPrice);
            salesHistory.put(ID, newTicket);
            }
        return salesHistory;
    }

    public static void showStockValue (HashMap<String,Product> storeStock) {
        double stockValue = 0;
        for (Product product : storeStock.values()) {
            stockValue += product.getQuantity()*product.getPrice();
        }
        System.out.println("TOTAL stock value:" +stockValue+ "€\n");
    }

    public static void showSalesValue (HashMap<Integer,ITicket> salesHistory) {
        double salesValue = 0;
        for (ITicket ticket : salesHistory.values()) {
            salesValue += ((Ticket) ticket).getTotalPrice();
        }
        System.out.println("Store's TOTAL sales: " +salesValue+ "€\n");
    }
}
