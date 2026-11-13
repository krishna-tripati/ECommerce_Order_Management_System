package ECommerceOrderManagement.Service;

import ECommerceOrderManagement.Dto.PaymentRequestDto;
import ECommerceOrderManagement.Dto.PaymentResponseDto;
import ECommerceOrderManagement.Entity.CustomerOrder;
import ECommerceOrderManagement.Entity.Payment;
import ECommerceOrderManagement.Exception.*;
import ECommerceOrderManagement.Repository.CustomerOrderRepository;
import ECommerceOrderManagement.Repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final CustomerOrderRepository customerOrderRepository;

    public PaymentService(PaymentRepository paymentRepository, CustomerOrderRepository customerOrderRepository) {
        this.paymentRepository = paymentRepository;
        this.customerOrderRepository = customerOrderRepository;

    }

    @Transactional
    public PaymentResponseDto payments(PaymentRequestDto paymentRequest){
         //check Order Id exits
        CustomerOrder customerOrder= customerOrderRepository.findById(paymentRequest.getOrderId()).orElseThrow(()-> new OrderNotFoundException(paymentRequest.getOrderId()));

        // check cancelled order
        if("CANCELLED".equals(customerOrder.getOrderStatus())){
            throw new OrderCancelledStatus();
        }

        //check Confirmed order
        if("CONFIRMED".equals(customerOrder.getOrderStatus())){
            throw new AlreadyConfirmdOrderStatus();
        }

        //if once done payment same id didn't work payment method
        if(paymentRepository.existsByCustomerOrder_OrderId(customerOrder.getOrderId())){
            throw new DuplicatePaymentException(customerOrder.getOrderId());
        }

        Payment payment= new Payment();
        payment.setPayMethod(paymentRequest.getPayMethod());
        payment.setPayAmount(customerOrder.getTotalAmount());
        payment.setCustomerOrder(customerOrder);
        payment.setPayStatus("SUCCESS");

         paymentRepository.save(payment);

         customerOrder.setOrderStatus("CONFIRMED");
          customerOrderRepository.save(customerOrder);
        return mapToPaymentRespone(payment);
    }

    //
    public PaymentResponseDto mapToPaymentRespone(Payment payment){
        PaymentResponseDto response= new PaymentResponseDto();
        response.setPayId(payment.getPayId());
        response.setOrderId(payment.getCustomerOrder().getOrderId());
        response.setPayMethod(payment.getPayMethod());
        response.setPayAmount(payment.getPayAmount());
        response.setPayStatus(payment.getPayStatus());
        return response;
    }

    // get payment by id
    @Transactional(readOnly = true)
    public PaymentResponseDto getPaymentbyId(int id){
       Payment p= paymentRepository.findById(id).orElseThrow(()-> new PaymentNotFoundException(id));
       return mapToPaymentRespone(p);
    }

    // get all payment list
    @Transactional(readOnly = true)
    public List<PaymentResponseDto> getAllPayments(){
       List<Payment> payments=paymentRepository.findAll();

       return  payments.stream().map(this::mapToPaymentRespone).toList();
    }
}
