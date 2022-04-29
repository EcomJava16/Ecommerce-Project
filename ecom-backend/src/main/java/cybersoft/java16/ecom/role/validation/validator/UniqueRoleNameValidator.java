package cybersoft.java16.ecom.role.validation.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java16.ecom.role.model.UserRole;
import cybersoft.java16.ecom.role.repository.UserGroupRepository;
import cybersoft.java16.ecom.role.repository.UserRoleRepository;
import cybersoft.java16.ecom.role.validation.annotation.UniqueRoleName;

public class UniqueRoleNameValidator implements ConstraintValidator<UniqueRoleName, String> {
	@Autowired
	private UserRoleRepository roleRepository;

	private String message;

	@Override
	public void initialize(UniqueRoleName constraintAnnotation) {
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String  username , ConstraintValidatorContext context) {
			UserRole role = roleRepository.findByName(username);
			if (role == null) {
				return true;
			}

		context.buildConstraintViolationWithTemplate(message).addConstraintViolation()
				.disableDefaultConstraintViolation();
		return false;
	}

}
