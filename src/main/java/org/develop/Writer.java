package org.develop;
import org.json.*;
import java.io.*;

public class Writer {

    /**
     * Writes a new store in the "Stores.txt" file and creates empty files for the store's products and tickets.
     * @param store The Store object to be written in the file.
     */
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

    /**
     * This method creates a JSONObject representing a Product object.
     * The JSONObject includes the ID, reference, name, quantity, price, and type of the product.
     * If the product is of type Tree, the height of the tree is also included.
     * If the product is of type Flower, the colour of the flower is also included.
     * If the product is of type Decoration, the material of the decoration is also included.
     * @param product The Product object to convert into a JSONObject.
     * @return A JSONObject representing the Product object.
     */
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

    /**
     * Writes a JSON representation of a product to a file with the name of the store.
     * The name of the file will be "Products" followed by the trimmed store name.
     * The JSON object passed as a parameter will be written into the file.
     * @param productJSON The JSON representation of the product to be written to the file.
     * @param storeName The name of the store that the product belongs to.
     */
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

    /**
     * Adds a new product to the store catalog in JSON format.
     * Converts the Product object to a JSONObject and writes it into a file named Products[storeName].txt.
     * @param product The product to be added to the store catalog.
     * @param storeName The name of the store where the product will be added.
     */
    public static void addProductJSON (Product product, String storeName) {
        JSONObject productJSON = createProductJSON(product);
        writeProductJSON(productJSON, storeName);
    }

    /**
     * Removes the JSON object of a product with a given reference, from the file storing the products of a given store.
     * The file is updated by creating a temporal file, copying all the products except the one to remove,
     * and then replacing the original file.
     * @param ref The reference of the product to remove.
     * @param storeName The name of the store where the product is stored.
     */
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

    /**
     * Updates a product in a store's products file by removing the old version of the product
     * and writing the updated version to the file.
     * @param product The updated product to write into the file.
     * @param storeName The name of the store the product belongs to.
     */
    public static void updateProductJSON (Product product, String storeName) {
        Writer.removeProductJSON(product.getRef(), storeName);
        JSONObject productJSON = createProductJSON(product);
        Writer.writeProductJSON(productJSON, storeName);
    }

    /**
     * Writes a ticket object in JSON format to a file for a given store.
     * @param ticket The Ticket object to be written into the file.
     * @param storeName The name of the store associated with the ticket.
     */
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
