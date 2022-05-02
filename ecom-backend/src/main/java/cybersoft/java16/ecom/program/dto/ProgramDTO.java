package cybersoft.java16.ecom.program.dto;

import cybersoft.java16.ecom.program.model.ProgramModule;
import cybersoft.java16.ecom.program.model.ProgramType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class ProgramDTO {
private String name;
private ProgramModule module;
private ProgramType type;
private String description;
}
