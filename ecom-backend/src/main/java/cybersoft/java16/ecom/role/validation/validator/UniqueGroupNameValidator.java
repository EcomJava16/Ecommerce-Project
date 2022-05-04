package cybersoft.java16.ecom.role.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java16.ecom.role.model.UserGroup;
import cybersoft.java16.ecom.role.repository.UserGroupRepository;
import cybersoft.java16.ecom.role.validation.annotation.UniqueGroupName;
import cybersoft.java16.ecom.role.validation.annotation.UniqueRoleName;

public class UniqueGroupNameValidator implements ConstraintValidator<UniqueGroupName, String> {
	private String message;
	@Autowired
	private UserGroupRepository repository;

	@Override
	public void initialize(UniqueGroupName constraintAnnotation) {
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		UserGroup group = repository.findByName(name);
		if (group == null) {
			return true;
		}
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation()
				.disableDefaultConstraintViolation();
		return false;
	}

}
