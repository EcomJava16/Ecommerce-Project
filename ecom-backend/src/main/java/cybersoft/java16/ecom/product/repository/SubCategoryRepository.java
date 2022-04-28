package cybersoft.java16.ecom.product.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cybersoft.java16.ecom.product.model.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, UUID> {
	Optional<SubCategory> findById(UUID id);
}
