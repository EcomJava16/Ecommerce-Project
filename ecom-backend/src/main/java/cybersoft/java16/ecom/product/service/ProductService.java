package cybersoft.java16.ecom.product.service;

import java.util.List;

import cybersoft.java16.ecom.product.dto.ProductDTO;
import cybersoft.java16.ecom.product.dto.ProductUpdateDTO;

public interface ProductService {
	ProductDTO 			createNewProduct(ProductDTO dto);
	List<ProductDTO> 	findAllProductDTO();
	ProductDTO 			findById(String id);
	ProductDTO 			findByCode(String code);
	ProductDTO 			findByName(String name);
	ProductUpdateDTO 	updateProduct(String id,ProductUpdateDTO dto);
	ProductDTO 			deleteProductById(String id);
	String 				getErrorMessage();
}
