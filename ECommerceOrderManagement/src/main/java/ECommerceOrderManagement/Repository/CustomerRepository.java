package ECommerceOrderManagement.Repository;

import ECommerceOrderManagement.Dto.CustomerResponseDto;
import ECommerceOrderManagement.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByCustName(String name);


}
