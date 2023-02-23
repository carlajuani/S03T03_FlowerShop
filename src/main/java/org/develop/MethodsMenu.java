package org.develop;

import org.json.JSONArray;
import java.util.HashMap;

public class MethodsMenu {

    //case1
    public static void createFlowerShop() {
        String storeName = Input.scanningForString("Enter the store name:");
        while(Reader.checkExistingStore(storeName)){
            storeName = Input.scanningForString("Store name already exists. Choose another store name:");
        }
        Writer.writeStoreJSON(new Store(storeName));
        System.out.println("Flower Store " +storeName+ " successfully created.");
    }

    //case2
    public static void  newProductToStore () {
        String storeName = Input.scanningForString("Please indicate the name of the store:");
        while (!Reader.checkExistingStore(storeName)) {
            storeName = Input.scanningForString("Store does not exist (remember to accurately write the name with its capital letters). Choose another store name:");
        }
        Product newProduct = Tools.createProduct(storeName);
        Writer.addProductJSON(newProduct, storeName);
    }

    //case3
    public static void  printCatalog () {
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

    //case4
    public static void removeProductFromStore () {
        String storeName = Input.scanningForString("Please indicate the products store's name:");
        while (!Reader.checkExistingStore(storeName)) {
            storeName = Input.scanningForString("Store does not exist (remember to accurately write the name with its capital letters). Choose another store name:");
        }
        String ref = Input.scanningForString("Please indicate product's reference:");
        Writer.removeProductJSON(ref, storeName);
    }

    //case5
    public static void  printStockQuantity () {
        String storeName = Input.scanningForString("Please indicate the products store's name:");
        while (!Reader.checkExistingStore(storeName)) {
            storeName = Input.scanningForString("Store does not exist (remember to accurately write the name with its capital letters). Choose another store name:");
        }
        JSONArray storeStockJSON = Reader.readProductsJSON(storeName);
        HashMap<String, Product> storeStock = Tools.JSONProductsToHashMap(storeStockJSON);
        storeStock.values().forEach(v -> System.out.println(v.stockQuantitytoString()));
    }

    //case6
    public static void  printStockValue () {
        String storeName = Input.scanningForString("Please indicate the products store's name:");
        while (!Reader.checkExistingStore(storeName)) {
            storeName = Input.scanningForString("Store does not exist (remember to accurately write the name with its capital letters). Choose another store name:");
        }
        JSONArray storeStockJSON = Reader.readProductsJSON(storeName);
        HashMap<String, Product> storeStock = Tools.JSONProductsToHashMap(storeStockJSON);
        Tools.showStockValue(storeStock);
    }

    //case7
    public static void  purchaseStoreSale () {
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

    //case8
    public static void printHistorySales () {
        String storeName = Input.scanningForString("Please indicate store's name:");
        while (!Reader.checkExistingStore(storeName)) {
            storeName = Input.scanningForString("Store does not exist (remember to accurately write the name with its capital letters). Choose another store name:");
        }
        JSONArray historySalesJSON = Reader.readTicketsJSON(storeName);
        HashMap<Integer,ITicket> historySales = Tools.JSONTicketsToHashMap(historySalesJSON);
        historySales.values().forEach(System.out::println);
    }

    //case9
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






