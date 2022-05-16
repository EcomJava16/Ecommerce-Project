package cybersoft.java16.ecom.product.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
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
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "category")
	private Set<SubCategory> sub = new LinkedHashSet<SubCategory>();
	
	public void addProduct(Product product) {
		sub.stream().map(s -> {
			if (product.getSubCategoryModel() == s)
				s.getProducts().add(product);
			return sub;
		});
	}
	
	public void removeProduct(Product product) {
		if(product.getSubCategory() == null)
			sub.stream().map(s -> {
			if (product.getSubCategoryModel() == s)
				s.getProducts().remove(product);
			return sub;
		});
	}
	
	public void addSubCategory(SubCategory subCategory) {
		subCategory.setCategory(this);
		sub.add(subCategory);
	}
	
	public void removeSubCategory(SubCategory subCategory) {
		subCategory.setCategory(null);
		sub.remove(subCategory);
	}
}
