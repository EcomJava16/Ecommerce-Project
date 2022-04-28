package cybersoft.java16.ecom.product.dto;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryWithSubCategoriesDTO {
	private String model;
	private Set<SubCategoryDTO> subCategories;
}
