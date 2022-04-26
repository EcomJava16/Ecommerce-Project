package cybersoft.java16.ecom.product.model;


import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
@Table(name = "product")
public class Product extends BaseEntity {
	
	@Column(unique = true)
	@NotBlank
	@Size(min = 5, max = 100)
	private String code;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@ManyToOne(targetEntity = Category.class)
	@JoinColumn(name = "category_id")
	private UUID categoryId;
//	
//	private Category caterogy;
//	

	public void addCategory(String categoryId) {
		this.setCategoryId(UUID.fromString(categoryId));
	}
	
	public void removeCategory() {
		this.setCategoryId(null);
	}
}
