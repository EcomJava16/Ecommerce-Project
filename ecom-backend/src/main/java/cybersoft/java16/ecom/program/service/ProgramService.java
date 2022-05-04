package cybersoft.java16.ecom.program.service;

import java.util.List;


import cybersoft.java16.ecom.program.dto.ProgramDTO;

public interface ProgramService {

	List<ProgramDTO> getAllPrograms();

	ProgramDTO createNewProgram( ProgramDTO dto);

}
