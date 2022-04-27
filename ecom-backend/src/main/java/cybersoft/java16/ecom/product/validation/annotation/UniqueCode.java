package cybersoft.java16.ecom.product.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cybersoft.java16.ecom.product.validation.validator.UniqueCodeValidator;

@Constraint(validatedBy = UniqueCodeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UniqueCode {
	String message() default "{product.validation.uniquecode.existedcode}";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
