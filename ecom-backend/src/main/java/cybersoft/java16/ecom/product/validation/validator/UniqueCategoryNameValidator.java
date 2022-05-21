package cybersoft.java16.ecom.product.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java16.ecom.product.model.Category;
import cybersoft.java16.ecom.product.repository.CategoryRepository;
import cybersoft.java16.ecom.product.validation.annotation.UniqueCategoryName;

public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, String> {
	private String message;
	
	@Autowired
	private CategoryRepository repository;
	
	@Override
	public void initialize(UniqueCategoryName uniqueCategoryName) {
		message = uniqueCategoryName.message();
	}
	
	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		Optional<Category> categoryOpt = repository.findByName(name);
		if(categoryOpt.isEmpty())
			return true;
		context.buildConstraintViolationWithTemplate(message)
			.addConstraintViolation()
			.disableDefaultConstraintViolation();
		return false;
	}

}
