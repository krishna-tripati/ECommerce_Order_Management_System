package ECommerceOrderManagement.Exception;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException (int id){
        super("Customer Id "+ id +" doesn't exits ");
    }
}
