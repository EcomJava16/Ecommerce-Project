package cybersoft.java16.ecom.program.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java16.ecom.common.helper.ResponseHelper;
import cybersoft.java16.ecom.program.dto.ProgramDTO;
import cybersoft.java16.ecom.program.model.ProgramAuthorization;
import cybersoft.java16.ecom.program.service.ProgramService;
@CrossOrigin(origins = {"http://localhost:3000","https://frontendjava16.herokuapp.com"})
@RestController
@RequestMapping("/api/v1/program")
public class ProgramController {
	@Autowired
	private ProgramService service;
	@ProgramAuthorization("find programs")
	@GetMapping
	public Object getAllProgram() {
		List<ProgramDTO> listProgram = service.getAllPrograms();
		return ResponseHelper.getResponse(listProgram, HttpStatus.OK);
	}
	@ProgramAuthorization("create programs")
	@PutMapping("/create")
	public Object createNewProgram(@Valid @RequestBody ProgramDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		ProgramDTO newProgram = service.createNewProgram(dto);
		return ResponseHelper.getResponse(newProgram, HttpStatus.CREATED);
	}
}
