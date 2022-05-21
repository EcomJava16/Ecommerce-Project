package cybersoft.java16.ecom.product.dto;


import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

import cybersoft.java16.ecom.product.model.Sex;
import cybersoft.java16.ecom.product.util.Value;
import cybersoft.java16.ecom.product.validation.annotation.UniqueProductName;
import cybersoft.java16.ecom.product.validation.annotation.UniqueProductSlug;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductUpdateDTO {
	@UniqueProductName
	@NotBlank(message = "{product.name.notblank}")
	private String name;
	
	@Min(value = Value.RATE_MIN, message = "{product.rate.min}")
	@Max(value = Value.RATE_MAX, message = "{product.rate.max}")
	@NotBlank(message = "{product.rate.notblank}")
	private byte rate;
	
	@Min(value = Value.REVIEW_COUNT_MIN, message = "{product.reviewcount.min}")
	@Max(value = Value.REVIEW_COUNT_MAX, message = "{product.reviewcount.max}")
	@NotNull(message = "{product.reviewcount.notnull}")
	private short reviewCount;

	@NotNull(message = "{product.price.notnull}")
	@DecimalMin(value = Value.PRICE_DECIMAL_MIN,message = "{product.price.decimalmin}")
	@Digits(fraction = Value.PRICE_DIGITS_FRACTION
	,integer = Value.PRICE_DIGITS_INTEGER, message = "{product.price.digits}")
	@DecimalMax(value = Value.PRICE_DECIMAL_MAX,message = "{product.price.decimalmax}")
	@ColumnDefault(value = Value.PRICE_COLUMN_DEFAULT)
	private double price;
	
	@NotNull(message = "{product.discount.notnull}")
	@DecimalMin(value = Value.DISCOUNT_DECIMAL_MIN,message = "{product.discount.decimalmin}")
	@Digits(fraction = Value.DISCOUNT_DIGITS_FRACTION
	,integer = Value.DISCOUNT_DIGITS_INTEGER, message = "{product.discount.digits}")
	@DecimalMax(value = Value.DISCOUNT_DECIMAL_MAX,message = "{product.discount.decimalmax}")
	@ColumnDefault(value = Value.DISCOUNT_COLUMN_DEFAULT)
	private float discount;
	
	@NotNull(message = "{product.brandnew.notnull}")
	private boolean brandNew;
	
	@Min(value = Value.QUANTITY_MIN, message = "{product.quantity.min}")
	@Max(value = Value.QUANTITY_MAX, message = "{product.quantity.max}")
	@NotBlank(message = "{product.quantity.notblank}")
	private short quantity;
	
	@ColumnDefault(value = Value.SEX_DEFAULT)
	private Sex sex;
	
	@NotNull(message = "{product.description.notnull}")
	private String description;
	
	@UniqueProductSlug
	@NotBlank(message = "{product.slug.notblank}")
	private String slug;
}
