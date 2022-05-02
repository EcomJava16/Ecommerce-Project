package cybersoft.java16.ecom.security.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cybersoft.java16.ecom.security.validation.validator.UsernameExistedValidator;

@Constraint(validatedBy = UsernameExistedValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UsernameExisted {
	String message() default "The username is  inexisted";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
