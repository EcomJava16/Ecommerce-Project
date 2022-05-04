package cybersoft.java16.ecom.program.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cybersoft.java16.ecom.common.model.BaseEntity;
import cybersoft.java16.ecom.role.model.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_program")
public class UserProgram extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@Column(name = "name", unique = true)
	private String name;
	@Enumerated(EnumType.STRING)
	private ProgramModule module;
	@Enumerated(EnumType.STRING)
	private ProgramType type;
	private String description;
	@JsonIgnore
	@ManyToMany(mappedBy = "programs",targetEntity = UserRole.class)
	private Set<UserRole> roles = new LinkedHashSet<UserRole>();
}
