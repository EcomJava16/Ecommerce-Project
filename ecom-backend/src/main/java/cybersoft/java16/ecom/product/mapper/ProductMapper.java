package cybersoft.java16.ecom.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import cybersoft.java16.ecom.product.dto.ProductDTO;
import cybersoft.java16.ecom.product.dto.ProductUpdateDTO;
import cybersoft.java16.ecom.product.model.Product;

@Mapper
public interface ProductMapper {
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	ProductUpdateDTO 	toProductUpdateDTO (Product model);
	ProductDTO 			toDTO(Product model);
	Product 			toModel(ProductDTO dto);
}