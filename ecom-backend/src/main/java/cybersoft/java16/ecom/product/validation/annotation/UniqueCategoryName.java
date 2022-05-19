package cybersoft.java16.ecom.product.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cybersoft.java16.ecom.product.validation.validator.UniqueCategoryNameValidator;

@Constraint(validatedBy = UniqueCategoryNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UniqueCategoryName {
	String message() default "{category.validation.uniquecategoryname.existedname}";
	
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default {};
}
