package cybersoft.java16.ecom.role.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cybersoft.java16.ecom.role.validation.validator.UniqueNameValidator;

@Constraint(validatedBy = UniqueNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UniqueName {
	String message() default "The name already existed";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
