package cybersoft.java16.ecom.product.service;

import java.util.List;

import javax.validation.Valid;

import cybersoft.java16.ecom.product.dto.CategoryDTO;
import cybersoft.java16.ecom.product.dto.CategoryWithProductsDTO;

public interface CategoryService {
	List<CategoryDTO> findAllCategoryDTO();
	CategoryDTO createNewCategory(@Valid CategoryDTO dto);
	CategoryWithProductsDTO addProduct(String categoryId, String productId);
	CategoryWithProductsDTO removeProduct(String categoryId, String productId);
	CategoryWithProductsDTO findCategoryWithProductsByCategoryId(String categoryId);
}