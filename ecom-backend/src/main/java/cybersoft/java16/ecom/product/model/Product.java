package cybersoft.java16.ecom.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	@Column(nullable = false)
	private String code;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	private String image;
	
	@Column(nullable = false)
	private short year;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Country country;
	
	@Column(nullable = false)
	@ColumnDefault(value = Value.PRICE_COLUMN_DEFAULT)
	private double price;
	
	@Column(nullable = false)
	@ColumnDefault(value = Value.STOCK_COLUMN_DEFAULT)
	private int stock;
	
	@ManyToOne()
	@JoinColumn(name = "subcategory_id")
	private SubCategory subCategory;
}
