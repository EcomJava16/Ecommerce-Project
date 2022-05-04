package cybersoft.java16.ecom.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import cybersoft.java16.ecom.product.dto.SubCategoryDTO;
import cybersoft.java16.ecom.product.dto.SubCategoryWithProductsDTO;
import cybersoft.java16.ecom.product.model.SubCategory;

@Mapper
public interface SubCategoryMapper {
	SubCategoryMapper INSTANCE = Mappers.getMapper(SubCategoryMapper.class);
	SubCategoryDTO 				toDTO (SubCategory model);
	SubCategory 				toModel (SubCategoryDTO dto);
	SubCategoryWithProductsDTO 	toDTOWithProducts(SubCategory model);
}
