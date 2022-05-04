package cybersoft.java16.ecom.product.service;

import cybersoft.java16.ecom.product.dto.SubCategoryDTO;
import cybersoft.java16.ecom.product.dto.SubCategoryWithProductsDTO;
import cybersoft.java16.ecom.product.model.Category;
import cybersoft.java16.ecom.product.model.Product;
import cybersoft.java16.ecom.product.model.SubCategory;

public interface SubCategoryService  {
	SubCategoryDTO 				creatNewSubCategory(SubCategoryDTO subCategoryDTO);
	SubCategoryWithProductsDTO 	findById(String id); 
	SubCategoryWithProductsDTO	addProduct(String subCategoryId,String productId);
	SubCategoryDTO				updateSubCategory(String id, SubCategoryDTO dto);
	SubCategoryWithProductsDTO 	removeProduct(String productId);
	SubCategoryDTO 				deleteById(String id);
	SubCategory 				autoCreateNewSubCategoryWhenAddProductInCategory(Category category, Product product);	
	String 						getErrorMessage();
}
