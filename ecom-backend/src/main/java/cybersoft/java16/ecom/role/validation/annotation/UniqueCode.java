package cybersoft.java16.ecom.role.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cybersoft.java16.ecom.role.validation.validator.UniqueCodeValidator;

@Constraint(validatedBy = UniqueCodeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface UniqueCode {
	String message() default "The code already existed.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
