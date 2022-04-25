package cybersoft.java16.ecom.role.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import cybersoft.java16.ecom.role.validation.annotation.UniqueCode;
import cybersoft.java16.ecom.role.validation.annotation.UniqueName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@UniqueCode(message = "{role.code.existed}")
@UniqueName(message = "{role.name.existed}")
public class UserRoleDTO {
	
	@Size(min=5, max=30, message = "{role.code.size}")
	private String code;
	
	@Size(min=5, max=30, message = "{role.name.size}")
	private String name;
	
	@NotBlank(message = "{role.description.blank}")
	private String description;
}
