package mate.academy.onlinebookstore01.repository;

import mate.academy.onlinebookstore01.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrderIdAndId(Long orderId, Long itemId);

    Optional<OrderItem> findByOrderIdAndId(Long orderId, Long itemId);
}
