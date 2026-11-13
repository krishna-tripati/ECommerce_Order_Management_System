package ECommerceOrderManagement.Service;

import ECommerceOrderManagement.Dto.OrderItemRequestDto;
import ECommerceOrderManagement.Dto.OrderRequestDto;
import ECommerceOrderManagement.Dto.ResponseOrderDto;
import ECommerceOrderManagement.Dto.ResponseProductDto;
import ECommerceOrderManagement.Entity.Customer;
import ECommerceOrderManagement.Entity.CustomerOrder;
import ECommerceOrderManagement.Entity.OrderItem;
import ECommerceOrderManagement.Entity.Product;
import ECommerceOrderManagement.Exception.*;
import ECommerceOrderManagement.Repository.CustomerOrderRepository;
import ECommerceOrderManagement.Repository.CustomerRepository;
import ECommerceOrderManagement.Repository.OrderItemsRepository;
import ECommerceOrderManagement.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerOrderService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CustomerOrderRepository customerOrderRepository;
    private final OrderItemsRepository orderItemsRepository;

    public CustomerOrderService(CustomerRepository customerRepository, ProductRepository productRepository, CustomerOrderRepository customerOrderRepository, OrderItemsRepository orderItemsRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.customerOrderRepository = customerOrderRepository;
        this.orderItemsRepository = orderItemsRepository;
    }

          // create order with multiple products
    @Transactional
    public ResponseOrderDto createOrder(OrderRequestDto orderRequestDto)
    {
        Customer customer =customerRepository.findById(orderRequestDto.getCustId()).orElseThrow(()->new CustomerNotFoundException(orderRequestDto.getCustId()));
        double totalAmount=0;

       for(OrderItemRequestDto item: orderRequestDto.getOrderItems())
       {
        int id=  item.getProductId() ;
         int quantity=  item.getQuantity();
         Product p= productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));

         if(p.getQuantity() < quantity){
             throw new InsufficientStock();
          }

          double subtotal= p.getPrice()*quantity;
          totalAmount +=subtotal;

      }

        //saving data in customer order
        CustomerOrder customerOrder=new CustomerOrder();
        customerOrder.setCustomer(customer);
        customerOrder.setTotalAmount(totalAmount);
        customerOrder.setOrderStatus("PENDING");
        customerOrderRepository.save(customerOrder);

        List<ResponseProductDto> responseProducts=new ArrayList<>();
           //returning product data and saving orderItems
       for(OrderItemRequestDto items: orderRequestDto.getOrderItems() ){
           Product product= productRepository.findById(items.getProductId()).orElseThrow(()->new ProductNotFoundException(items.getProductId()));

           OrderItem orderItem=new OrderItem();
           orderItem.setCustomerOrder(customerOrder);
           orderItem.setProduct(product);
           orderItem.setQuantity(items.getQuantity());
           orderItem.setPrice(product.getPrice());
           orderItemsRepository.save(orderItem);

           ResponseProductDto response=mapToResponseProduct(orderItem);
           responseProducts.add(response);

           product.setQuantity(product.getQuantity()- items.getQuantity());
           productRepository.save(product);
       }

       return mapToResponseOrder(customerOrder,responseProducts);
    }

   public ResponseOrderDto mapToResponseOrder(CustomerOrder customerOrder, List<ResponseProductDto> products){
        ResponseOrderDto responseOrder=new ResponseOrderDto();

        responseOrder.setOrderId(customerOrder.getOrderId());
        responseOrder.setCustId(customerOrder.getCustomer().getCustId());
        responseOrder.setCustName(customerOrder.getCustomer().getCustName());
        responseOrder.setTotalAmount(customerOrder.getTotalAmount());
        responseOrder.setOrderStatus(customerOrder.getOrderStatus());
        responseOrder.setProducts(products);

        return responseOrder;
    }

    public ResponseProductDto mapToResponseProduct(OrderItem item)
    {
        ResponseProductDto responseProductDto=new ResponseProductDto();
        responseProductDto.setProductName(item.getProduct().getProductName());
        responseProductDto.setProductPrice(item.getProduct().getPrice());
        responseProductDto.setProductQuantity(item.getQuantity());
        return  responseProductDto;
    }


    // get order by id
    @Transactional
    public ResponseOrderDto getOrderById(int id)
    {

        CustomerOrder customerOrder= customerOrderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException(id));

        List<ResponseProductDto> responseProducts=customerOrder.getOrderItems().stream().map(this::mapToResponseProduct).toList();

       return mapToResponseOrder(customerOrder,responseProducts);

    }

    //get order by customer id
    @Transactional(readOnly = true)
    public List<ResponseOrderDto> getOrderbyCustId(int id){

        if(!customerRepository.existsById(id)){
            throw new CustomerNotFoundException(id);
        }
         List<CustomerOrder> orders= customerOrderRepository.findByCustomer_CustId(id);

        return orders.stream().map(order->{
            List<ResponseProductDto> products=order.getOrderItems().stream()
                    .map(this::mapToResponseProduct).toList();

                return mapToResponseOrder(order,products);

        }).toList();

     }



    //get all order
    @Transactional(readOnly = true)
    public List<ResponseOrderDto> getAllOrderList(){
       List<CustomerOrder> orders= customerOrderRepository.findAll();

       return orders.stream().map(order->{
         List<ResponseProductDto> products=order.getOrderItems().stream()
                 .map(this::mapToResponseProduct).toList();

          return mapToResponseOrder(order,products);

       }).toList();
    }


    //cancel order
    @Transactional
    public String cancelOrder(int id){
        //find the order by id
      CustomerOrder customer= customerOrderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException(id));

      //check order status
        if("CANCELLED".equals(customer.getOrderStatus())){
          throw new alreadyCancelled();
        }

      //get order items and restore product quantity
      for(OrderItem items: customer.getOrderItems()){
            //getting product id  by OrderItems
         Product p= productRepository.findById(items.getProduct().getProductId()).orElseThrow(()-> new ProductNotFoundException(items.getProduct().getProductId()));
           p.setQuantity(p.getQuantity()+ items.getQuantity());

         productRepository.save(p);
      }

      //update order status
        customer.setOrderStatus("CANCELLED");
        customerOrderRepository.save(customer);
        return "Order Canceled";
    }


}
