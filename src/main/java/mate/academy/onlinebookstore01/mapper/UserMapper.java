package mate.academy.onlinebookstore01.mapper;

import mate.academy.onlinebookstore01.config.MapperConfig;
import mate.academy.onlinebookstore01.dto.user.UserRegistrationRequestDto;
import mate.academy.onlinebookstore01.dto.user.UserResponseDto;
import mate.academy.onlinebookstore01.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto userRegistrationRequestDto);
}
