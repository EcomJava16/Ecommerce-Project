package cybersoft.java16.ecom.role.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import cybersoft.java16.ecom.role.validation.annotation.UniqueCode;
import cybersoft.java16.ecom.role.validation.annotation.UniqueName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@UniqueCode(message = "{group.code.existed}")
@UniqueName(message = "{group.name.existed}")
public class UserGroupDTO {
	
	@Size(min=5, max=30, message = "{group.code.size}")
	private String code;
	
	@Size(min=5, max=30, message = "{group.name.size}")
	private String name;
	 
	@NotBlank(message = "{group.description.blank}")
	private String description;
}
