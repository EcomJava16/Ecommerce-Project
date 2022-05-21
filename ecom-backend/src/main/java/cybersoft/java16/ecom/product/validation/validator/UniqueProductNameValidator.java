package cybersoft.java16.ecom.product.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java16.ecom.product.model.Product;
import cybersoft.java16.ecom.product.repository.ProductRepository;
import cybersoft.java16.ecom.product.validation.annotation.UniqueProductName;

public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName, String> {
	private String message;
	
	@Autowired
	private ProductRepository repository;
	
	@Override
	public void initialize(UniqueProductName uniqueProductName) {
		message = uniqueProductName.message();
	}
	
	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		Optional<Product> productOpt = repository.findByName(name);
		if(productOpt.isEmpty())
			return true;
		context.buildConstraintViolationWithTemplate(message)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
		return false;
	}

}
