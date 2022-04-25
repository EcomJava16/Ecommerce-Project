package cybersoft.java16.ecom.role.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "user_group")
public class UserGroup extends BaseEntity {

	@Column(name="code", nullable = false, unique = true)
	private String code;
	
	@Column(name="name", nullable = false, unique = true)
	private String name;
	
	@Column(name="description", nullable = false)
	private String description;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(
			name="group_role",
			joinColumns = @JoinColumn(name = "group_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private Set<UserRole> roles = new LinkedHashSet<UserRole>();

	public void addRole(UserRole role) {
		roles.add(role);
		role.getGroups().add(this);
	}

	public void removeRole(UserRole role) {
		roles.remove(role);
		role.getGroups().remove(this);
	}

}
