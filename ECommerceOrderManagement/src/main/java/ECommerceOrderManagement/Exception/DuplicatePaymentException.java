package ECommerceOrderManagement.Exception;


public class DuplicatePaymentException extends RuntimeException{

    public DuplicatePaymentException(int id) {
        super("Payment ID "+id+ " already exists for this order");
    }


}
