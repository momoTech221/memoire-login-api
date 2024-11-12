package sn.uasz.api.uaszloginapi.services;


import sn.uasz.api.uaszloginapi.entities.UserDto;
import sn.uasz.spi.authApi.model.Log;
import sn.uasz.spi.authApi.model.LogResponse;
import sn.uasz.spi.authApi.model.User;


public interface UserLogService {
     String register(User user);
     LogResponse login(Log log);
     UserDto findByEmail(String login);
}
