package cybersoft.java16.ecom.program.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ProgramAuthorization {
String value() default "";
Class<?>[] groups() default{};
}
