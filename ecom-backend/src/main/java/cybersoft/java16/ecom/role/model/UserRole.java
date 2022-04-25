package cybersoft.java16.ecom.role.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import cybersoft.java16.ecom.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Table(name="user_role")
public class UserRole extends BaseEntity {
	
	@Column(name="code", nullable = false, unique = true)
	private String code;
	
	@Column(name="name", nullable = false, unique = true)
	private String name;
	
	@Column(name="description", nullable = false)
	private String description;
	
	@ManyToMany(mappedBy = "roles")
	private Set<UserGroup> groups = new LinkedHashSet();
}
