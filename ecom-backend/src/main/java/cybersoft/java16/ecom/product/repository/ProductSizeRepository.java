package cybersoft.java16.ecom.product.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cybersoft.java16.ecom.product.model.ProductSize;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, UUID> {

	Optional<ProductSize> findByName(String name);
}
