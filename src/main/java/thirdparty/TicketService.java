package thirdparty;

import domain.TicketTypeRequest;

public interface TicketService {
    void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests);
}
