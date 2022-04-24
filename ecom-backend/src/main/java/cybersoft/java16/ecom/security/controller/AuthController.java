package cybersoft.java16.ecom.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java16.ecom.common.helper.ResponseHelper;

@RequestMapping("/api/v1/login")
@RestController
public class AuthController {
@GetMapping
public Object welcome() {
	return ResponseHelper.getResponse("Welcome", HttpStatus.ACCEPTED);
}
}
