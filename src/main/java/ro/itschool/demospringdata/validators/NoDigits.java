package ro.itschool.demospringdata.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NoDigitsValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoDigits {

    String message() default "String cannot contain digits.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
