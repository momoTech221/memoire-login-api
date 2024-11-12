package sn.uasz.api.uaszloginapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
//import sn.uasz.api.uaszloginapi.dtos.SignUpDto;
import sn.uasz.api.uaszloginapi.entities.LogDto;
import sn.uasz.api.uaszloginapi.entities.UserDto;
import sn.uasz.spi.authApi.model.Log;

import java.util.List;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.generated")
public interface UserSpiMapper {
    sn.uasz.spi.authApi.model.User mapUserDtoToUser(UserDto userDto);
    UserDto mapUserToUserDto(sn.uasz.spi.authApi.model.User user);
    @Mapping(target = "password", ignore = true)
    List<sn.uasz.spi.authApi.model.User> mapListUserDtoToListUser(List<UserDto> list);

//    SignUpDto mapSignUpToSignUpDto(SignUp signUp);

    LogDto mapLogToLogDto(Log log);
}
