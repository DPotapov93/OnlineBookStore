package mate.academy.onlinebookstore01.repository;

import java.util.List;
import mate.academy.onlinebookstore01.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId, Pageable pageable);
}
