package org.develop;
import org.json.*;

import java.io.*;

public class Writer {

    //STORE
    public static void writeStoreJSON (Store store) {
        String storeNameTrimmed = store.getStoreName().trim().replace(" ","_");
        JSONObject storeJSON = new JSONObject();
        storeJSON.put("name", storeNameTrimmed);
        try {
            File file = new File ("src/main/resources/Stores.txt");
            File productsFile = new File("src/main/resources/Products"+storeNameTrimmed+".txt");
            productsFile.createNewFile();
            File ticketsFile = new File("src/main/resources/Tickets"+storeNameTrimmed+".txt");
            ticketsFile.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(storeJSON + "\n");
            bw.close();
        } catch (IOException e) {
            System.out.println("ERROR. Unable to save store into file.");
        }
    }

    //PRODUCTS
    public static JSONObject createProductJSON (Product product) {
        JSONObject productJSON = new JSONObject();
        productJSON.put("ID", product.getID());
        productJSON.put("reference", product.getRef());
        productJSON.put("name", product.getName());
        productJSON.put("quantity", product.getQuantity());
        productJSON.put("price", product.getPrice());
        productJSON.put("type", String.valueOf(product.getProductType()));

        if (product.getProductType() == Product.ProductType.TREE) {
            Tree tree = (Tree) product;
            productJSON.put("height", tree.getHeight());
        } else if (product.getProductType() == Product.ProductType.FLOWER) {
            Flower flower = (Flower) product;
            productJSON.put("colour", flower.getColour());
        } else if (product.getProductType() == Product.ProductType.DECORATION) {
            Decoration decoration = (Decoration) product;
            productJSON.put ("material", String.valueOf(decoration.getMaterial()));
        }
        return productJSON;
    }

    public static void writeProductJSON (JSONObject productJSON, String storeName) {
        String trimmedStoreName = storeName.trim().replace(" ","_");
        String fileName = "Products" + trimmedStoreName;
        try {
            File file = new File ("src/main/resources/" + fileName + ".txt");
            BufferedWriter br = new BufferedWriter(new FileWriter(file, true));
            br.write(productJSON.toString() + "\n");
            br.close();
        } catch (IOException e) {
            System.out.println("Unable to save product into file.");
        }
    }

    public static void addProductJSON (Product product, String storeName) {
        JSONObject productJSON = createProductJSON(product);
        writeProductJSON(productJSON, storeName);
    }

    //REMOVE PRODUCT
    public static void removeProductJSON (String ref, String storeName) {
        String trimmedStoreName = storeName.trim().replace(" ","_");
        try {
            File originalFile = new File("src/main/resources/Products" + trimmedStoreName + ".txt");
            File temporalFile = new File("src/main/resources/Products" + trimmedStoreName + "Temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(originalFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(temporalFile));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                if (currentLine.contains(ref)) continue;
                bw.write(currentLine + System.getProperty("line.separator"));
            }
            br.close();
            bw.close();
            originalFile.delete();
            temporalFile.renameTo(originalFile);
        } catch (RuntimeException | IOException e) {
            System.out.println("ERROR. Unable to remove product.");
        }
    }

    //UPDATE PRODUCT
    public static void updateJSONProduct (Product product, String storeName) {
        Writer.removeProductJSON(product.getRef(), storeName);
        JSONObject productJSON = createProductJSON(product);
        Writer.writeProductJSON(productJSON, storeName);
    }

    //TICKET
    public static void writeTicketJSON (Ticket ticket, String storeName) {
        JSONObject ticketJSON = new JSONObject();
        String fileName = "Tickets" + storeName.trim().replace(" ","_");
        ticketJSON.put("ID", ticket.getID());
        ticketJSON.put("ticketLines", ticket.getTicketLines());
        ticketJSON.put("totalPrice", ticket.getTotalPrice());
        try {
            File file = new File ("src/main/resources/" + fileName + ".txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(ticketJSON + "\n");
            bw.close();
        } catch (IOException e) {
            System.out.println("ERROR. Unable to write ticket into file.");
        }
    }
}
