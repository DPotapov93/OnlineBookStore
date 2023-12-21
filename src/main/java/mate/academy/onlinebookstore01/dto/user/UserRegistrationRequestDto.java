package mate.academy.onlinebookstore01.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mate.academy.onlinebookstore01.validation.FieldMatch;

@Data
public class UserRegistrationRequestDto {
    private static final String EMAIL_EXCEPTION
            = "Cannot be null/empty and must consist symbol: @";
    private static final String PASSWORD_EXCEPTION
            = "Cannot be null and must be between 8 and 16 characters.";
    private static final String NULL_MESSAGE
            = "Cannot be null or empty";
    @NotNull
    @NotEmpty
    @Email(message = EMAIL_EXCEPTION)
    private String email;
    @NotNull(message = PASSWORD_EXCEPTION)
    @NotEmpty
    @Size(min = 8, max = 16)
    @FieldMatch
    private String password;
    @NotNull
    @NotEmpty
    @Size(min = 8, max = 16)
    private String repeatPassword;
    @NotNull
    @NotEmpty(message = NULL_MESSAGE)
    private String firstName;
    @NotNull
    @NotEmpty(message = NULL_MESSAGE)
    private String lastName;
    private String shippingAddress;
}
