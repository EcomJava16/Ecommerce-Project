package cybersoft.java16.ecom.product.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cybersoft.java16.ecom.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "sub_category")
public class SubCategory extends BaseEntity {
	private short year;
	private String part;
	
	@ManyToOne()
	@JoinColumn(name = "category_id")
	private Category  category;
	
	@OneToMany(mappedBy = "subCategory")
	private Set<Product> products = new LinkedHashSet<Product>();
	
	public void addProduct(Product product) {
		product.setSubCategory(this);
		products.add(product);
	}
	
	public void removeProduct(Product product) {
		product.setSubCategory(null);
		products.remove(product);
	}
}
