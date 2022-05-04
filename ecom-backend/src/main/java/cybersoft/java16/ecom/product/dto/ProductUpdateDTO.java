package cybersoft.java16.ecom.product.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

import cybersoft.java16.ecom.product.model.Country;
import cybersoft.java16.ecom.product.util.Value;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductUpdateDTO {
	// No need to check unique code, ProductServiceImpl.updateProduct do that.
	@NotNull(message = "{product.code.notnull}")
	@NotBlank (message = "{product.code.notblank}")
	@Size(min =5, max = 100, message = "{product.code.size}")
	private String code;
	
	@NotNull(message = "{product.name.notnull}")
	@NotBlank(message = "{product.name.notblank}")
	private String name;
	
	@NotNull(message = "{product.description.notnull}")
	@NotBlank(message = "{product.description.notblank}")
	private String description;
	
	@NotNull(message = "{product.year.notnull}")
	private short year;
	
	
	@NotNull(message = "{product.country.notnull}")
	private Country country;
	
	@NotNull(message = "{product.price.notnull}")
	@DecimalMin(value = Value.PRICE_DECIMAL_MIN,message = "{product.price.decimalmin}")
	@Digits(fraction = Value.PRICE_DIGITS_FRACTION
			,integer = Value.PRICE_DIGITS_INTEGER, message = "{product.price.digits}")
	@DecimalMax(value = Value.PRICE_DECIMAL_MAX,message = "{product.price.decimalmax}")
	@ColumnDefault(value = Value.PRICE_COLUMN_DEFAULT)
	private double price;
	
	
	@NotNull(message = "{product.stock.notnull}")
	@Min(value = Value.STOCK_MIN,message = "{product.stock.min}")
	@Max(value = Value.STOCK_MAX,message = "{product.stock.max}")
	@Digits(fraction = Value.STOCK_DIGITS_FRACTION
			,integer = Value.STOCK_DIGITS_INTEGER, message = "{product.stock.digits}")
	@ColumnDefault(value = Value.STOCK_COLUMN_DEFAULT)
	private int stock;
}
