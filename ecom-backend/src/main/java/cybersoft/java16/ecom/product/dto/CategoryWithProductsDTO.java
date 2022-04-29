package cybersoft.java16.ecom.product.dto;

import java.util.Set;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CategoryWithProductsDTO {
	private UUID id;
	private String model;
	private String year;
	private Set<ProductDTO> products;
}
