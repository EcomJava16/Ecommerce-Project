package cybersoft.java16.ecom.role.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java16.ecom.common.helper.ResponseHelper;
import cybersoft.java16.ecom.role.dto.UserRoleDTO;
import cybersoft.java16.ecom.role.dto.UserRoleUpdateDTO;
import cybersoft.java16.ecom.role.dto.UserRoleWithProgramsDTO;
import cybersoft.java16.ecom.role.service.UserRoleService;

@CrossOrigin(origins = {"http://localhost:3000","https://frontendjava16.herokuapp.com"})
@RestController
@RequestMapping("/api/v1/roles")
public class UserRoleController {
	@Autowired
	private UserRoleService service;

	@GetMapping
	public Object findAllRoles() {
		List<UserRoleDTO> listRoles = service.findAllRoles();
		return new ResponseEntity<>(listRoles, HttpStatus.OK);
	}

	@PostMapping("/create")
	public Object createNewRole(@Valid @RequestBody UserRoleDTO dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		}
		UserRoleDTO newRole = service.createNewRole(dto);
		return ResponseHelper.getResponse(newRole, HttpStatus.CREATED);
	}

	@PutMapping("/update/{role-id}")
	public Object updateRole(@PathVariable(name = "role-id") String roleId, @Valid @RequestBody UserRoleUpdateDTO dto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		}
		UserRoleDTO updatedRole= service.updateRole(roleId, dto);

		if (updatedRole== null) {
			return ResponseHelper.getErrorResponse("Id is invalid", HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(updatedRole, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public Object deleteRole(@PathVariable(name = "id") String roleId) {
		UserRoleDTO deletedRole = service.deleteRole(roleId);

		if (deletedRole == null) {
			return ResponseHelper.getErrorResponse("Id is invalid", HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(deletedRole, HttpStatus.OK);
	}
	@PutMapping("/add_program/{program_id}/{role_id}")
	public Object addProgramIntoRole(@PathVariable(name = "program_id") String programId, @PathVariable(name = "role_id") String roleId) {
		UserRoleWithProgramsDTO roleWithPrograms = service.addProgramsIntoRole(programId, roleId);
		if (roleWithPrograms == null) {
			return ResponseHelper.getErrorResponse("Id is invalid",HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(roleWithPrograms, HttpStatus.CREATED);
	}
}
