package ECommerceOrderManagement.Controller;

import ECommerceOrderManagement.Dto.PaymentRequestDto;
import ECommerceOrderManagement.Dto.PaymentResponseDto;
import ECommerceOrderManagement.Service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment")
    public ResponseEntity<PaymentResponseDto> payment(@Valid @RequestBody PaymentRequestDto paymentRequest){
        PaymentResponseDto response=paymentService.payments(paymentRequest);
        return ResponseEntity.ok(response);
    }

    //get payment by id
    @GetMapping("/payment/{id}")
    public ResponseEntity<PaymentResponseDto> getPaymentById(@PathVariable ("id") int id){
        PaymentResponseDto paymentResponse= paymentService.getPaymentbyId(id);
        return ResponseEntity.ok(paymentResponse);
    }

    //get payment list
    @GetMapping("/payment/list")
    public ResponseEntity<List<PaymentResponseDto>> getAllPaymentList(){

        List<PaymentResponseDto> paymentResponse= paymentService.getAllPayments();
        return ResponseEntity.ok(paymentResponse);
    }
}
