package org.example.app.services.Utils.annotation;

import org.example.app.services.Utils.validators.FileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FileValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyFile {
    String message() default "File must not be empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}