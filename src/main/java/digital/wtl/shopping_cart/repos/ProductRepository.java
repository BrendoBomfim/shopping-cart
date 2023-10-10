package digital.wtl.shopping_cart.repos;

import digital.wtl.shopping_cart.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
