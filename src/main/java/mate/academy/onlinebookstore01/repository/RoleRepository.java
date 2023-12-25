package mate.academy.onlinebookstore01.repository;

import mate.academy.onlinebookstore01.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
