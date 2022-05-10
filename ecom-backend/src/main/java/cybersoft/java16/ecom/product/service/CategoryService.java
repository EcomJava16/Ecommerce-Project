package cybersoft.java16.ecom.product.service;

import java.util.List;

import cybersoft.java16.ecom.product.dto.CategoryDTO;
import cybersoft.java16.ecom.product.dto.CategoryWithSubCategoriesDTO;

public interface CategoryService {
	CategoryDTO 						createNewCategory(CategoryDTO dto);
	List<CategoryDTO>					findAllCategoryDTO();
	CategoryWithSubCategoriesDTO 		findCategoryWithSubCategoryByCategoryId(String categoryId);
	CategoryDTO 						updateCategory(String id, CategoryDTO dto);
	CategoryDTO							addProduct(String categoryId, String productId);
	CategoryWithSubCategoriesDTO		addSubCategory(String categoryId, String subCategoryId);
	List<CategoryWithSubCategoriesDTO> 	removeProduct(String productId);
	CategoryDTO 						deleteCategory(String id);
	String 								getErrorMessage();
}
