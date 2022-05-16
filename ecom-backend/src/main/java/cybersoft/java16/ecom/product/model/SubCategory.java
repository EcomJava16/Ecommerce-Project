package cybersoft.java16.ecom.product.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
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
	@Column(nullable = false)
	private String name;
	
	@ManyToOne()
	@JoinColumn(name = "category_id")
	private Category  category;
	
	@OneToMany(mappedBy = "subCategoryModel")
	private Set<Product> products = new LinkedHashSet<Product>();
	
	public void addProduct(Product product) {
		product.setSubCategoryModel(this);
		product.setCategory(category.getName());
		product.setSubCategory(name);
		products.add(product);
	}
	
	public void removeProduct(Product product) {
		product.setSubCategoryModel(null);
		product.setCategory(null);
		product.setSubCategory(null);
		products.remove(product);
	}
}
