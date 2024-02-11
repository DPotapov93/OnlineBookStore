package mate.academy.onlinebookstore01.repository;

import mate.academy.onlinebookstore01.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(Role.RoleName roleName);
}
