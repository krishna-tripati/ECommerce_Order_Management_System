package ECommerceOrderManagement.Repository;

import ECommerceOrderManagement.Entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder , Integer> {

List<CustomerOrder> findByCustomer_CustId(int custId);

}
