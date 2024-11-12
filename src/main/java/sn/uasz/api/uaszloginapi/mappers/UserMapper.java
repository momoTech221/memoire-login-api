package sn.uasz.api.uaszloginapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sn.uasz.api.uaszloginapi.entities.LogResponseDto;
import sn.uasz.api.uaszloginapi.entities.RegisterDto;
import sn.uasz.api.uaszloginapi.entities.UserDto;
import sn.uasz.spi.authApi.model.LogResponse;
import sn.uasz.spi.authApi.model.User;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUserSpi(UserDto userDto);
    UserDto toUserDto(User user);
//    sn.uasz.spi.authApi.model.User mapUserDtoToUserSpi(UserDto userDto);

    @Mapping(target = "password", ignore = true)
    UserDto signUpToUser(RegisterDto registerDto);

    User mapUserDtoToUser(UserDto userDto);
    UserDto mapUserToUserDto(User user);

    LogResponse mapLogResponseDtoToLogResponse(LogResponseDto user);
    LogResponseDto mapUserDtoToLogResponseDto(UserDto user);

//code ajout

//    Role signUpToRole(String s);

}
