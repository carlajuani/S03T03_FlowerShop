package org.develop;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Scanner;

public class Reader {

    //STORE
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
        } catch (IOException e) {
            System.out.println("ERROR. Can't open file.");
        }
        return found;
    }

    //PRODUCT ID
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

    //PRODUCTS
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

    //TICKETS
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
