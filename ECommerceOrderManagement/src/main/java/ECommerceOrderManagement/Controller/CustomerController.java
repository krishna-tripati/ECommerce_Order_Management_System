package ECommerceOrderManagement.Controller;

import ECommerceOrderManagement.Dto.CustomerRequestDto;
import ECommerceOrderManagement.Dto.CustomerResponseDto;
import ECommerceOrderManagement.Dto.CustomerUpdateRequestDto;
import ECommerceOrderManagement.Entity.Customer;
import ECommerceOrderManagement.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
          //add customer
     @PostMapping
    public ResponseEntity<CustomerResponseDto> addCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto responseDto=customerService.addCustomer(customerRequestDto);
        return ResponseEntity.ok(responseDto);
    }

     //get all customer
    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomer(){
        List<CustomerResponseDto> response=customerService.getAllCustomer();
        return ResponseEntity.ok(response);
    }

    //get by id
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getByCustId(@PathVariable ("id") int id){
        CustomerResponseDto response=customerService.getByCustId(id);
        return ResponseEntity.ok(response);
    }

    // by customer name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<CustomerResponseDto>> getByName(@PathVariable ("name") String name){
        List<CustomerResponseDto> responseDtos= customerService.getByName(name);
        return ResponseEntity.ok(responseDtos);

    }

    //update customer
    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer( @PathVariable ("id") int id ,
                                                               @Valid @RequestBody CustomerUpdateRequestDto customerRequestDto){
        CustomerResponseDto responseDto= customerService.updateCustomer(id, customerRequestDto);
        return ResponseEntity.ok(responseDto);
    }

      //delete by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable ("id") int id){
       String msg=customerService.deleteCustomer(id);

         return ResponseEntity.ok(msg);
    }

}
