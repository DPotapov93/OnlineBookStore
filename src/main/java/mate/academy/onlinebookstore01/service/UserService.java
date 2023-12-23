package mate.academy.onlinebookstore01.service;

import mate.academy.onlinebookstore01.dto.user.UserRegistrationRequestDto;
import mate.academy.onlinebookstore01.dto.user.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);
}
