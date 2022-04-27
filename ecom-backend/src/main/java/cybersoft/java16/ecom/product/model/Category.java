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
@Access(AccessType.FIELD)
@Table(name = "category")
public class Category extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String model;
	private String year;
	
	@OneToMany(mappedBy = "category")
	private Set<Product> products = new LinkedHashSet<Product>();
	
	public void addProduct(Product product) {
		product.setCategory(this);
		products.add(product);
	}
	
	public void removeProduct(Product product) {
		product.setCategory(null);
		products.remove(product);
	}
}