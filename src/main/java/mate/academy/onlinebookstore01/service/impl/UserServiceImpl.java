package mate.academy.onlinebookstore01.service.impl;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore01.dto.user.UserRegistrationRequestDto;
import mate.academy.onlinebookstore01.dto.user.UserResponseDto;
import mate.academy.onlinebookstore01.exception.RegistrationException;
import mate.academy.onlinebookstore01.mapper.UserMapper;
import mate.academy.onlinebookstore01.model.Role;
import mate.academy.onlinebookstore01.model.User;
import mate.academy.onlinebookstore01.repository.RoleRepository;
import mate.academy.onlinebookstore01.repository.UserRepository;
import mate.academy.onlinebookstore01.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("This email is already registered");
        }
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User user = userMapper.toModel(requestDto);
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(Role.RoleName.USER);
        roles.add(role);
        user.setRoles(roles);
        return userMapper.toDto(userRepository.save(user));
    }
}
