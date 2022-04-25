package cybersoft.java16.ecom.role.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java16.ecom.role.dto.UserRoleDTO;
import cybersoft.java16.ecom.role.dto.UserRoleUpdateDTO;
import cybersoft.java16.ecom.role.service.UserRoleService;

@RestController
@RequestMapping("/api/v1/roles")
public class UserRoleController {
	@Autowired
	private UserRoleService service;

	@PostMapping("/create")
	public Object createNewRole(@Valid @RequestBody UserRoleDTO dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(
					bindingResult.getAllErrors().stream().map(t -> t.getDefaultMessage()).collect(Collectors.toList()),
					HttpStatus.BAD_REQUEST);
		service.createNewRole(dto);
		return new ResponseEntity<>("Create new role successfully", HttpStatus.CREATED);
	}

	@GetMapping
	public Object findAllRole() {
		List<UserRoleDTO> lstRoles = service.findAllDto();
		return new ResponseEntity<>(lstRoles, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public Object updateRole(@PathVariable(name="id") String roleId, @Valid @RequestBody UserRoleUpdateDTO dto, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(bindingResult.getAllErrors()
					.stream()
					.map(t->t.getDefaultMessage())
					.collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
		UserRoleDTO updated = service.updateRole(roleId, dto);
		
		if(updated == null) {
			return new ResponseEntity<>("Id is invalid", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Update role successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public Object deleteRole(@PathVariable(name="id") String roleId) {
		UserRoleDTO deleted = service.deleteRole(roleId);
		
		if(deleted ==null) {
			return new ResponseEntity<>("Id is invalid", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Delete role successfully", HttpStatus.OK);
	}
}
