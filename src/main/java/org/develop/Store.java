package org.develop;

import java.util.HashMap;

/**
 * Represents a store that sells products.
 * As an attributes has the store name and the stock of products available for sale.
 */
public class Store {
    
    private final String storeName;
    private HashMap<String,Product> storeStock = new HashMap<>();

    /**
     * Constructor used to create a new store with the specified name added to the file.
     */
    public Store (String storeName) {
        this.storeName = storeName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreStock(HashMap<String,Product> storeStock) {
        this.storeStock = storeStock;
    }

    /**
     * Allows a customer to purchase products from the store.
     * Creates a new ticket and updates the store stock and sales records accordingly.
     * The customer is prompted to input a product reference. If the store has it in stock,
     * is prompted to indicate a quantity. Depending on the quantity of the product in stock,
     * one of three options can occur:
     * 1. If there is enough product in stock, the quantity sold is subtracted from the product's quantity.
     * 2. If the store has just the quantity requested by the customer, the product is removed from the store stock.
     * 3. If there is not enough product in stock, the customer is prompted to see if they would like to purchase the remaining stock.
     * If the customer agrees, the remaining stock is sold and the product is removed from the store stock. If not,
     * the sale of the product is canceled.
     * After a product and its quantity are added to the sale ticket, the customer is prompted to indicate whether they would like
     * to add another product to the sale. If not, the sale ticket is printed and saved into a file, and the sale is marked as completed.
     */
    public void purchaseSale() {
        String trimmedStoreName = storeName.trim().replace(" ","_");
        int ID = Reader.readLastID("Tickets"+trimmedStoreName+".txt");
        ITicket saleTicket = new Ticket(ID);
        boolean saleCompleted = false;
        while (!saleCompleted) {
            String ref = Input.scanningForString("Please indicate product reference:");
            if (storeStock.containsKey(ref)) {
                Product product = storeStock.get(ref);
                int quantity = Input.scanningForInt("Please indicate quantity:");
                if (product.getQuantity() > quantity) {
                    product.sellQuantity(quantity);
                    saleTicket.addTicketLine(product, quantity);
                    Writer.updateProductJSON(product, this.storeName);
                } else if (product.getQuantity() == quantity){
                    storeStock.remove(ref);
                    System.out.println("Lucky you! Last ones on stock!");
                    saleTicket.addTicketLine(product, quantity);
                    Writer.removeProductJSON(ref, this.storeName);
                } else if (product.getQuantity() < quantity) {
                    String limitedSale = Input.scanningForString("Sorry, currently we have only " +product.getQuantity()+ " on stock. Would you like to acquire the remaining stock?");
                    if (limitedSale.equalsIgnoreCase("yes")) {
                        quantity = product.getQuantity();
                        storeStock.remove(ref);
                        System.out.println("Excellent choice!");
                        saleTicket.addTicketLine(product, quantity);
                        Writer.removeProductJSON(ref, this.storeName);
                    }
                }
            } else {
                System.out.println("Sorry, this product is not currently available.");
            }
            String nextSale = Input.scanningForString("Would you like to add anything else to your sale?");
            if (nextSale.equalsIgnoreCase("no")) {
                System.out.print(saleTicket);
                Writer.writeTicketJSON((Ticket)saleTicket, this.storeName);
                saleCompleted = true;
            }
        }
    }
}
