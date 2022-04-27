package cybersoft.java16.ecom.product.model;


import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

import cybersoft.java16.ecom.common.model.BaseEntity;
import cybersoft.java16.ecom.product.util.Value;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "product")
public class Product extends BaseEntity {
	
	@Column(unique = true)
	@NotBlank (message = "{product.code.notblank}")
	@Size(min = Value.CODE_SIZE_MIN, max = Value.CODE_SIZE_MAX, message = "{product.code.size}")
	private String code;
	
	@NotBlank(message = "{product.name.notblank}")
	private String name;
	
	@NotBlank(message = "{product.description.notblank}")
	private String description;
	
	@DecimalMin(value = Value.PRICE_DECIMAL_MIN,message = "{product.price.decimalmin}")
	@DecimalMax(value = Value.PRICE_DECIMAL_MAX,message = "{product.price.decimalmax}")
	@Digits(integer = Value.PRICE_DIGITS_INTEGER
			,fraction = Value.PRICE_DIGITS_FRACTION
			, message = "{product.price.digits}")
	@ColumnDefault(value = Value.PRICE_COLUMN_DEFAULT)
	private double price;
	
	@NotNull
	@Min(value = Value.STOCK_MIN,message = "{product.stock.min}")
	@Max(value = Value.STOCK_MAX,message = "{product.stock.max}")
	@Digits(integer = Value.STOCK_DIGITS_INTEGER
			,fraction = Value.STOCK_DIGITS_FRACTION
			, message = "{product.stock.digits}")
	@ColumnDefault(value = Value.STOCK_COLUMN_DEFAULT)
	private int stock;
	
	@ManyToOne(targetEntity = Category.class)
	@JoinColumn(name = "category_id")
	private UUID categoryId;

	public void addCategory(String categoryId) {
		this.setCategoryId(UUID.fromString(categoryId));
	}
	
	public void removeCategory() {
		this.setCategoryId(null);
	}
}
