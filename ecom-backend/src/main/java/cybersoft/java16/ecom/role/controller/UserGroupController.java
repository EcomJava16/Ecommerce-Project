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

import cybersoft.java16.ecom.role.dto.UserGroupDTO;
import cybersoft.java16.ecom.role.dto.UserGroupUpdateDTO;
import cybersoft.java16.ecom.role.service.UserGroupService;

@RestController
@RequestMapping("/api/v1/groups")
public class UserGroupController {

	@Autowired
	private UserGroupService service;
	
	@PostMapping("/create")
	public Object createNewGroup(@Valid @RequestBody UserGroupDTO dto, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(bindingResult.getAllErrors()
					.stream()
					.map(t->t.getDefaultMessage())
					.collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
		service.createNewGroup(dto);
		return new ResponseEntity<>("Create new group successfully", HttpStatus.CREATED);
	}
	
	@GetMapping
	public Object findAllGroup() {
		List<UserGroupDTO> lstGroups = service.findAllDto();
		return new ResponseEntity<>(lstGroups, HttpStatus.OK);
	}
	
	@PutMapping("update/{id}")
	public Object updateGroup(@PathVariable(name="id") String groupId, @Valid @RequestBody UserGroupUpdateDTO dto, 
			BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(bindingResult.getAllErrors()
					.stream()
					.map(t->t.getDefaultMessage())
					.collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
		UserGroupDTO updated = service.update(groupId, dto);
		
		if(updated == null)
			return new ResponseEntity<>("Id is invalid", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>("Update group successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public Object deleteGroup(@PathVariable(name="id") String groupId) {
		UserGroupDTO deleted = service.delete(groupId);
		
		if(deleted == null)
			return new ResponseEntity<>("Id is invalid", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>("Delete group successfully", HttpStatus.OK);
	}
	
	@PutMapping("/addRole/{groupId}/{roleId}")
	public Object addRole(@PathVariable(name="groupId") String groupId, @PathVariable(name="roleId") String roleId) {
		Object obj = service.addRole(groupId, roleId);
		
		if(obj instanceof UserGroupDTO)
			return new ResponseEntity<>("Add role to group successfully", HttpStatus.OK);
		else
			return new ResponseEntity<>(obj.toString(), HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/removeRole/{groupId}/{roleId}")
	public Object removeRole(@PathVariable(name="groupId") String groupId, @PathVariable(name="roleId") String roleId) {
		Object obj = service.removeRole(groupId, roleId);
		
		if(obj instanceof UserGroupDTO)
			return new ResponseEntity<>("Remove role to group successfully", HttpStatus.OK);
		else
			return new ResponseEntity<>(obj.toString(), HttpStatus.BAD_REQUEST);
	}
}
