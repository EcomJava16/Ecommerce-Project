package cybersoft.java16.ecom.product.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryWithSubCategoriesDTO {
	@NotNull(message = "{category.model.notnull}")
	@NotBlank(message = "{category.model.notblank}")
	private String model;
	
	private Set<SubCategoryDTO> subCategories;
}
