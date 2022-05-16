package cybersoft.java16.ecom.product.dto;

import java.util.ArrayList;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cybersoft.java16.ecom.product.model.Sex;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductReturnDTO {
	private String category;
	
	private String subCategory;
	
	private String name;
	
	private byte rate;
	
	private short reviewCount;

	private double price;
	
	private float discount;
	
	private boolean brandNew;
	
	private short quantity;
	
	private Sex sex;
	
	private ArrayList<String> thumbImage;

	private ArrayList<String> images;
	
	@NotNull(message = "{product.description.notnull}")
	private String description;
	
	@NotBlank(message = "{product.slug.notblank}")
	private String slug;
		
}
