package org.develop;

public interface ITicket {
    void addTicketLine(Product product, int quantity);
    void calculateTotalPrice();
}
