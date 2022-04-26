package cybersoft.java16.ecom.product.dto;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryWithProductsDTO {
	private UUID id;
	private String model;
	private String year;
	private List<ProductDTO> products;
}
