package service;

import domain.TicketType;
import domain.TicketTypeRequest;
import thirdparty.TicketPaymentService;
import thirdparty.SeatReservationService;

public class TicketServiceImpl implements thirdparty.TicketService {
    private final TicketPaymentService paymentService;
    private final SeatReservationService reservationService;

    public TicketServiceImpl(TicketPaymentService paymentService, SeatReservationService reservationService) {
        this.paymentService = paymentService;
        this.reservationService = reservationService;
    }

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) {
        if (accountId == null || accountId <= 0) {
            throw new IllegalArgumentException("Invalid account ID");
        }

        int totalTickets = 0;
        int adultTickets = 0;
        int childTickets = 0;
        int infantTickets = 0;
        int totalAmount = 0;

        for (TicketTypeRequest request : ticketTypeRequests) {
            int quantity = request.getNoOfTickets();
            totalTickets += quantity;

            switch (request.getTicketType()) {
                case ADULT -> {
                    adultTickets += quantity;
                    totalAmount += quantity * 25;
                }
                case CHILD -> {
                    childTickets += quantity;
                    totalAmount += quantity * 15;
                }
                case INFANT -> infantTickets += quantity;
            }
        }

        if (totalTickets > 25) {
            throw new IllegalArgumentException("Cannot purchase more than 25 tickets");
        }

        if (adultTickets == 0 && (childTickets > 0 || infantTickets > 0)) {
            throw new IllegalArgumentException("Child or infant tickets require at least one adult ticket");
        }

        paymentService.makePayment(accountId, totalAmount);
        reservationService.reserveSeat(accountId, adultTickets + childTickets);
    }
}
