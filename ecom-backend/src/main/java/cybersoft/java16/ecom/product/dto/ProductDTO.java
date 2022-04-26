package cybersoft.java16.ecom.product.dto;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import cybersoft.java16.ecom.product.validation.annotation.UniqueCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Builder
public class ProductDTO {
	@UniqueCode
	@Size(min =5, max = 100, message = "{product.code.size}")
	private String code;
	
	@NotBlank(message = "{product.name.notblank}")
	private String name;
	
	@NotBlank(message = "{product.description.notblank}")
	private String description;
}
