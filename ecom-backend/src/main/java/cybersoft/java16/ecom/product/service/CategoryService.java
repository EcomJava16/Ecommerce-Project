package cybersoft.java16.ecom.product.service;

import java.util.List;

import cybersoft.java16.ecom.product.dto.CategoryDTO;
import cybersoft.java16.ecom.product.dto.CategoryWithProductsAndSubCategoriesDTO;
import cybersoft.java16.ecom.product.dto.CategoryWithProductsDTO;
import cybersoft.java16.ecom.product.dto.CategoryWithSubCategoriesDTO;

public interface CategoryService {
	List<CategoryDTO> findAllCategoryDTO();
	CategoryDTO createNewCategory(CategoryDTO dto);
	CategoryWithProductsDTO addProduct(String categoryId, String productId);
	CategoryWithProductsDTO removeProduct(String categoryId, String productId);
	CategoryWithProductsDTO findCategoryWithProductsByCategoryId(String categoryId);
	CategoryWithSubCategoriesDTO addSubCategory(String categoryId, String subCategoryId);
	List<CategoryWithProductsAndSubCategoriesDTO> findAllCategoriesWithProductsAndSubCategoriesDTO();
}
