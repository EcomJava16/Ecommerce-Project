package cybersoft.java16.ecom.program.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import cybersoft.java16.ecom.program.dto.ProgramDTO;
import cybersoft.java16.ecom.program.model.UserProgram;

@Mapper
public interface ProgramMapper {
ProgramMapper INSTANCE = Mappers.getMapper(ProgramMapper.class);
UserProgram toProgram(ProgramDTO dto);
ProgramDTO toProgramDTO(UserProgram program);
}
