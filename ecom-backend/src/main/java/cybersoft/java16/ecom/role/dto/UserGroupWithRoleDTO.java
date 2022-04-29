package cybersoft.java16.ecom.role.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGroupWithRoleDTO {
	private String name;
	private String description;
	private Set<UserRoleDTO> roles;
}
