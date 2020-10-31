package to.uk.mkhardy.passwordmanager.service.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = BlankListElementValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface BlankListElementConstraint {
    String message() default "The input list cannot contain blank elements.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}