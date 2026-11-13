package ECommerceOrderManagement.Repository;

import ECommerceOrderManagement.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {


    boolean existsByCustomerOrder_OrderId(int orderId);
}
