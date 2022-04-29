package cybersoft.java16.ecom.role.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import cybersoft.java16.ecom.role.validation.annotation.UniqueRoleName;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserGroupDTO {
	@UniqueRoleName(message = "{group.name.existed}")
	@Size(min=5, max=30, message = "{group.name.size}")
	private String name;
	 
	@NotBlank(message = "{group.description.blank}")
	private String description;
}
