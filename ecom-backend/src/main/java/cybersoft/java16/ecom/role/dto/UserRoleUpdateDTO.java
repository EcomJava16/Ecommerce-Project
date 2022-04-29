package cybersoft.java16.ecom.role.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRoleUpdateDTO {
	
	@Size(min=5, max=30, message = "{role.name.size}")
	private String name;
	
	@NotBlank(message = "{role.description.blank}")
	private String description;
}
