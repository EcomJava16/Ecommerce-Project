package cybersoft.java16.ecom.product.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cybersoft.java16.ecom.product.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
	@Query("SELECT c FROM Category c JOIN c.products WHERE c.id =?1")
	Category FindCategoryWithProducts(UUID categoryId);
}
