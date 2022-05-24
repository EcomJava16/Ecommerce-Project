package cybersoft.java16.ecom.product.service;

import java.util.List;

import cybersoft.java16.ecom.product.dto.ProductSizeDTO;

public interface ProductSizeService {
	ProductSizeDTO createNewProductSize(ProductSizeDTO dto);
	List<ProductSizeDTO> findAllProductSize();
	ProductSizeDTO updateProductSize(String id,ProductSizeDTO dto);
	ProductSizeDTO deleteProductSize(String id);
	String getErrorMessage();
}
