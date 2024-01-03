package mate.academy.onlinebookstore01.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDto(
        @Email
        @NotBlank
        String email,
        @NotBlank
        @Size(min = 8, max = 16)
        String password
) {
}
