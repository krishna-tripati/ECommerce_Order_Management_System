package ECommerceOrderManagement.Exception;


public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(int id){
        super("Order Id "+ id+" doesn't exits");
    }

}
