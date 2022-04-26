package cybersoft.java16.ecom.product.service;

import java.util.List;

import javax.validation.Valid;

import cybersoft.java16.ecom.product.dto.ProductDTO;

public interface ProductService {
	List<ProductDTO> findAllProductDTO();
	ProductDTO createNewProduct(@Valid ProductDTO dto);
	ProductDTO findById(String id);
	ProductDTO updateProduct(String id, @Valid ProductDTO dto);
	ProductDTO deleteProductById(String id);
}
