package cybersoft.java16.ecom.product.dto;

import javax.validation.constraints.NotBlank;

import cybersoft.java16.ecom.product.validation.annotation.UniqueCategoryName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
	@UniqueCategoryName
	@NotBlank(message = "{category.name.notblank}")
	private String name;
}
