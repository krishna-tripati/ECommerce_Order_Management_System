package ECommerceOrderManagement.Repository;

import ECommerceOrderManagement.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> getByCategory(String category);
    List<Product> getByProductName(String productName);

}
