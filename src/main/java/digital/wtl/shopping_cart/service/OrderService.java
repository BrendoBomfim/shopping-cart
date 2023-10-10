package digital.wtl.shopping_cart.service;

import digital.wtl.shopping_cart.domain.Order;
import digital.wtl.shopping_cart.domain.Product;
import digital.wtl.shopping_cart.model.OrderDTO;
import digital.wtl.shopping_cart.repos.OrderRepository;
import digital.wtl.shopping_cart.repos.ProductRepository;
import digital.wtl.shopping_cart.util.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(final OrderRepository orderRepository,
            final ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<OrderDTO> findAll() {
        final List<Order> orders = orderRepository.findAll(Sort.by("id"));
        return orders.stream()
                .map(order -> mapToDTO(order, new OrderDTO()))
                .toList();
    }

    public OrderDTO get(final Long id) {
        return orderRepository.findById(id)
                .map(order -> mapToDTO(order, new OrderDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final OrderDTO orderDTO) {
        final Order order = new Order();
        mapToEntity(orderDTO, order);
        return orderRepository.save(order).getId();
    }

    public void update(final Long id, final OrderDTO orderDTO) {
        final Order order = orderRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(orderDTO, order);
        orderRepository.save(order);
    }

    public void delete(final Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO mapToDTO(final Order order, final OrderDTO orderDTO) {
        orderDTO.setId(order.getId());
        orderDTO.setOrderName(order.getOrderName());
        orderDTO.setProducts(order.getProducts().stream()
                .map(product -> product.getId())
                .toList());
        return orderDTO;
    }

    private Order mapToEntity(final OrderDTO orderDTO, final Order order) {
        order.setOrderName(orderDTO.getOrderName());
        final List<Product> products = productRepository.findAllById(
                orderDTO.getProducts() == null ? Collections.emptyList() : orderDTO.getProducts());
        if (products.size() != (orderDTO.getProducts() == null ? 0 : orderDTO.getProducts().size())) {
            throw new NotFoundException("one of products not found");
        }
        order.setProducts(products.stream().collect(Collectors.toSet()));
        return order;
    }

}
