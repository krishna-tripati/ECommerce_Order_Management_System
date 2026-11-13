package ECommerceOrderManagement.Exception;

public class alreadyCancelled extends RuntimeException{

    public alreadyCancelled(){
        super("this Order already Cancelled");
    }

}
