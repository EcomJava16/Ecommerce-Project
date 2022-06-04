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
import cybersoft.java16.ecom.role.dto.UserGroupDTO;
import cybersoft.java16.ecom.role.dto.UserGroupWithRoleDTO;
import cybersoft.java16.ecom.role.dto.UserGroupWithUserDTO;
import cybersoft.java16.ecom.role.dto.UserGroupUpdateDTO;
import cybersoft.java16.ecom.role.service.UserGroupService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/groups")
public class UserGroupController {

	@Autowired
	private UserGroupService service;

	@GetMapping
	public Object findAllGroup() {

		List<UserGroupDTO> listGroups = service.findAllGroups();
		return ResponseHelper.getResponse(listGroups, HttpStatus.OK);
	}

	@PostMapping("/create")
	public Object createNewGroup(@Valid @RequestBody UserGroupDTO dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		}
		UserGroupDTO newGroup = service.createNewGroup(dto);
		return ResponseHelper.getResponse(newGroup, HttpStatus.CREATED);
	}

	@PutMapping("update/{id}")
	public Object updateGroup(@PathVariable(name = "id") String groupId, @Valid @RequestBody UserGroupUpdateDTO dto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		}
		UserGroupDTO updatedGroup = service.updateGroup(groupId, dto);
		if (updatedGroup == null) {
			return ResponseHelper.getErrorResponse("Id is invalid", HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(updatedGroup, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public Object deleteGroup(@PathVariable(name = "id") String groupId) {
		UserGroupDTO deletedGroup = service.deleteGroup(groupId);

		if (deletedGroup == null) {
			return ResponseHelper.getErrorResponse("Id is invalid",HttpStatus.BAD_REQUEST);
			}

		return ResponseHelper.getResponse(deletedGroup, HttpStatus.ACCEPTED);
	}

	@PutMapping("/addRole/{groupName}/{roleName}")
	public Object addRole(@PathVariable(name = "groupName") String groupName,
			@PathVariable(name = "roleName") String roleName) {
		UserGroupWithRoleDTO groupWithRole= service.addRoleIntoGroupByName(groupName, roleName);
		if (groupWithRole==null){
			return ResponseHelper.getErrorResponse("Id group or role is invalid",HttpStatus.BAD_REQUEST);
		}
			return ResponseHelper.getResponse(groupWithRole, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/removeRole/{groupName}/{roleName}")
	public Object removeRole(@PathVariable(name = "groupName") String groupName,
			@PathVariable(name = "roleName") String roleName) {
		UserGroupWithRoleDTO groupWithRole= service.removeRoleFromGroupByName(groupName, roleName);
		if (groupWithRole==null){
			return ResponseHelper.getErrorResponse("Id group or role is invalid",HttpStatus.BAD_REQUEST);
		}
			return ResponseHelper.getResponse(groupWithRole, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/addUser/{groupName}/{username}")
	public Object addUser(@PathVariable(name = "groupName") String groupName,
			@PathVariable(name = "username") String username) {
		UserGroupWithUserDTO groupWithUser= service.addUserIntoGroupByName(groupName, username);
		if (groupWithUser==null){
			return ResponseHelper.getErrorResponse("Group name or username is invalid",HttpStatus.BAD_REQUEST);
		}
			return ResponseHelper.getResponse(groupWithUser, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/removeUser/{groupName}/{username}")
	public Object removeUser(@PathVariable(name = "groupName") String groupName,
			@PathVariable(name = "username") String username) {
		UserGroupWithUserDTO groupWithUser= service.removeUserFromGroupByName(groupName, username);
		if (groupWithUser==null){
			return ResponseHelper.getErrorResponse("Group name or username is invalid",HttpStatus.BAD_REQUEST);
		}
			return ResponseHelper.getResponse(groupWithUser, HttpStatus.ACCEPTED);
	}
}
