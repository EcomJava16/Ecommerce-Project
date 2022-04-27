package cybersoft.java16.ecom.product.model;

import javax.persistence.Access;
import javax.persistence.AccessType;

import javax.persistence.Entity;
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
	private String model;
	private String year;
	
}