package ECommerceOrderManagement.Exception;

public class AlreadyConfirmdOrderStatus extends RuntimeException{

    public AlreadyConfirmdOrderStatus() {
        super("Order is already confirmed");


    }
}
