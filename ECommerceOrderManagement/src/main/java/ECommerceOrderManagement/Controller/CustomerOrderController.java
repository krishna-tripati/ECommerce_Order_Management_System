package ECommerceOrderManagement.Controller;

import ECommerceOrderManagement.Dto.OrderRequestDto;
import ECommerceOrderManagement.Dto.ResponseOrderDto;
import ECommerceOrderManagement.Service.CustomerOrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class CustomerOrderController {

    public final CustomerOrderService customerOrderService;


    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    // order multiple item
    @PostMapping("/customerorder")
    public ResponseEntity<ResponseOrderDto> calculateOrder(@Valid @RequestBody OrderRequestDto order){
       ResponseOrderDto responseOrder= customerOrderService.createOrder(order);
       return ResponseEntity.ok(responseOrder);
    }


    //get order by id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrderDto> getOrderById(@PathVariable ("id") int id){
       ResponseOrderDto response= customerOrderService.getOrderById(id);
       return ResponseEntity.ok(response);
    }

    // get order by customer id
    @GetMapping("/cust/{id}")
    public ResponseEntity<List<ResponseOrderDto>> getOrderByCustId(@PathVariable ("id") int id){
         List<ResponseOrderDto> response= customerOrderService.getOrderbyCustId(id);
         return ResponseEntity.ok(response);
    }

    //get all orders
    @GetMapping("/list")
    public ResponseEntity<List<ResponseOrderDto>> getAllOrderList(){
        List<ResponseOrderDto> response= customerOrderService.getAllOrderList();
        return ResponseEntity.ok(response);
    }

     //order cancel by order id
    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable ("id") int id){
        String msg= customerOrderService.cancelOrder(id);
        return ResponseEntity.ok(msg);
    }


}
