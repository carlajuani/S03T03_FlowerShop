package org.develop;

import org.json.JSONArray;
import java.util.HashMap;

public class MethodsMenu {

    /**
     * Case 1:
     * Creates a new flower shop by prompting the user to enter a store name and checking if it already exists.
     * If the store name already exists, prompts the user to choose another one. If not, creates the files
     * using the store name and writes the new store with the given name into "Stores.txt" file.
     */
    public static void createFlowerShop () {
        String storeName = Input.scanningForString("Enter the store name:");
        while(Reader.checkExistingStore(storeName)){
            storeName = Input.scanningForString("Store name already exists. Choose another store name:");
        }
        Writer.writeStoreJSON(new Store(storeName));
        System.out.println("Flower Store " +storeName+ " successfully created.");
    }

    /**
     * Case 2:
     * Allows the user to add a new product to an existing store.
     * It prompts the user to input the name of the store and validates his existence.
     * Then creates a new product with the information provided by the user.
     * Finally, it calls the method to add the new product to the store's stock list in JSON format.
     */
    public static void newProductToStore () {
        String storeName = Input.scanningForString("Please indicate the name of the store:");
        while (!Reader.checkExistingStore(storeName)) {
            storeName = Input.scanningForString("Store does not exist (remember to accurately write the name with its capital letters). Choose another store name:");
        }
        Product newProduct = Tools.createProduct(storeName);
        Writer.addProductJSON(newProduct, storeName);
    }

    /**
     * Case 3:
     * Prints the catalog of products for a given store name where the catalog is organized by product type (tree, flower, and decoration).
     * First prompts the user to input the store name and then checks whether exists or not.
     * If the store does not exist, it will keep prompting the user to input the store name until a valid one is inputted.
     */
    public static void printCatalog () {
        String storeName = Input.scanningForString("Please indicate store's name:");
        while (!Reader.checkExistingStore(storeName)) {
            storeName = Input.scanningForString("Store does not exist (remember to accurately write the name with its capital letters). Choose another store name:");
        }
        JSONArray storeStockJSON = Reader.readProductsJSON(storeName);
        HashMap<String, Product> storeStock = Tools.JSONProductsToHashMap(storeStockJSON);
        storeStock.values().stream().filter(v -> v.getProductType() == Product.ProductType.TREE).forEach(p -> System.out.println(p.storeCatalogtoString()));
        storeStock.values().stream().filter(v -> v.getProductType() == Product.ProductType.FLOWER).forEach(p -> System.out.println(p.storeCatalogtoString()));
        storeStock.values().stream().filter(v -> v.getProductType() == Product.ProductType.DECORATION).forEach(p -> System.out.println(p.storeCatalogtoString()));
    }

    /**
     * Case 4:
     * Removes a product from a given store's stock, based on the product's reference number.
     * First prompts the user to input the name of the store and validates the existence.
     * Then prompts the user to input the reference number of the product to be removed.
     * Finally, calls the Writer class to remove the product from the store's stock file.
     */
    public static void removeProductFromStore () {
        String storeName = Input.scanningForString("Please indicate the products store's name:");
        while (!Reader.checkExistingStore(storeName)) {
            storeName = Input.scanningForString("Store does not exist (remember to accurately write the name with its capital letters). Choose another store name:");
        }
        String ref = Input.scanningForString("Please indicate product's reference:");
        Writer.removeProductJSON(ref, storeName);
    }

    /**
     * Case 5:
     * Prints the stock quantity of all products in a given store's stock.
     * First checks the existence of the store and then reads the store's stock file and converts the JSON data into a HashMap.
     * Finally iterates over the HashMap and prints the stock quantity of each product.
     */
    public static void printStockQuantity () {
        String storeName = Input.scanningForString("Please indicate the products store's name:");
        while (!Reader.checkExistingStore(storeName)) {
            storeName = Input.scanningForString("Store does not exist (remember to accurately write the name with its capital letters). Choose another store name:");
        }
        JSONArray storeStockJSON = Reader.readProductsJSON(storeName);
        HashMap<String, Product> storeStock = Tools.JSONProductsToHashMap(storeStockJSON);
        storeStock.values().forEach(v -> System.out.println(v.stockQuantitytoString()));
    }

    /**
     * Case 6:
     * Prints the total value of the stock of all products in a given store's.
     * Prompts the user to input the store name and checks if exists or not. Then reads the store's stock
     * file and converts the JSON data into a HashMap. Calls the showStockValue() method from the Tools class
     * to calculate and print the total value.
     */
    public static void printStockValue () {
        String storeName = Input.scanningForString("Please indicate the products store's name:");
        while (!Reader.checkExistingStore(storeName)) {
            storeName = Input.scanningForString("Store does not exist (remember to accurately write the name with its capital letters). Choose another store name:");
        }
        JSONArray storeStockJSON = Reader.readProductsJSON(storeName);
        HashMap<String, Product> storeStock = Tools.JSONProductsToHashMap(storeStockJSON);
        Tools.showStockValue(storeStock);
    }

    /**
     * Case 7:
     * Allows the user to initiate a purchase from a store's stock.
     * Asks the user for the store's name, retrieves the stock from the corresponding file and creates a new store object.
     * The sale is processed by invoking the purchaseSale() method with the store object.
     */
    public static void purchaseStoreSale () {
        String storeName = Input.scanningForString("Please indicate the products store's name:");
        while (!Reader.checkExistingStore(storeName)) {
            storeName = Input.scanningForString("Store does not exist (remember to accurately write the name with its capital letters). Choose another store name:");
        }
        Store store = new Store(storeName);
        JSONArray storeStockJSON = Reader.readProductsJSON(storeName);
        HashMap<String,Product> storeStock = Tools.JSONProductsToHashMap(storeStockJSON);
        store.setStoreStock(storeStock);
        store.purchaseSale();
    }

    /**
     * Case 8:
     * Allows the user to view the sales history of a store.
     * Asks for the store's name, retrieves the history of sales from the corresponding file,
     * creates a HashMap and prints the sales data.
     */
    public static void printHistorySales () {
        String storeName = Input.scanningForString("Please indicate store's name:");
        while (!Reader.checkExistingStore(storeName)) {
            storeName = Input.scanningForString("Store does not exist (remember to accurately write the name with its capital letters). Choose another store name:");
        }
        JSONArray historySalesJSON = Reader.readTicketsJSON(storeName);
        HashMap<Integer,ITicket> historySales = Tools.JSONTicketsToHashMap(historySalesJSON);
        historySales.values().forEach(System.out::println);
    }

    /**
     * Case 9:
     * Displays the total sales value for a given store.
     * Reads the sales history for the tickets file and then calculates the total sales value
     * by summing the price of all items sold in each transaction.
     */
    public static void printTotalSalesValue () {
        String storeName = Input.scanningForString("Please indicate store's name:");
        while (!Reader.checkExistingStore(storeName)) {
            storeName = Input.scanningForString("Store does not exist (remember to accurately write the name with its capital letters). Choose another store name:");
        }
        JSONArray historySalesJSON = Reader.readTicketsJSON(storeName);
        HashMap<Integer, ITicket> historySales = Tools.JSONTicketsToHashMap(historySalesJSON);
        Tools.showSalesValue(historySales);
    }
}






