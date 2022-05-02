package cybersoft.java16.ecom.role.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import cybersoft.java16.ecom.program.dto.ProgramDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRoleWithProgramsDTO {
	@Size(min=5, max=30, message = "{role.name.size}")
private String name;
	@NotBlank(message = "{role.description.blank}")
private String description;
private Set<ProgramDTO> programs;
}
