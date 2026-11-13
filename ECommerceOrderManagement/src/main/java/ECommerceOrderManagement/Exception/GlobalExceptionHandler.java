package ECommerceOrderManagement.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException ex){

     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex){

        String msg= ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
    }


    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFound(CustomerNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InsufficientStock.class)
    public ResponseEntity<String> InsufficientStock(InsufficientStock ex){
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderIdNotFound(OrderNotFoundException msg){
        return ResponseEntity.ok(msg.getMessage());
    }

    @ExceptionHandler(alreadyCancelled.class)
    public ResponseEntity<String> handleCancelOrder(alreadyCancelled msg){
        return ResponseEntity.ok(msg.getMessage());
    }

    @ExceptionHandler(DuplicatePaymentException.class)
    public ResponseEntity<String> handleDuplicatePayment(DuplicatePaymentException msg){
        return ResponseEntity.ok(msg.getMessage());
    }

    @ExceptionHandler(OrderCancelledStatus.class)
    public ResponseEntity<String> handleOrderCancelStatus(OrderCancelledStatus msg){
        return ResponseEntity.ok(msg.getMessage());
    }

    @ExceptionHandler(AlreadyConfirmdOrderStatus.class)
    public ResponseEntity<String> handleConfirmedOrderStatus(AlreadyConfirmdOrderStatus msg){
        return ResponseEntity.ok(msg.getMessage());
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<String> handlePaymentIdNotFound(PaymentNotFoundException msg){
        return ResponseEntity.ok(msg.getMessage());
    }
}
