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

import com.fasterxml.jackson.annotation.JsonIgnore;

import cybersoft.java16.ecom.common.model.BaseEntity;
import cybersoft.java16.ecom.program.model.UserProgram;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name="user_role")
public class UserRole extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name="name", nullable = false, unique = true)
	private String name;
	
	@Column(name="description", nullable = false)
	private String description;
	@JsonIgnore
	@ManyToMany(mappedBy = "roles",targetEntity = UserGroup.class)
	private Set<UserGroup> groups = new LinkedHashSet<UserGroup>();
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinTable(name = "role_with_program", joinColumns = @JoinColumn(name="program_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<UserProgram> programs = new LinkedHashSet<UserProgram>();
	
	public void addPrograms(UserProgram program) {
		programs.add(program);
		program.getRoles().add(this);
	}
	public void removePrograms(UserProgram program) {
		programs.remove(program);
		program.getRoles().remove(this);
	}
}
