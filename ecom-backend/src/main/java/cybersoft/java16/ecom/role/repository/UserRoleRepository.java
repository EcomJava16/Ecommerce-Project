package cybersoft.java16.ecom.role.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cybersoft.java16.ecom.role.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID>{

	Optional<UserRole> findByName(String username);

}
