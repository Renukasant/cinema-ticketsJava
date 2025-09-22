package service;

import domain.TicketType;
import domain.TicketTypeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import thirdparty.SeatReservationService;
import thirdparty.TicketPaymentService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketServiceImplTest {

    private TicketPaymentService paymentService;
    private SeatReservationService reservationService;
    private TicketServiceImpl ticketService;

    @BeforeEach
    public void setup() {
        paymentService = mock(TicketPaymentService.class);
        reservationService = mock(SeatReservationService.class);
        ticketService = new TicketServiceImpl(paymentService, reservationService);
    }

    @Test
    public void testValidPurchase() {
        TicketTypeRequest adult = new TicketTypeRequest(TicketType.ADULT, 2);
        TicketTypeRequest child = new TicketTypeRequest(TicketType.CHILD, 1);

        ticketService.purchaseTickets(1L, adult, child);

        verify(paymentService).makePayment(1L, 65); // 2*25 + 1*15
        verify(reservationService).reserveSeat(1L, 3); // 2 adults + 1 child
    }

    @Test
    public void testMoreThan25TicketsThrowsException() {
        TicketTypeRequest adult = new TicketTypeRequest(TicketType.ADULT, 26);

        assertThrows(IllegalArgumentException.class, () ->
                ticketService.purchaseTickets(1L, adult)
        );
    }

    @Test
    public void testNoAdultTicketThrowsException() {
        TicketTypeRequest child = new TicketTypeRequest(TicketType.CHILD, 1);

        assertThrows(IllegalArgumentException.class, () ->
                ticketService.purchaseTickets(1L, child)
        );
    }

    @Test
    public void testInvalidAccountIdThrowsException() {
        TicketTypeRequest adult = new TicketTypeRequest(TicketType.ADULT, 1);

        assertThrows(IllegalArgumentException.class, () ->
                ticketService.purchaseTickets(0L, adult)
        );
    }

    @Test
    public void testInfantWithAdultDoesNotReserveSeat() {
        TicketTypeRequest adult = new TicketTypeRequest(TicketType.ADULT, 1);
        TicketTypeRequest infant = new TicketTypeRequest(TicketType.INFANT, 1);

        ticketService.purchaseTickets(1L, adult, infant);

        verify(paymentService).makePayment(1L, 25);
        verify(reservationService).reserveSeat(1L, 1); // Only 1 adult seat
    }
}
