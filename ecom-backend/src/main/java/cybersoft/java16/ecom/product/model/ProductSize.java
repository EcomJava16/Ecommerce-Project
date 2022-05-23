package cybersoft.java16.ecom.product.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "product_size")
public class ProductSize extends BaseEntity {
	@Column(nullable = false)
	private String name;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(
		name 		= "product_product_size",
		joinColumns = @JoinColumn(name = "size_id"),
		inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	private Set<Product> products = new LinkedHashSet<Product>();
}
