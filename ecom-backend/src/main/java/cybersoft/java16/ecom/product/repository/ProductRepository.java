package cybersoft.java16.ecom.product.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cybersoft.java16.ecom.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
	Optional<Product> findByName(String name);
	Optional<Product> findBySlug(String slug);
}
