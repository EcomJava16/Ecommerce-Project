package cybersoft.java16.ecom.user.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cybersoft.java16.ecom.user.model.EcomUser;
@Repository
public interface UserRepository extends JpaRepository<EcomUser, UUID>{

	Optional<EcomUser> findByUsername(String username);

	List<EcomUser> findByDeleteFalse();

}
