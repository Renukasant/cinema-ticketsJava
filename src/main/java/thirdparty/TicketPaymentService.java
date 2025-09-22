package thirdparty;

public interface TicketPaymentService {
    void makePayment(Long accountId, int totalAmount);
}
