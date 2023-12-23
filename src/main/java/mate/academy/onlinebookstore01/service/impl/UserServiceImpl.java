package mate.academy.onlinebookstore01.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore01.dto.user.UserRegistrationRequestDto;
import mate.academy.onlinebookstore01.dto.user.UserResponseDto;
import mate.academy.onlinebookstore01.exception.RegistrationException;
import mate.academy.onlinebookstore01.mapper.UserMapper;
import mate.academy.onlinebookstore01.model.User;
import mate.academy.onlinebookstore01.repository.UserRepository;
import mate.academy.onlinebookstore01.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("This email is already registered");
        }
        User saved = userRepository.save(userMapper.toModel(requestDto));
        return userMapper.toDto(saved);
    }
}
