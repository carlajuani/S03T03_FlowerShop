package org.develop;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the ITicket interface.
 * Represents a ticket that contains as attributes a unique ID number, a list of ticket lines and a total price.
 */
public class Ticket implements ITicket{
    final int ID;
    List<TicketLine> ticketLines = new ArrayList<>();
    double totalPrice = 0;

    /**
     * Constructor used to create a new ticket when a purchase is made.
     */
    public Ticket (int ID) {
        this.ID = ID;
    }

    /**
     * Constructor overloading. Used to initialize a new ticket in method JSONTicketsToHashMap().
     */
    public Ticket(int ID, List<TicketLine> ticketLines, double totalPrice ) {
        this.ID = ID;
        this.ticketLines = ticketLines;
        this.totalPrice = totalPrice;
    }

    public int getID() {
        return ID;
    }

    public List<TicketLine> getTicketLines() {
        return ticketLines;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Adds a new product line to the ticket with the specified product and quantity and update's the ticket's total price.
     * @param product The product to add to the ticket line.
     * @param quantity The quantity of the product to add to the ticket line.
     */
    @Override
    public void addTicketLine(Product product, int quantity) {
        TicketLine ticketLine = new TicketLine(product, quantity);
        ticketLines.add(ticketLine);
        calculateTotalPrice();
    }

    /**
     * This method iterates through the ticket's list of ticket lines, calculates the total price of each line based
     * on the product's price and the quantity specified in the line, and then sums up the total price of all lines
     * to get the ticket's total price. The value is then rounded to two decimal places and stored in
     * the ticket's totalPrice attribute.
     */
    @Override
    public void calculateTotalPrice() {
        double totalPrice = 0;
        for (TicketLine line : ticketLines) {
            totalPrice += line.getProduct().getPrice()*line.getQuantity();
        }
        this.totalPrice = (Math.round(totalPrice*100))/100d;
    }

    /**
     * ToString used to print a ticket with all its attributes.
     */
    @Override
    public String toString() {
        return "Ticket{ ID=" +ID +ticketLines+ "\ntotalPrice=" +totalPrice+ "€}\n";
    }
}
