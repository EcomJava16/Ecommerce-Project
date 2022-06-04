package cybersoft.java16.ecom.role.dto;

import java.util.Set;

import cybersoft.java16.ecom.user.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGroupWithUserDTO {
	private String name;
	private String description;
	private Set<UserDTO> users;
}
