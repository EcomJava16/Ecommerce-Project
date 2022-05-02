package cybersoft.java16.ecom.user.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java16.ecom.common.helper.ResponseHelper;
import cybersoft.java16.ecom.user.dto.UserDTO;
import cybersoft.java16.ecom.user.dto.UserUpdateDTO;
import cybersoft.java16.ecom.user.service.UserService;

@RequestMapping("/api/v1/user")
@RestController
public class UserController {
	@Autowired
	private UserService service;

	@GetMapping
	public Object getAllUser() {
		List<UserDTO> listUser = service.findAllUser();
		return ResponseHelper.getResponse(listUser, HttpStatus.FOUND);
	}

	@PostMapping("/register")
	public Object createNewUser(@Valid @RequestBody UserDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		UserDTO newUser = service.createNewUser(dto);
		return ResponseHelper.getResponse(newUser, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/update/{user-id}")
	public Object updateUser(@PathVariable(name = "user-id") String userId,@Valid @RequestBody UserUpdateDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		UserDTO updateUser = service.updateUser(userId,dto);
		if (updateUser == null) {
			return ResponseHelper.getErrorResponse("Id is not valid", HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(updateUser, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{user-id}")
	public Object deleteUser(@PathVariable(name = "user-id") String userId) {
		UserDTO deleteUser = service.deleteUser(userId);
		if (deleteUser == null) {
			return ResponseHelper.getErrorResponse("Id is not valid", HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(deleteUser, HttpStatus.ACCEPTED);
	}
}
