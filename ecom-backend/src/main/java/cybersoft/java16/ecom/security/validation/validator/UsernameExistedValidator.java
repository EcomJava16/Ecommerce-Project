package cybersoft.java16.ecom.security.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java16.ecom.security.validation.annotation.UsernameExisted;
import cybersoft.java16.ecom.user.model.EcomUser;
import cybersoft.java16.ecom.user.repository.UserRepository;

public class UsernameExistedValidator implements ConstraintValidator<UsernameExisted, String>{
	
	private String message;
	@Autowired
	private UserRepository repository;

	@Override
	public void initialize(UsernameExisted constraintAnnotation) {
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		Optional<EcomUser> user = repository.findByUsername(username);
		if (user.isPresent()) {
			return true;
		}
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation().disableDefaultConstraintViolation();
		return false;
	}

}
