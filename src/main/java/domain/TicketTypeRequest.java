package domain;

public final class TicketTypeRequest {
    private final TicketType ticketType;
    private final int noOfTickets;

    public TicketTypeRequest(TicketType ticketType, int noOfTickets) {
        if (ticketType == null || noOfTickets <= 0) {
            throw new IllegalArgumentException("Invalid ticket request");
        }
        this.ticketType = ticketType;
        this.noOfTickets = noOfTickets;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }
}
