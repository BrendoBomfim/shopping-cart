package digital.wtl.shopping_cart.repos;

import digital.wtl.shopping_cart.domain.Order;
import digital.wtl.shopping_cart.domain.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findFirstByProducts(Product product);

    List<Order> findAllByProducts(Product product);

}
