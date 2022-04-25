package cybersoft.java16.ecom.role.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java16.ecom.role.dto.UserGroupDTO;
import cybersoft.java16.ecom.role.dto.UserRoleDTO;
import cybersoft.java16.ecom.role.model.UserGroup;
import cybersoft.java16.ecom.role.model.UserRole;
import cybersoft.java16.ecom.role.repository.UserGroupRepository;
import cybersoft.java16.ecom.role.repository.UserRoleRepository;
import cybersoft.java16.ecom.role.validation.annotation.UniqueName;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, Object> {
	@Autowired
	private UserRoleRepository roleRepository;

	@Autowired
	private UserGroupRepository groupRepository;

	private String message;

	@Override
	public void initialize(UniqueName constraintAnnotation) {
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		if (obj instanceof UserRoleDTO) {
			UserRoleDTO dto = (UserRoleDTO) obj;
			Optional<UserRole> role = roleRepository.findByName(dto.getName());

			if (role.isEmpty())
				return true;
		} else if (obj instanceof UserGroupDTO) {
			UserGroupDTO dto = (UserGroupDTO) obj;
			Optional<UserGroup> group = groupRepository.findByName(dto.getName());

			if (group.isEmpty())
				return true;
		}

		context.buildConstraintViolationWithTemplate(message).addConstraintViolation()
				.disableDefaultConstraintViolation();
		return false;
	}

}
