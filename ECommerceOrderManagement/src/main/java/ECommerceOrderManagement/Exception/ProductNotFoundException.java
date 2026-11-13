package ECommerceOrderManagement.Exception;

public class ProductNotFoundException extends RuntimeException{


    public ProductNotFoundException(int id){
        super("product Id "+ id +" doesn't exit ");
    }

}
