package mate.academy.onlinebookstore01.repository;

import java.util.Optional;
import mate.academy.onlinebookstore01.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
