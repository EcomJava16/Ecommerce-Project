package cybersoft.java16.ecom.product.dto;

import java.util.Set;

import cybersoft.java16.ecom.product.validation.annotation.UniqueProductSizeName;
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
public class ProductSizeDTO {
	@UniqueProductSizeName
	private String name;
}
