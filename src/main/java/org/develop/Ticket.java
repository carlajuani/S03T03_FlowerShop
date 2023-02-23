package org.develop;

import java.util.ArrayList;
import java.util.List;

public class Ticket implements ITicket{
    final int ID;
    List<TicketLine> ticketLines = new ArrayList<>();
    double totalPrice = 0;

    public Ticket (int ID) {
        this.ID = ID;
    }

    //Constructor pel mètode JSONArrayToHashMap
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

    @Override
    public void addTicketLine(Product product, int quantity) {
        TicketLine ticketLine = new TicketLine(product, quantity);
        ticketLines.add(ticketLine);
        calculateTotalPrice();
    }

    @Override
    public void calculateTotalPrice() {
        double totalPrice = 0;
        for (TicketLine line : ticketLines) {
            totalPrice += line.getProduct().getPrice()*line.getQuantity();
        }
        this.totalPrice = (Math.round(totalPrice*100))/100d;
    }

    @Override
    public String toString() {
        return "Ticket{ ID=" +ID +ticketLines+ "\ntotalPrice=" +totalPrice+ "€}\n";
    }
}
