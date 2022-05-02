package cybersoft.java16.ecom.program.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java16.ecom.program.dto.ProgramDTO;
import cybersoft.java16.ecom.program.mapper.ProgramMapper;
import cybersoft.java16.ecom.program.model.UserProgram;
import cybersoft.java16.ecom.program.repository.ProgramRepository;

@Service
public class ProgramServiceImpl implements ProgramService{
	@Autowired
	private ProgramRepository repository;

	@Override
	public List<ProgramDTO> getAllPrograms() {
		return repository.findAll().stream().map(t -> ProgramMapper.INSTANCE.toProgramDTO(t)).collect(Collectors.toList());
	}

	@Override
	public ProgramDTO createNewProgram(ProgramDTO dto) {
		UserProgram newProgram = ProgramMapper.INSTANCE.toProgram(dto);
		repository.save(newProgram);
		return ProgramMapper.INSTANCE.toProgramDTO(newProgram);
	}
	
}
 