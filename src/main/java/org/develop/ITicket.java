package org.develop;

/**
 * Interface that represents a ticket used to purchase products.
 * Defines two methods that must be implemented by the class that implements the interface.
 */
public interface ITicket {
    void addTicketLine(Product product, int quantity);
    void calculateTotalPrice();
}
