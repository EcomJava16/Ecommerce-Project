package cybersoft.java16.ecom.product.service;

import cybersoft.java16.ecom.product.dto.ProductDTO;
import cybersoft.java16.ecom.product.dto.SubCategoryDTO;
import cybersoft.java16.ecom.product.dto.SubCategoryWithProductsDTO;

public interface SubCategoryService  {
	SubCategoryDTO creatNewSubCategory(SubCategoryDTO subCategoryDTO);
	SubCategoryWithProductsDTO findById(String id); 
	SubCategoryWithProductsDTO addProductDTO(String subCategoryId,String productId);
		
	
}
