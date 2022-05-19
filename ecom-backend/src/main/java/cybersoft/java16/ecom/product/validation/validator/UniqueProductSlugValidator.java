package cybersoft.java16.ecom.product.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java16.ecom.product.model.Product;
import cybersoft.java16.ecom.product.repository.ProductRepository;
import cybersoft.java16.ecom.product.validation.annotation.UniqueProductSlug;

public class UniqueProductSlugValidator implements ConstraintValidator<UniqueProductSlug, String> {
	private String message;
	
	@Autowired
	private ProductRepository repository;
	
	@Override
	public void initialize(UniqueProductSlug uniqueProductSlug) {
		message = uniqueProductSlug.message();
	}
		
	@Override
	public boolean isValid(String slug, ConstraintValidatorContext context) {
		Optional<Product> productOpt = repository.findBySlug(slug);
		if(productOpt.isEmpty())
			return true;
		context.buildConstraintViolationWithTemplate(message)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
		return false;
	}

}
