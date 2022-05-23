package cybersoft.java16.ecom.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import cybersoft.java16.ecom.product.dto.ProductSizeDTO;
import cybersoft.java16.ecom.product.model.ProductSize;

@Mapper
public interface ProductSizeMapper {
	ProductSizeMapper INSTANCE = Mappers.getMapper(ProductSizeMapper.class);
	ProductSizeDTO toDTO(ProductSize model);
	ProductSize toModel(ProductSizeDTO dto);
}
