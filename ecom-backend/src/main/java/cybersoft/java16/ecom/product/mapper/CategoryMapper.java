package cybersoft.java16.ecom.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import cybersoft.java16.ecom.product.dto.CategoryDTO;
import cybersoft.java16.ecom.product.dto.CategoryWithProductsAndSubCategoriesDTO;
import cybersoft.java16.ecom.product.dto.CategoryWithProductsDTO;
import cybersoft.java16.ecom.product.dto.CategoryWithSubCategoriesDTO;
import cybersoft.java16.ecom.product.model.Category;

@Mapper
public interface CategoryMapper {
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	CategoryDTO toDTO(Category model);
	Category toModel(CategoryDTO dto);
	CategoryWithProductsDTO toDTOWithProducts(Category modifiedCategory);
	CategoryWithSubCategoriesDTO toDTOWithSubCategories(Category modifiedCategory); 
	CategoryWithProductsAndSubCategoriesDTO toDTOWithProductsAndSubCategories(Category modifiedCategory);
}
