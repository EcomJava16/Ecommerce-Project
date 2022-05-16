package cybersoft.java16.ecom.product.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubCategoryWithProductsDTO {
	@NotNull(message = "{subcategory.name.notnull}")
	private String name;
	
	private Set<ProductDTO> products;
}
