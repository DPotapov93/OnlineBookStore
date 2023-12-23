package mate.academy.onlinebookstore01.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import mate.academy.onlinebookstore01.dto.user.UserRegistrationRequestDto;

public class FieldMatchValidator implements
        ConstraintValidator<FieldMatch, UserRegistrationRequestDto> {
    @Override
    public boolean isValid(
            UserRegistrationRequestDto userRegistrationRequestDto,
            ConstraintValidatorContext constraintValidatorContext
    ) {
        return Objects.equals(userRegistrationRequestDto.getPassword(),
                userRegistrationRequestDto.getRepeatPassword());
    }
}
