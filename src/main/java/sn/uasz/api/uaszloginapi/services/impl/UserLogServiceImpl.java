package sn.uasz.api.uaszloginapi.services.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import sn.uasz.api.uaszloginapi.dtos.CredentialsDto;
//import sn.uasz.api.uaszloginapi.dtos.SignUpDto;
import sn.uasz.api.uaszloginapi.entities.RoleDto;
import sn.uasz.api.uaszloginapi.entities.UserDto;
import sn.uasz.api.uaszloginapi.execptions.AppException;
import sn.uasz.api.uaszloginapi.mappers.UserMapper;
import sn.uasz.api.uaszloginapi.mappers.UserSpiMapper;
import sn.uasz.api.uaszloginapi.repositories.RoleRepository;
import sn.uasz.api.uaszloginapi.repositories.UserRepository;
import sn.uasz.api.uaszloginapi.services.UserLogService;
import sn.uasz.spi.authApi.model.Log;
import sn.uasz.spi.authApi.model.LogResponse;
import sn.uasz.spi.authApi.model.User;

import java.nio.CharBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Data
@Slf4j
public class UserLogServiceImpl implements UserLogService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserSpiMapper spiMapper;

    @Override
    public LogResponse login(Log  log) {
        UserDto user = userRepository.findByLogin(log.getLogin());
//        System.out.println("***********"+user+"**********");
        if(user == null) {
            throw new AppException("Oups !!! Adresse E-mail ou Mot de passe incorrect.", HttpStatus.NOT_FOUND);
        }
        else if (passwordEncoder.matches(CharBuffer.wrap(log.getPassword()), user.getPassword())) {
            return userMapper.mapLogResponseDtoToLogResponse(userMapper.mapUserDtoToLogResponseDto(user));
        }
        throw new AppException("Oups !!! Adresse E-mail ou Mot de passe incorrect.", HttpStatus.BAD_REQUEST);
    }

    @Override
    public String register(User userDto) {
        UserDto optionalUserDto = userRepository.findByLogin(userDto.getLogin());

        if (optionalUserDto != null) {
            throw new AppException("Un compte avec ce nom d'utilisateur existe deja", HttpStatus.BAD_REQUEST);
        }

        UserDto user = userMapper.toUserDto(userDto);

        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));
        user.setDateCreation(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy-HH-mm-ss")));
        user.setEtatCompte(true);

        user.setRoles(addRoles(userDto.getRoleName()));

        userRepository.save(user);
        return "Inscription reussie";
    }

    public  List<RoleDto> addRoles(String role){
        String [] roles = role.split(",");

        List<RoleDto> newRole = new ArrayList<>();

        for (String rol : roles){
            RoleDto ro = roleRepository.findByName(rol);
            if (ro == null){
                throw new AppException(rol+" n'est pas reconnu comme role!! Veuillez le signaler à votre superieur hiérarchique.", HttpStatus.NOT_FOUND);
            }else {
                newRole.add(ro);
            }
        }
        return newRole;
    }
    @Override
    public UserDto findByEmail(String login) {
        UserDto userDto = userRepository.findByLogin(login);
        if(userDto == null){
            throw new AppException("login introuvable !!!", HttpStatus.NOT_FOUND);
        }
        return userDto;
    }

}
