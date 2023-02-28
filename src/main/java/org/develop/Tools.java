package org.develop;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tools {

    /**
     * Utility method that prompts the customer to choose the type of product to create.
     */
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

    /**
     * Utility method that prompts the customer to choose the type of material to create a decoration product.
     */
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

    /**
     * Creates a new product based on customer input and returns it.
     * @param storeName Indicates the name of the store where the new product will be added.
     * @return The newly created Product object.
     */
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

    /**
     * Converts a JSONObject representing a Product object, to a new Product object.
     * @param productJSON The JSONObject to be converted.
     * @return The newly created Product object.
     */
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

    /**
     * Converts a JSONArray of JSONObjects representing Product objects into a HashMap of Product objects.
     * @param storeStockJSON The JSONArray representing the Product objects to be converted.
     * @return The newly created HashMap of Product objects.
     */
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

    /**
     * Converts a JSONArray of tickets in JSON format, to a HashMap of ITicket objects.
     * This method iterates through each Ticket object in the JSONArray and retrieves its ID, ticket lines, and total price.
     * For each ticket line, it then extracts the product and quantity information in the ticketLines array,
     * and creates a new TicketLine object with this information.
     * It then creates a new ITicket object with the extracted ID, list of TicketLine objects and total Price,
     * and adds it to a HashMap with the ID as the key and the ITicket as the value.
     * The resulting HashMap stores the sales history of the store.
     * @param ticketArrayJSON A JSONArray containing ticket data in JSON format.
     * @return A HashMap of ITicket objects representing the sales history of the store.
     */
    public static HashMap<Integer,ITicket> JSONTicketsToHashMap (JSONArray ticketArrayJSON) {
        HashMap<Integer,ITicket> salesHistory = new HashMap<>();
        for (Object ticket: ticketArrayJSON) {
            JSONObject object = (JSONObject) ticket;
            int ID = object.getInt("ID");
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
            Ticket newTicket = new Ticket(ID, tLines, totalPrice);
            salesHistory.put(ID, newTicket);
            }
        return salesHistory;
    }

    /**
     * Calculates and displays the total value of the stock in a given store, based on the quantity and price of each product.
     * @param storeStock A HashMap containing the current stock of the store,
     *                   where the keys are the product references and the values are the product objects.
     */
    public static void showStockValue (HashMap<String,Product> storeStock) {
        double stockValue = 0;
        for (Product product : storeStock.values()) {
            stockValue += product.getQuantity()*product.getPrice();
        }
        System.out.println("TOTAL stock value:" +stockValue+ "€\n");
    }

    /**
     * Calculates the total sales value for the store based on the sales history provided in a HashMap of ITicket objects.
     * @param salesHistory A HashMap containing the store's sales history,
     *                     with ticket IDs as keys and corresponding ITicket objects as values.
     */
    public static void showSalesValue (HashMap<Integer,ITicket> salesHistory) {
        double salesValue = 0;
        for (ITicket ticket : salesHistory.values()) {
            salesValue += ((Ticket) ticket).getTotalPrice();
        }
        System.out.println("Store's TOTAL sales: " +salesValue+ "€\n");
    }
}
