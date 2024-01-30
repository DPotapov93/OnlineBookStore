package mate.academy.onlinebookstore01.repository;

import mate.academy.onlinebookstore01.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
