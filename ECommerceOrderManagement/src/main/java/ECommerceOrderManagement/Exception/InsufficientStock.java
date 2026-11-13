package ECommerceOrderManagement.Exception;

public class InsufficientStock extends RuntimeException{

     public InsufficientStock(){
         super("Insufficient Stock");
     }

}
