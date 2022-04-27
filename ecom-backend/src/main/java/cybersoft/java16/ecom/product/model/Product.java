package cybersoft.java16.ecom.product.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	private String code;
	private String name;
	private String description;
	private String image;
	
	@ColumnDefault(value = Value.PRICE_COLUMN_DEFAULT)
	private double price;
	
	@ColumnDefault(value = Value.STOCK_COLUMN_DEFAULT)
	private int stock;
	
	
	@ManyToOne()
	@JoinColumn(name = "category_id")
	private Category category;
}
