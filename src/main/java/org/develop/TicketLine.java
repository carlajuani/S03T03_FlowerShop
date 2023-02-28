package org.develop;

/**
 * Represents a single line in a ticket.
 * Contains as attributes the product and its quantity.
 */
public class TicketLine {
    Product product;
    int quantity;

    /**
     * Constructor used to create a new ticket line added to the ticket.
     */
    public TicketLine (Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * ToString used to print a ticket line with all its attributes.
     */
    @Override
    public String toString() {
        return "\n{" + "product= " + product.storeCatalogtoString() + ", quantity=" + quantity + "}";
    }
}
