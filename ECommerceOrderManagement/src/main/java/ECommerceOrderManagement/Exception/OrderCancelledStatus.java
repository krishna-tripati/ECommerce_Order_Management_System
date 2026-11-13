package ECommerceOrderManagement.Exception;

public class OrderCancelledStatus extends RuntimeException{

    public OrderCancelledStatus() {
        super("Cannot pay for a cancelled order");

    }
}
