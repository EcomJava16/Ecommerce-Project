package cybersoft.java16.ecom.role.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import cybersoft.java16.ecom.role.validation.annotation.UniqueRoleName;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRoleDTO {
	@UniqueRoleName(message = "{role.name.existed}")
	@Size(min=5, max=30, message = "{role.name.size}")
	private String name;
	
	@NotBlank(message = "{role.description.blank}")
	private String description;
}
