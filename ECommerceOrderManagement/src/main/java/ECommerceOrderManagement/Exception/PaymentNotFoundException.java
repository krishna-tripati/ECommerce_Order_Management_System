package ECommerceOrderManagement.Exception;

public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException(int id){
        super("Payment ID "+ id +" doesn't exits");
    }
}
