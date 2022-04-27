package cybersoft.java16.ecom.user.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java16.ecom.user.model.EcomUser;
import cybersoft.java16.ecom.user.repository.UserRepository;
import cybersoft.java16.ecom.user.validation.annotation.UniqueUsername;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{
	private String message;
	
	@Autowired
	private UserRepository repository;

	@Override
	public void initialize(UniqueUsername uniqueUsername) {
		message = uniqueUsername.message();
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		Optional<EcomUser> user = repository.findByUsername(username);
		if (user.isEmpty()) {
			return true;
		}
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation().disableDefaultConstraintViolation();
		return false;
	}

}
