package cybersoft.java16.ecom.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java16.ecom.common.helper.ResponseHelper;
@RequestMapping("/api/v1/user")
@RestController
public class UserController {
	@GetMapping
 public Object welcome() {
	 return ResponseHelper.getErrorResponse("Welcome", HttpStatus.NOT_FOUND);
 }
}
