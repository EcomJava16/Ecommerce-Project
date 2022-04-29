package cybersoft.java16.ecom.product.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cybersoft.java16.ecom.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "category")
public class Category extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String model;
	private String year;
	
	@OneToMany(mappedBy = "category")
	private Set<SubCategory> subCategories = new LinkedHashSet<SubCategory>();
	
	@OneToMany(mappedBy = "category")
	private Set<Product> products = new LinkedHashSet<Product>();
	
	public void addProduct(Product product) {
		product.setCategory(this);
		products.add(product);
		subCategories.stream().map(s -> {
			if (product.getSubCategory() == s)
				s.getProducts().add(product);
			return subCategories;
		});
	}
	
	public void removeProduct(Product product) {
		product.setCategory(null);
		products.remove(product);
		if(product.getSubCategory() == null)
		subCategories.stream().map(s -> {
			if (product.getSubCategory() == s)
				s.getProducts().remove(product);
			return subCategories;
		});
	}
	
	public void addSubCategory(SubCategory subCategory) {
		subCategory.setCategory(this);
		subCategories.add(subCategory);
	}
	
	public void removeSubCategory(SubCategory subCategory) {
		subCategory.setCategory(null);
		subCategories.remove(subCategory);
	}
}
