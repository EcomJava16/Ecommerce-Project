package cybersoft.java16.ecom.product.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;


import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Transactional
@Entity
@Table(name = "product")
public class Product extends BaseEntity {
	@Column(nullable = false)
	private String category;
	
	@Column(nullable = false)
	private String subCategory;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private byte rate;
	
	@Column(nullable = false)
	private short reviewCount;

	@Column(nullable = false)
	@ColumnDefault(value = Value.PRICE_COLUMN_DEFAULT)
	private double price;
	
	@Column(nullable = false)
	private float discount;
	
	@Column(nullable = false)
	private boolean brandNew;
	
	@Column(nullable = false)
	private short quantity;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "products")
	private Set<Size> size = new LinkedHashSet<Size>();
	
	private ArrayList<String> thumbImage = new ArrayList<String>();
	
	private ArrayList<String> images = new ArrayList<String>();
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String slug;
	
	@ManyToOne()
	@JoinColumn(name = "subcategory_id")
	private SubCategory subCategoryModel;

}
