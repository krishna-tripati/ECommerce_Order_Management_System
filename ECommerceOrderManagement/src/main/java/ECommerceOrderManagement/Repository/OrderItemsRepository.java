package ECommerceOrderManagement.Repository;

import ECommerceOrderManagement.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem,Integer> {

  List<OrderItem> findByCustomerOrder_OrderId(int id);


}
