package cybersoft.java16.ecom.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java16.ecom.common.helper.ResponseHelper;
import cybersoft.java16.ecom.security.dto.UserLoginDTO;
import cybersoft.java16.ecom.security.service.UserLoginService;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/login")
@RestController
public class AuthController {
	@Autowired
	private UserLoginService service;
	@PostMapping
	public Object login(@Valid @RequestBody UserLoginDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		String jwt = service.generateToken(dto);
		if (jwt == null) {
			return ResponseHelper.getErrorResponse("Password is incorrect",HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(jwt, HttpStatus.CREATED);
	}
}
