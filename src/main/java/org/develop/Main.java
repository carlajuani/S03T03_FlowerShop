package org.develop;

public class Main {
    /**
     * The FlowerShop application. Displays a menu to the user with several options to manage a flower shop.
     * Each option corresponds to a method in the MethodsMenu class.
     */
    public static void main(String[] args) {
        int menu;
        do {
            menu = Input.scanningForInt("""
                    Choose which option do you want of the next menu:
                    1. Create a flower shop.
                    2. Add a new product to store.
                    3. Print all the store catalog.
                    4. Remove a product from the store.
                    5. Print all the stock quantity.
                    6. Print the total price of the stock products from the store.
                    7. Create a purchase ticket with products.
                    8. Print store sales history.
                    9. Print the total money earned from all sales.
                    0. Exit the application.
                    Choose a number between 0 to 9:""");
            switch (menu) {
                case 0 -> System.out.println("Thank you for your purchase!");
                case 1 -> MethodsMenu.createFlowerShop();
                case 2 -> MethodsMenu.newProductToStore();
                case 3 -> MethodsMenu.printCatalog();
                case 4 -> MethodsMenu.removeProductFromStore();
                case 5 -> MethodsMenu.printStockQuantity();
                case 6 -> MethodsMenu.printStockValue();
                case 7 -> MethodsMenu.purchaseStoreSale();
                case 8 -> MethodsMenu.printHistorySales();
                case 9 -> MethodsMenu.printTotalSalesValue();
                default -> System.out.println("Error. Introduce a number between 0 to 13.");
            }
        } while (menu != 0);
    }
}

