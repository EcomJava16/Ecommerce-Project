package cybersoft.java16.ecom.role.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import cybersoft.java16.ecom.role.validation.annotation.UniqueGroupName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserGroupDTO {
	@UniqueGroupName(message = "{group.name.existed}")
	@Size(min = 5, max = 30, message = "{group.name.size}")
	private String name;

	@NotBlank(message = "{group.description.blank}")
	private String description;
}
