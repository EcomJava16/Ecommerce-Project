package cybersoft.java16.ecom.product.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cybersoft.java16.ecom.product.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
	List<Product> findByCategoryId(UUID categoryId);
	Optional<Product> findByCode(String code);
}