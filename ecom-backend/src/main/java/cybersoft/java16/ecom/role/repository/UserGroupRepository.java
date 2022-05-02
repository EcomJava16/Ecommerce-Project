package cybersoft.java16.ecom.role.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cybersoft.java16.ecom.role.model.UserGroup;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, UUID>{
	UserGroup findByName(String name);
}
