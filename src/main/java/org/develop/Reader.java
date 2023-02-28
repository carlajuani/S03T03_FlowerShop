package org.develop;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Scanner;

public class Reader {

    /**
     * This method checks if a store with the given name already exists in the Stores.txt file.
     * It trims and replaces whitespaces in the store name before checking.
     * @param storeName A String representing the name of the store to check for.
     * @return True if the store exists in the file, false otherwise.
     */
    public static boolean checkExistingStore (String storeName) {
        String storeNameTrimmed = storeName.trim().replace(" ","_");
        boolean found = false;
        try {
            File fileToCheck = new File("src/main/resources/Stores.txt");
            BufferedReader br = new BufferedReader(new FileReader(fileToCheck));
            String line = br.readLine();
            if (fileToCheck.exists()) {
                while ((line != null) && !found) {
                    if(line.contains(storeNameTrimmed)) {
                        found = true;
                    }
                    line = br.readLine();
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("ERROR. Can't open file.");
        }
        return found;
    }

    /**
     * Reads the last used ID from a given file and returns the next ID to use.
     * @param fileName The name of the file to read from.
     * @return The next ID to use.
     */
    public static int readLastID (String fileName) {
        int lastID = 1;
        Scanner scanner;
        try {
            File file = new File("src/main/resources/" +fileName);
            scanner = new Scanner(file);
            String line = "";
            if (scanner.hasNext()) {
                while (scanner.hasNext()) {
                    line = scanner.next();
                }
                JSONObject ticketLineJSON = new JSONObject(line);
                lastID += ticketLineJSON.getInt("ID");
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("ERROR. Can't open file.");
        }
        return lastID;
    }

    /**
     * Reads a file containing products for a specific store and returns it as a JSONArray.
     * @param storeName The name of the store to read products from.
     * @return A JSONArray containing the products in the store's stock.
     */
    public static JSONArray readProductsJSON (String storeName) {
        String storeNameTrimmed = storeName.trim().replace(" ","_");
        JSONArray storeStockJSON = new JSONArray();
        String line;
        try {
            File file = new File("src/main/resources/Products"+storeNameTrimmed+".txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            while (line != null) {
                JSONObject productJSON = new JSONObject(line);
                storeStockJSON.put(productJSON);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("ERROR. Can't open file.");
        }
        return storeStockJSON;
    }

    /**
     * Reads and parses the JSON data of all the sales tickets stored in a file for a specific store,
     * and returns them as a JSONArray.
     * @param storeName The name of the store for which to retrieve the sales tickets.
     * @return A JSONArray containing the JSON data of all the sales tickets for the specified store.
     */
    public static JSONArray readTicketsJSON (String storeName) {
        String storeNameTrimmed = storeName.trim().replace(" ","_");
        JSONArray salesHistoryJSON = new JSONArray();
        String line;
        try {
            File file = new File("src/main/resources/Tickets" + storeNameTrimmed + ".txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            while (line != null) {
                JSONObject ticketJSON = new JSONObject(line);
                salesHistoryJSON.put(ticketJSON);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("ERROR. Can't open file.");
        }
        return salesHistoryJSON;
    }
}
