package cybersoft.java16.ecom.product.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java16.ecom.product.model.ProductSize;
import cybersoft.java16.ecom.product.repository.ProductSizeRepository;
import cybersoft.java16.ecom.product.validation.annotation.UniqueProductSizeName;

public class UniqueProductSizeNameValidator implements ConstraintValidator<UniqueProductSizeName, String> {
	private String message;
	
	@Autowired
	private ProductSizeRepository repository;
	
	@Override
	public void initialize(UniqueProductSizeName uniqueProductSizeName) {
		message = uniqueProductSizeName.message();
	}
	
	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		Optional<ProductSize> productSizeOpt = repository.findByName(name);
		if(productSizeOpt.isEmpty()) {
			return true;
		}
		context.buildConstraintViolationWithTemplate(message)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
		return false;
	}

}
