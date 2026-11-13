package ECommerceOrderManagement.Service;

import ECommerceOrderManagement.Dto.CustomerRequestDto;
import ECommerceOrderManagement.Dto.CustomerResponseDto;
import ECommerceOrderManagement.Dto.CustomerUpdateRequestDto;
import ECommerceOrderManagement.Entity.Customer;
import ECommerceOrderManagement.Exception.CustomerNotFoundException;
import ECommerceOrderManagement.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    //add customer
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto){
           Customer customer= mapToEntity(customerRequestDto);
        Customer customerAdded=  customerRepository.save(customer);
        return mapToDto(customerAdded);
    }


    // get all customer
    public List<CustomerResponseDto> getAllCustomer(){
        return customerRepository.findAll().stream().map(this::mapToDto).toList();
    }


    //get by cust id
    public CustomerResponseDto getByCustId(int id){
        Customer customer= customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException(id));
         return mapToDto(customer);
    }

     // get by name
    public List<CustomerResponseDto> getByName(String name){
      return  customerRepository.findByCustName(name).stream().map(this::mapToDto).toList();
    }

    //update customer details
      public CustomerResponseDto updateCustomer(int id, CustomerUpdateRequestDto customerRequestDto){
        Customer customer= customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException(id));
         customer.setAddress(customerRequestDto.getAddress());
         customer.setMobileNo(customerRequestDto.getMobileNo());
         customer.setEmail(customerRequestDto.getEmail());
          Customer updatedCustomer= customerRepository.save(customer);

         return mapToDto(updatedCustomer);
      }


    // delete customer
    public String deleteCustomer(int id){
        Customer c= customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException(id));
        customerRepository.delete(c);
        return "Customer Deleted Successfully ";
    }


    // Map to customer Entity
    public Customer mapToEntity(CustomerRequestDto customerRequestDto){
        Customer customer=new Customer();
        customer.setCustName(customerRequestDto.getCustName());
        customer.setAddress(customerRequestDto.getAddress());
        customer.setEmail(customerRequestDto.getEmail());
        customer.setMobileNo(customerRequestDto.getMobileNo());
        return customer;
    }


    //Map to customer Response Dto
    public CustomerResponseDto mapToDto(Customer customer){
        CustomerResponseDto response=new CustomerResponseDto();
        response.setCustId(customer.getCustId());
        response.setCustName(customer.getCustName());
        response.setAddress(customer.getAddress());
        response.setMobileNo(customer.getMobileNo());
        response.setEmail(customer.getEmail());
        return response;
    }

}
