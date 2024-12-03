package org.example.app.services.Utils.validators;

import org.example.app.services.Utils.annotation.NotEmptyFile;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<NotEmptyFile, MultipartFile> {
    @Override
    public void initialize(NotEmptyFile constraintAnnotation) {}

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return file != null && !file.isEmpty();
    }
}
