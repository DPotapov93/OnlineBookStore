package mate.academy.onlinebookstore01.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.onlinebookstore01.model.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrderId(Long orderId, Pageable pageable);

    @Query("SELECT oi FROM OrderItem oi "
            + "JOIN FETCH oi.order o "
            + "JOIN FETCH oi.book b "
            + "WHERE oi.id = :itemId AND o.id = :orderId")
    Optional<OrderItem> findByOrderIdAndId(Long itemId, Long orderId);
}
